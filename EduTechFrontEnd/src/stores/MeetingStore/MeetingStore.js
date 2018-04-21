import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import { Meeting } from './Meeting';
import swal from 'sweetalert';
import _ from 'lodash';

import UtilStore from '../UtilStore/UtilStore';

import ScheduleItemStore from '../ScheduleItemStore/ScheduleItemStore';
// let fetchedItems = fetchScheduleItems();

class MeetingStore {
    @observable meetings = [];
    @observable editFormSuccess;
    @observable addFormSuccess = false;
    @observable selectedMeeting = []; // for meeting minute page

    @action
	populateMeetings(groupId) {
      // const groupId = 101;
      console.log(groupId);
     	axios.get(`/scheduleitem/group/${groupId}`)
	    .then((res) => {
        console.log('Populating MeetingStore', res.data);
	      this.meetings = res.data;
        if (this.selectedMeeting !== []) {
          const selectedMeetingId = this.selectedMeeting.id;
          const index = _.findIndex(this.meetings, o => o.id === selectedMeetingId);
          console.log('index of selected meeting: ', index);
          this.selectedMeeting = this.meetings[index];
        }
        ScheduleItemStore.populateMergedScheduleItemsForGroup(groupId);
	    })
	    .catch((error) => {
	      console.log(error);
        // swal('Error!', 'Network error in getting meetings.', 'error');
	    });
     }

     @action
    addMeeting(title, description, startDate, endDate, location, groupId, itemType) {
      	if (!title || !description || !startDate || !endDate || !location) {
          swal('Warning!', 'Please make sure all fields are entered.', 'warning');
        } else if (startDate > endDate) {
          swal('Time Error!', 'Please make sure start time is earlier than end time.', 'warning');
        } else {
          const dType = 'ScheduleItem';
          const createdBy = localStorage.getItem('username');
          const newMeeting = new Meeting(title, description, startDate,
            endDate, location, createdBy, itemType, groupId, [], dType
          );
          const dataSet = toJS(newMeeting);
          dataSet.createdBy = { username : dataSet.createdBy };
          console.log("Data Set : ", dataSet);
          axios.post('/scheduleitem', dataSet)
            .then((res) => {
              this.meetings.push(newMeeting);
              UtilStore.openSnackbar('Meeting Event Added.')
              this.populateMeetings(groupId);
              this.addFormSuccess = true;
            })
            .catch((err) => {
              console.log(err);
              swal("Network Error!","Unable to create ","error");
            })
        }
    }

    @action
    updateMeeting(id, title, description, startDate, endDate, location, createdBy, groupId) {
      if (!title || !description || !startDate || !endDate || !location) {
          swal("Warning!", "Please make sure all fields are entered.", "warning");
        } else if (startDate > endDate) {
          swal("Time Error!", "Please make sure start time is earlier than end time.", "warning");
        } else{
          var index = this.getIndex(id, this.meetings, "id");
          this.meetings[index].title = title;
          this.meetings[index].description = description;
          this.meetings[index].startDate = startDate;
          this.meetings[index].endDate = endDate;
          this.meetings[index].location = location;
          this.meetings[index].groupId = groupId;
          const dataSet = toJS(this.meetings[index]);
          axios.put(`/scheduleitem/${id}`, dataSet)
          .then((res) => {
            UtilStore.openSnackbar('Meeting updated successfully.')
            this.populateMeetings(groupId);
            this.editFormSuccess = true;
            return true;
          })
          .catch((err) => {
            console.log(err)
            return false;
          })
        }
    }

    @action
    removeMeeting(id, groupId, meeting: Meeting) {
      axios.delete( `/scheduleitem/${id}`)
      .then((res) => {
        const index = this.meetings.indexOf(meeting);
        if (index > -1) {
          this.meetings.splice(index, 1);
        }
        UtilStore.openSnackbar('Meeting removed successfully.')
        this.populateMeetings(groupId);
      })
      .catch((err) => {
        console.log(err);
        swal("Error!", "Network error in removing meeting.", "error")
      })
    }

    @action
    getSelectedMeeting(meeting) {
      this.selectedMeeting = meeting;
    }

    @action
    getIndex(value, arr, prop) {
      for(var i = 0; i < arr.length; i++) {
          if(arr[i][prop] === value) {
              return i;
          }
      }
      return -1;
    }


}

export default new MeetingStore;
