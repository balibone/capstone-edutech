import {observable, computed} from 'mobx';

export class Meeting {
  // id = Math.random();
  @observable title;
  @observable description;
  @observable startDate;
  @observable endDate;
  @observable location;
  @observable createdBy;
  @observable itemType;
  @observable groupId;
  @observable meetingMinutes;
  @observable dType;

  constructor(title, description,startDate, endDate, location, createdBy, itemType, groupId, meetingMinutes, dType) {
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.location = location;
      this.createdBy = createdBy;
      this.itemType = itemType;
      this.groupId = groupId;
      this.meetingMinutes = meetingMinutes;
      this.dType = dType;
  }
}