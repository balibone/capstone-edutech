import { observable, toJS } from 'mobx';

export default class Task {
  @observable title;
  @observable deadline;
  @observable assignedTo;
  @observable type;
  @observable groupId;
  @observable moduleCode;
  @observable createdBy = { username: localStorage.getItem('username') };
  @observable modifiedBy;
  @observable verifiedBy;
  @observable progressCode = 0;

  constructor(title, type, deadline, assignedTo, groupId, moduleCode) {
    this.title = title;
    this.type = type;
    this.deadline = !deadline ? null : deadline;
    if (assignedTo && assignedTo.length > 0) {
      this.assignedTo = assignedTo;
    } else {
      this.assignedTo = toJS([]);
    }
    this.groupId = groupId;
    this.moduleCode = moduleCode;
  }
}
