import { observable } from 'mobx';

export default class Post {
  @observable pageId;
  @observable message;
  @observable replies = [];
  @observable replyTo;
  @observable isPinned = false;
  @observable likedBy = [];
  @observable createdBy = { username: localStorage.getItem('username') };

  constructor(pageId, message, replyTo) {
    this.pageId = pageId;
    this.message = message;
    this.replyTo = replyTo;
  }
}
