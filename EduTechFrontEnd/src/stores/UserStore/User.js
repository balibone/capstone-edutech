import { observable } from 'mobx';

export default class User {
  @observable username;
  @observable name;
  @observable imageFileName;
  @observable type;

  constructor(username, name, imageFileName, type) {
    this.username = username;
    this.name = name;
    this.imageFileName = imageFileName;
    this.type = type;
  }
}
