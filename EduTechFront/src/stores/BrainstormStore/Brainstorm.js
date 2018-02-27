import { observable } from 'mobx';

export default class Brainstorm {
  id = Math.random();
  @observable title;
  @observable groupId;
  @observable ideas = [];
  @observable createdAt;
  @observable status = 0;

  /**
   * Represents a Feed post.
   * @constructor
   * @param {string} title - The idea.
   */
  constructor(groupId, title) {
    this.title = title;
    this.createdAt = new Date();
  }
}
