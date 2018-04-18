import { observable } from 'mobx';

export class Minute {
  // id = Math.random();
  @observable meeting;
  @observable startTime;
  @observable endTime;
  @observable attendees;
  @observable agendas;
  @observable modifiedAt;
  @observable modifiedBy;
  @observable attachments;

  constructor(meeting, startTime, endTime, attendees, agendas, modifiedAt, attachments, modifiedBy) {
      this.meeting = meeting;
      this.startTime = startTime;
      this.endTime = endTime;
      this.attendees = attendees;
      this.agendas = agendas;
      this.modifiedAt = modifiedAt;
      this.attachments = attachments;
      this.modifiedBy = modifiedBy;
  }
}
