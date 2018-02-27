import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {Meeting} from './Meeting';

// let fetchedItems = fetchScheduleItems();

class MeetingStore {

    @observable meetings = [];

    @action
	populateMeetings(){

     	axios.get('/v2/getAllMeetings.json')
	    .then((res) => {
        console.log(res.data)
	      this.meetings = res.data.MeetingItems;
	    })
	    .catch((error)=>{
	      console.log(error);
	      this.meetings = null;
	    });
     }

     @action
    addMeeting(name, description, startTime, endTime, location, isRecurring, createdBy, createdAt, groupId, meetingMinutes) {
      	const newMeeting = new Meeting(name, description, startTime, endTime, location, isRecurring, createdBy, createdAt, groupId, meetingMinutes);
        this.meetings.push(newMeeting);
    }

    // @action
    // removeMeeting(meeting: Task) {
    //   const index = this.tasks.indexOf(task);
    //   if (index > -1) {
    //     this.tasks.splice(index, 1);
    //   }
    // }

    @action
    removeMeeting(id){
      var filteredMeetings = this.meetings.filter((meeting) => meeting._id !== id)
      this.meetings.replace(filteredMeetings);

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
