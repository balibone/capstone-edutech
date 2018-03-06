import {observable, computed} from 'mobx';

export class Meeting {
  // id = Math.random();
  @observable title;
  @observable description;
  @observable startDate;
  @observable endDate;
  @observable location;
  @observable createdBy;
  @observable type;
  @observable groupId;
  @observable meetingMinutes;

  constructor(title, description,startDate, endDate, location, createdBy, type, groupId, meetingMinutes) {
      this.title = title;
      this.description = description;
      this.startDate = startDate;
      this.endDate = endDate;
      this.location = location;
      this.createdBy = createdBy;
      this.type = type;
      this.groupId = groupId;
      this.meetingMinutes = meetingMinutes;
  }
}