import { observable, computed } from 'mobx';

export class Announcement {
  // id = Math.random();
  @observable id;
  @observable assignedTo;
  @observable title;
  @observable message;
  @observable createdBy;
  @observable createdAt;
  @observable seenBy;
  @observable path;

  constructor(id, assignedTo, title, message, createdBy, createdAt, seenBy, path) {
      this.id = id;
      this.assignedTo = assignedTo;
      this.title = title;
      this.message = message;
      this.createdBy = createdBy;
      this.createdAt = createdAt;
      this.seenBy = seenBy;
      this.path  = path;
  }
}
