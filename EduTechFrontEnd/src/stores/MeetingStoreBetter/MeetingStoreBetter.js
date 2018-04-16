import { observable, action, computed, toJS, runInAction } from 'mobx';
import axios from 'axios';
import moment from 'moment';
import swal from 'sweetalert';

import Meeting from './Meeting';
import UtilStore from '../UtilStore/UtilStore';

import { findGroupMeetingItems, createMeetingItem, editMeetingItem, deleteMeetingItem } from '../../services/meetingApi';

// import ScheduleItemStore from '../ScheduleItemStore/ScheduleItemStore';
// let fetchedItems = fetchScheduleItems();

class MeetingStore {
    @observable meetingItems = [];
    @observable donePopulating = false;

     async populateMeetingItems(groupId) {
       try {
         const meetingItems = await findGroupMeetingItems(groupId);
         runInAction(() => {
           // populate with fake data if no items, so bigCalendar will show #hack
           this.meetingItems = meetingItems.data.length > 0 ?
             meetingItems.data : this.meetingItems;
           this.donePopulating = true;
         });
       } catch (e) {
         swal('Error', 'Error populating personal schedule items', 'error');
       }
     }

    @action
    async addMeeting(title, description, startDate, endDate, location, groupId, itemType) {
      if (!title || !description || !startDate || !endDate || !location || !itemType) {
        swal('Warning!', 'Please make sure all fields are entered.', 'warning');
      } else if (startDate > endDate) {
        swal('Time Error!', 'Please make sure start time is earlier than end time.', 'warning');
      } else {
        const createdBy = JSON.parse(localStorage.getItem('currentUser'));
        const dType = 'ScheduleItem';
        const newMeeting = new Meeting(title, description, startDate, endDate, location, createdBy, itemType, groupId, [], dType);
        // const scheduleItem = await axios.post('/scheduleitem', newScheduleItem);
        try {
          const meetingItem = await createMeetingItem(newMeeting);
          this.meetingItems.push(meetingItem.data);
          UtilStore.openSnackbar(`${meetingItem.data.title} added to meeting.`);
        } catch (e) {
          swal('Error', 'Error adding schedule item', 'error');
        }
      }
    }
}

export default new MeetingStore();
