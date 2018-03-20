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
  @observable itemType;
  @observable moduleCode;
  @observable groupId;
  @observable dType;

  constructor(title, description,startDate, endDate, location, createdBy, assignedTo, itemType, moduleCode, groupId, dType) {
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.location = location;
      this.createdBy = createdBy;
      this.assignedTo = assignedTo;
      this.itemType = itemType;
      this.moduleCode = moduleCode;
      this.groupId = groupId;
      this.dType = dType;
  }
}
