import { observable } from 'mobx';

export default class Group {
  @observable title;
  @observable description;
  @observable imagefilename;
  @observable members;
  @observable moduleCode;
  @observable createdBy;
  @observable createdAt;
  @observable modifiedBy;
  @observable modifiedAt;

  constructor(title, description, imagefilename, members, moduleCode, createdBy, createdAt, modifiedBy, modifiedAt) { // eslint-disable-line
    this.title = title;
    this.description = description;
    this.imagefilename = imagefilename;
    this.members = members;
    this.moduleCode = moduleCode;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
    this.modifiedBy = modifiedBy;
    this.modifiedAt = modifiedAt;
  }
}
