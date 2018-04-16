import { observable, action, computed, toJS, runInAction } from 'mobx';
import axios from 'axios';
import swal from 'sweetalert';

import Post from './Post';
import UtilStore from '../UtilStore/UtilStore';
import GroupStore from '../GroupStore/GroupStore';
import ModuleStore from '../ModuleStore/ModuleStore';
import AnnouncementStore from '../AnnouncementStore/AnnouncementStore';

class FeedStore {
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
      if (!isNaN(post.pageId)) {
        AnnouncementStore.postAnnouncement(
          GroupStore.selectedGroup.title,
          `New conversation started: ${post.message}`,
           GroupStore.selectedGroup.members,
           `/group/${GroupStore.selectedGroup.id}?tabKey=Conversations`,
        );
      } else {
        AnnouncementStore.postAnnouncement(
          ModuleStore.selectedModule.moduleCode,
          `New conversation started: ${post.message}`,
           ModuleStore.selectedModule.members,
           `/module/${ModuleStore.selectedModule.moduleCode}?tabKey=Conversations`,
        );
      }
      UtilStore.openSnackbar('post added');
      this.fetchPagePosts();
    }

    async replyPost(parentPostId, post: Post) {
      await axios.post(`/post/${parentPostId}`, post);
      UtilStore.openSnackbar('Reply added');
      this.fetchPagePosts();
    }

    @action
    async removePost(postId) {
      await axios.delete(`/post/${postId}`);
      UtilStore.openSnackbar('post deleted');
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
      this.removePost(post.id);
    }

    @action
    pinPost(selectedPost: Post) {
      axios.put(`/post/pin/${selectedPost.id}`);
      this.unpinPost();
      const index = this.posts.indexOf(selectedPost);
      if (index > -1) {
        this.posts[index].isPinned = true;
      }
      UtilStore.openSnackbar('post pinned');
      window.scrollTo(0, 0);
    }

    @action
    unpinPost(scene) {
      if (scene === 'module' && localStorage.getItem('userType') === 'student') {
        swal('Error', 'You cannot unpin posts from this page.', 'error');
        return false;
      }
      if (this.pinnedPost) {
        axios.put(`/post/pin/${this.pinnedPost.id}`);
        this.pinnedPost.isPinned = false;
      }
      UtilStore.openSnackbar('post unpinned');
      return true;
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

export default new FeedStore();
