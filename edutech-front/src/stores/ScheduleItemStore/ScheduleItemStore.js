import {observable, action, computed, toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import {ScheduleItem} from './ScheduleItem';
import swal from 'sweetalert';
import {fetchScheduleItems} from '../../services/api/scheduleItem';

// let fetchedItems = fetchScheduleItems();

class ScheduleItemStore {

    @observable scheduleItems = [];

    @action
	populateScheduleItems(){
      axios.get('/edutechentities.common.scheduleitem')
      .then((res) => {
        console.log( "GETTING from JAVA EE: ",res.data)
        this.scheduleItems = res.data;
        console.log("After populating", this.scheduleItems)
      })
      .catch((error)=>{
        console.log(error);
        this.scheduleItems = null;
      });
     }

     @action
    addScheduleItem(title, description, startDate, endDate, location, createdBy, createdAt, type, moduleId, groupId) {
      	const newScheduleItem = new ScheduleItem(title, description, startDate, endDate, location, createdBy, createdAt, type, moduleId, groupId);
        const dataSet = toJS(newScheduleItem);
        console.log("Data Set : ", dataSet)
        axios.post('/edutechentities.common.scheduleitem', dataSet)
          .then((res) => {
            // scheduleItemStore.addScheduleItem(name, description, startDate, endDate, location, user, createdAt, "nandastore", "timetable", "module22", "group44");
            this.scheduleItems.push(newScheduleItem);
            swal(res.data,"Schedule Event Added!" , "success");
            this.populateScheduleItems()

          })
          .catch((err) => {
            console.log("Error in Adding item: ", err)
          })
    }

    @action
    updateScheduleItem(id, title, description, startDate, endDate, location, createdBy, createdAt) {
      var index = this.getIndex(id, this.scheduleItems, "id");
      console.log(index);
      this.scheduleItems[index].title = title;
      this.scheduleItems[index].description = description;
      this.scheduleItems[index].startDate = startDate;
      this.scheduleItems[index].endDate = endDate;
      this.scheduleItems[index].location = location;
      const dataSet = toJS(this.scheduleItems[index]);
      console.log("Dataset to put: ", dataSet)
      axios.put(`/edutechentities.common.scheduleitem/${id}`, dataSet)
      .then((res) => {
        swal("Success!","Item updated successfully." , "success");
      })
      .catch((err) => {
        console.log(err)
      })
    }

    @computed
    get getRecentKeyDates(){
      console.log("GETTING key dates:", this.scheduleItems)
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
        console.log(allDurationsInHr)
        allDurationsInHr.sort(function(a,b) {return (a.duration > b.duration) ? 1 : ((b.duration > a.duration) ? -1 : 0);} ); 
        return allDurationsInHr;
      }
      return allDurations;
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
      return this.scheduleItems.filter(item => item.startDate.includes(selectedItem))
    }

    @action
    removeScheduleItem(id, scheduleItem: ScheduleItem) {
      const index = this.scheduleItems.indexOf(scheduleItem);
 
      axios.delete( `/edutechentities.common.scheduleitem/${id}`)
      .then((res) => {
        console.log(res);
        if (index > -1) {
          this.scheduleItems.splice(index, 1);
        }
        swal("Success!", "Item removed successfully.", "success")
        this.populateScheduleItems();
      })
      .catch((err) =>{
        console.log(err);
      })

      console.log("remaining items:", this.scheduleItems)
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
