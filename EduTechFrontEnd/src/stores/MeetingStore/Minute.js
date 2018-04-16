import { observable } from 'mobx';

export class Minute {
  // id = Math.random();
  @observable meeting;
  @observable startTime;
  @observable endTime;
  @observable attendees;
  @observable agendas;
  @observable createdAt;
  @observable attachments;

  constructor(meeting, startTime, endTime, attendees, agendas, createdAt, attachments) {
      this.meeting = meeting;
      this.startTime = startTime;
      this.endTime = endTime;
      this.attendees = attendees;
      this.agendas = agendas;
      this.createdAt = createdAt;
      this.attachments = attachments;
  }
}
