import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {ScheduleItem} from './ScheduleItem';
import swal from 'sweetalert';
import {fetchScheduleItems} from '../../services/api/scheduleItem';

// let fetchedItems = fetchScheduleItems();

class ScheduleItemStore {

    @observable scheduleItems = [];
    @observable userGroupScheduleItems = [];
    @observable addFormSuccess = false;
    @observable editFormSuccess = false;
    @observable recentKeyDates = [];

    @action
	  populateScheduleItems(username){
      // const username = "localStorage.getItem('username')";
      // const userScheduleItem = await axios.get(`/scheduleitem/user/${username}`);

      axios.get(`/scheduleitem/user/${username}`)
      .then((res) => {
        this.scheduleItems = res.data;
      })
      .catch((error)=>{
        console.log(error);
        // this.scheduleItems = null;
      });
     }

     // @action
     // populateGroupScheduleItems(userList){
     //  var i = 0;
     //  console.log(userList);
     //  this.userGroupScheduleItems = []
     //  while(i<userList){
     //    axios.get(`/scheduleitem/user/${userList[i]}`)
     //    .then((res) => {
     //      this.userGroupScheduleItems[i] = res.data;
     //      i++;
     //    })
     //    .catch((error)=>{
     //      console.log(error);
     //      // this.scheduleItems = null;
     //    });
     //  }
     // }

     @action
     populateMergedScheduleItemsForGroup(groupId){

      axios.get(`/scheduleitem/members/${groupId}`)
        .then((res) => {
          this.userGroupScheduleItems = res.data;
        })
        .catch((err) => {
          console.log(err);
        })

     }

     @action
    addScheduleItem(title, description, startDate, endDate, location, createdBy, assignedTo, type, moduleCode, groupId) {
        if(!title || !description || !startDate || !endDate || !location || !type){
          swal("Warning!", "Please make sure all fields are entered.", "warning");
        } else if(startDate > endDate) {
          swal("Time Error!", "Please make sure start time is earlier than end time.", "warning");
        } else{
          const newScheduleItem = new ScheduleItem(title, description, startDate, endDate, location, createdBy, assignedTo, type, moduleCode, groupId);
          const dataSet = toJS(newScheduleItem);
          dataSet.createdBy ={username: dataSet.createdBy};
          axios.post('/scheduleitem', dataSet)
            .then((res) => {
              this.scheduleItems.push(newScheduleItem);
              swal(res.data,"Schedule Event Added!" , "success");
              this.populateScheduleItems(localStorage.getItem('username'));
              this.addFormSuccess = true;
            })
            .catch((err) => {
              console.log(err);
            })
        }
    }

    @action
    updateScheduleItem(id, title, description, startDate, endDate, location, createdBy, groupId) {
      if(!title || !description || !startDate || !endDate || !location){
        swal("Warning!", "Please make sure all fields are entered.", "warning");
      } else if(startDate >= endDate) {
        swal("Time Error!", "Please make sure start time is earlier than end time.", "warning");
      } else{
        var index = this.getIndex(id, this.scheduleItems, "id");
        this.scheduleItems[index].title = title;
        this.scheduleItems[index].description = description;
        this.scheduleItems[index].startDate = startDate;
        this.scheduleItems[index].endDate = endDate;
        this.scheduleItems[index].location = location;
        this.scheduleItems[index].groupId = groupId;
        const dataSet = toJS(this.scheduleItems[index]);
        console.log("Dataset to put: ", dataSet)
        this.editFormSuccess = true;
        axios.put(`/scheduleitem/${id}`, dataSet)
        .then((res) => {
          swal("Success!","Item updated successfully." , "success");
          this.populateScheduleItems(localStorage.getItem('username'));
          
        })
        .catch((err) => {
          console.log(err)
        })
      }     
    }

    @computed
    get getRecentKeyDates(){
      var now = moment(new Date());
      var scheduleItemsSort = this.scheduleItems;
      var allDurations = []
      var allDurationsInHr = []
      var startDate = []
      if(scheduleItemsSort){
          for(var i =0 ; i<scheduleItemsSort.length ; i++){
          startDate[i] = moment(scheduleItemsSort[i].startDate);
          allDurations[i] = moment.duration(startDate[i].diff(now));
          if(allDurations[i].asHours()>0){
            allDurationsInHr[i] = {
              duration: allDurations[i].asHours(),
              itemDetails: scheduleItemsSort[i],
            }
          }
        }
        allDurationsInHr.sort(function(a,b) {return (a.duration > b.duration) ? 1 : ((b.duration > a.duration) ? -1 : 0);} );
        // this.recentKeyDates = allDurationsInHr;
        return allDurationsInHr;
      }
      return allDurations;
    }

    @action
    getGroupRecentKeyDates(groupId){
      var now = moment(new Date());
      var scheduleItemsSort = this.scheduleItems;
      var allDurations = []
      var allDurationsInHr = []
      var startDate = []
      if(scheduleItemsSort){
        console.log("scheduleItem sort: ", scheduleItemsSort)
          for(var i =0 ; i<scheduleItemsSort.length ; i++){
          startDate[i] = moment(scheduleItemsSort[i].startDate);
          allDurations[i] = moment.duration(startDate[i].diff(now));
          if(allDurations[i].asHours()>0){
            allDurationsInHr[i] = {
              duration: allDurations[i].asHours(),
              itemDetails: scheduleItemsSort[i],

            }
          }
        }
        allDurationsInHr.sort(function(a,b) {return (a.duration > b.duration) ? 1 : ((b.duration > a.duration) ? -1 : 0);} );
        console.log("KEY DATES no filter: ", allDurationsInHr)
        allDurationsInHr.filter((item) => item.itemDetails.id == groupId)
        console.log("KEY DATES filtered: ", allDurationsInHr)
        this.recentKeyDates = allDurationsInHr;
        // return allDurationsInHr;
      }
      // return allDurations;
      this.recentKeyDates = allDurations;
    }

    @computed
    get allScheduleItems(){
      return this.scheduleItems;
    }

    @action
    findOne(id){
      return this.scheduleItems.filter(item => item.id == id)
    }

     @action
    selectedScheduleItem(selectedItem) {
      return this.scheduleItems.filter(item => moment(item.startDate).format('MMM DD YYYY HH:mm').includes(selectedItem))
    }

    @action
    removeScheduleItem(id, scheduleItem: ScheduleItem) {
      axios.delete( `/scheduleitem/${id}`)
      .then((res) => {
        const index = this.scheduleItems.indexOf(scheduleItem);
        if (index > -1) {
          this.scheduleItems.splice(index, 1);
        }
        swal("Success!", "Item removed successfully.", "success")
        this.populateScheduleItems(localStorage.getItem('username'));
      })
      .catch((err) =>{
        console.log(err);
      })
    }

    getIndex(value, arr, prop) {
      for(var i = 0; i < arr.length; i++) {
          if(arr[i][prop] === value) {
              return i;
          }
      }
      return -1;
    }


}

export default new ScheduleItemStore;
