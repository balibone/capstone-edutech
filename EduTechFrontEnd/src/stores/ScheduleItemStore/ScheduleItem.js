import { observable, computed } from 'mobx';

export class ScheduleItem {
  // id = Math.random();
  @observable title;
  @observable description;
  @observable startDate;
  @observable endDate;
  @observable location;
  @observable createdBy;
  @observable assignedTo;
  @observable type;
  @observable moduleCode;
  @observable groupId;

  constructor(title, description,startDate, endDate, location, createdBy, assignedTo, type, moduleCode, groupId) {
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.location = location;
      this.createdBy = createdBy;
      this.assignedTo = assignedTo;
      this.type = type;
      this.moduleCode = moduleCode;
      this.groupId = groupId;
  }
}
