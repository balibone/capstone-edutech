import { observable, action, computed, toJS } from 'mobx';
import Post from './Post';

export default class FeedStore {
    @observable posts = [{
      id: 1,
      pageId: '1',
      poster: {
        id: 1,
        name: 'ahmed',
        img: 'avatar.png',
      },
      message: 'hello world.',
      replyTo: null,
      isPinned: false,
      likers: [],
    }, {
      id: 2,
      pageId: '1',
      poster: {
        id: 2,
        name: 'david',
        img: 'avatar2.png',
      },
      message: 'hey there, universe .',
      replyTo: null,
      isPinned: false,
      likers: [],
    }, {
      id: 3,
      pageId: '1',
      poster: {
        id: 2,
        name: 'david',
        img: 'avatar2.png',
      },
      message: 'hey there, universe .',
      replyTo: '1',
      isPinned: false,
      likers: [],
    }, {
      id: 4,
      pageId: '1',
      poster: {
        id: 1,
        name: 'ahmed',
        img: 'avatar.png',
      },
      message: 'hey you.',
      replyTo: 1,
      isPinned: false,
      likers: [],
    }];

    @observable currentPageId;

    /**
     * Represents a Feed post.
     * @constructor
     * @param {string} pageId - The id of the page the feed post belongs to.
     * @param {string} poster - The poster's user object.
     * @param {string} message - The message of the post.
     * @param {string} replyTo - OPTIONAL. The id of post this post is replying to.
     */
    @action
    createPost(pageId, poster, message, replyTo) {
      const newPost = new Post(pageId, poster, message, replyTo);
      if (replyTo) {
        this.posts.push(newPost);
      } else {
        this.posts.unshift(newPost);
      }
    }

    @action
    deletePost(post: Post) {
      const index = this.posts.indexOf(post);
      if (index > -1) {
        this.posts.splice(index, 1);
      }
    }

    @action
    pinPost(selectedPost: Post) {
      this.unpinPost();

      const index = this.posts.indexOf(selectedPost);
      if (index > -1) {
        this.posts[index].isPinned = true;
      }
    }

    @action
    unpinPost() {
      if (this.pinnedPost) this.pinnedPost.isPinned = false;
    }

    @computed
    get pinnedPost() {
      const pinnedPost = this.posts.filter(post =>
        (post.replyTo === null
          && post.pageId === this.currentPageId
          && post.isPinned === true));
      return pinnedPost[0];
    }

    @computed
    get mainPosts() {
      return this.posts.filter(post =>
        (post.replyTo === null
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
