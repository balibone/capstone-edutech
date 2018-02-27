import {observable, computed} from 'mobx';

export class ScheduleItem {
  // id = Math.random();
  @observable title;
  @observable description;
  @observable startDate;
  @observable endDate;
  @observable location;
  @observable createdBy;
  @observable createdAt;
  // @observable assignedTo;
  @observable type;
  @observable moduleId;
  @observable groupId;

  constructor(title, description,startDate, endDate, location, createdBy, createdAt, type, moduleId, groupId) {
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.location = location;
      this.createdBy = createdBy;
      this.createdAt = createdAt;
      // this.assignedTo = assignedTo;
      this.type = type;
      this.moduleId = moduleId;
      this.groupId = groupId;
  }
}
