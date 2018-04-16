import { observable } from 'mobx';

export default class Announcement {
  @observable title;
  @observable message;
  @observable assignedTo;
  @observable createdBy = { username: localStorage.getItem('username') };
  @observable createdAt;
  @observable path;

  constructor(title, message, assignedTo, path) {
    this.title = title;
    this.message = message;
    this.assignedTo = assignedTo;
    this.path = path;
  }
}
