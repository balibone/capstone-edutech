import { observable } from 'mobx';

export default class Idea {
  id = Math.random();
  @observable title;
  @observable owner;
  @observable voters = [];
  @observable brainstormId;

  /**
   * Represents a Feed post.
   * @constructor
   * @param {string} title - The idea.
   */
  constructor(brainstormId, title) {
    this.brainstormId = brainstormId;
    this.title = title;
  }
}
