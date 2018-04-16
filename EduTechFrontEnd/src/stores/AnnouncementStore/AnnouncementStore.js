import { observable, action, runInAction } from 'mobx';
import swal from 'sweetalert';

import Announcement from './Announcement';
import { findUserAnnouncements, postAnnouncement } from '../../services/announcementApi';


class AnnouncementStore {
    @observable announcements = [];
    @observable donePopulating = false;

    async populateAnnouncements(username) {
      try {
        const userAnnouncements = await findUserAnnouncements(username);
        runInAction(() => {
          this.announcements = userAnnouncements.data;
          this.donePopulating = true;
        });
      } catch (e) {
        swal('Error', 'Error populating announcements', 'error');
      }
    }

    @action
    async postAnnouncement(title, message, assignedTo, path) {
      const newAnnouncement = new Announcement(title, message, assignedTo, path);
      try {
        const announcement = await postAnnouncement(newAnnouncement);
        this.announcements.unshift(announcement.data);
      } catch (e) {
        swal('Error', 'Error pushing announcement', 'error');
      }
    }
}


export default new AnnouncementStore();
