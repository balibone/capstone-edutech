import { observable, runInAction } from 'mobx';
import axios from 'axios';

class UserStore {
  @observable currentUser = {};
  @observable currentUsername = null;

  constructor() {
    this.getUserDetails();
  }

  async getUserDetails() {
    console.log('{localStorage.getItem()', localStorage.getItem('username'))
    const user = await axios.get(`/systemuser/${localStorage.getItem('username')}`);
    console.log('getUserDetails', user.data);
    localStorage.setItem('currentUser', JSON.stringify(user.data));
    console.log('getUserDetails', JSON.parse(localStorage.getItem('currentUser')));

    runInAction(() => {
      this.currentUser = user.data;
    });
    console.log('getUserDetailsm this.currentUser', this.currentUser);

  }
}

export default new UserStore();
