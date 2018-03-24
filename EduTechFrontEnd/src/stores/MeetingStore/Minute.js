import {observable, computed} from 'mobx';

export class Minute {
  // id = Math.random();
  @observable id;
  @observable scheduleItem;
  @observable startTime;
  @observable endTime;
  @observable attendees;
  @observable agendas;
  @observable createdAt;
  @observable attachments;

  constructor(id, scheduleItem, startTime, endTime, attendees, agendas, createdAt, attachments) {
      this.id = id;
      this.scheduleItem = scheduleItem;
      this.startTime = startTime;
      this.agendas = agendas;
      this.createdAt = createdAt;
      this.attachments = attachments;
  }
}