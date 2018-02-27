import { observable } from 'mobx';

export default class Post {
  id = Math.random();
  @observable pageId;
  @observable postedBy;
  @observable message;
  @observable replyTo;
  @observable likers = [];
  @observable isPinned = false;

  /**
   * Represents a Feed post.
   * @constructor
   * @param {string} pageId - The id of the page the post belongs to.
   * @param {string} poster - The poster's user object.
   * @param {string} message - The message of the post.
   * @param {string} replyTo - OPTIONAL. The id of post this post is replying to.
   */
  constructor(pageId, poster, message, replyTo) {
    this.pageId = pageId;
    this.poster = poster;
    this.message = message;
    this.replyTo = replyTo;
  }
}
