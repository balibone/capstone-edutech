import { observable, action, computed, toJS, runInAction } from 'mobx';
import axios from 'axios';
import Post from './Post';

export default class FeedStore {
    @observable posts = [];

    @observable currentPageId;

    constructor() {
      this.fetchPagePosts();
    }

    async fetchPagePosts() {
      const posts = await axios.get(`/post/${this.currentPageId}`);
      runInAction(() => {
        this.posts = posts.data;
      });
    }

    async createPost(post: Post) {
      await axios.post('/post', post);
      this.fetchPagePosts();
    }

    async replyPost(parentPostId, post: Post) {
      await axios.post(`/post/${parentPostId}`, post);
      this.fetchPagePosts();
    }

    @action
    async removePost(postId) {
      console.log('removePost', postId)
      await axios.delete(`/post/${postId}`);
      this.fetchPagePosts();
    }


    @action
    addPost(pageId, message) {
      const newPost = new Post(pageId, message, null);
      this.createPost(newPost);
      this.posts.push(newPost);
    }

    @action
    addReply(message, parentPostId) {
      const newReply = new Post(null, message, parentPostId);
      this.replyPost(parentPostId, newReply);
    }

    @action
    deletePost(post: Post) {
      console.log('deletePost', post);
      this.removePost(post.id);
      // const index = this.posts.indexOf(post);
      // if (index > -1) {
      //   this.posts.splice(index, 1);
      // }
    }

    @action
    pinPost(selectedPost: Post) {
      axios.put(`/post/pin/${selectedPost.id}`);
      this.unpinPost();
      const index = this.posts.indexOf(selectedPost);
      if (index > -1) {
        this.posts[index].isPinned = true;
      }
    }

    @action
    unpinPost() {
      if (this.pinnedPost) {
        axios.put(`/post/pin/${this.pinnedPost.id}`);
        this.pinnedPost.isPinned = false;
      }
    }

    @computed
    get pinnedPost() {
      const pinnedPost = this.posts.filter(post =>
        (!post.replyTo
          && post.pageId === this.currentPageId
          && post.isPinned === true));
      return pinnedPost[0];
    }

    @computed
    get mainPosts() {
      return this.posts.filter(post =>
        (!post.replyTo
          && post.pageId === this.currentPageId
          && post.isPinned === false));
    }

    @action
    getReplies(postId) {
      return this.posts.filter(post => post.replyTo === postId);
    }

    @action
    toggleLikePost(post, liker) {
      const postIndex = this.posts.indexOf(post);
      if (postIndex > -1) {
        const plainLikersArr = toJS(this.posts[postIndex].likers);
        const likerIndex = plainLikersArr.findIndex(user => user.id === liker.id);
        // if user is in likers array
        if (likerIndex > -1) {
          // remove user from likers array
          this.posts[postIndex].likers.splice(likerIndex, 1);
          return false;
        }
        // if user is not likers array, add as liker
        this.posts[postIndex].likers.push(liker);
        return true;
      }
      return false;
    }
}
