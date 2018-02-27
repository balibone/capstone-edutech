import {observable, computed} from 'mobx';

export class Meeting {
  // id = Math.random();
  @observable name;
  @observable description;
  @observable startTime;
  @observable endTime;
  @observable location;
  @observable isRecurring;
  @observable createdBy;
  @observable createdAt;
  @observable groupId;
  @observable meetingMinutes;

  constructor(name, description,startTime, endTime, location, isRecurring, createdBy, createdAt, groupId, meetingMinutes) {
      this.name = name;
      this.description = description;
      this.startTime = startTime;
      this.endTime = endTime;
      this.location = location;
      this.isRecurring = isRecurring;
      this.createdBy = createdBy;
      this.createdAt = createdAt;
      this.groupId = groupId;
      this.meetingMinutes = meetingMinutes;
  }
}