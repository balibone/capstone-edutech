import { observable } from 'mobx';

export default class Task {
  id = Math.random();
  @observable title;
  @observable isCompleted = false;
  @observable isInProgress = false;
  @observable statusCode = 0;
  @observable groupId;
  @observable assignee;
  @observable deadline;

  constructor(groupId, title, assignee, deadline) {
    this.groupId = groupId;
    this.title = title;
    this.assignee = assignee;
    this.deadline = deadline;
  }
}
