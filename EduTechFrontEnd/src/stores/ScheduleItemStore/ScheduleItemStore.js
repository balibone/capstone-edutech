import { observable, action, computed, runInAction } from 'mobx';
import moment from 'moment';
import swal from 'sweetalert';
import axios from 'axios';

import ScheduleItem from './ScheduleItem';
import UtilStore from '../UtilStore/UtilStore';
import { findSemester, findUserScheduleItems, createScheduleItem, editScheduleItem, deleteScheduleItem } from '../../services/scheduleItemApi';

class ScheduleItemStore {
  @observable scheduleItems = [
    {
      id: 0,
      title: 'All Day Event very long title',
      allDay: true,
      start: new Date(2015, 3, 0),
      end: new Date(2015, 3, 1),
    },
  ];
  @observable donePopulating = false;
  @observable semester;
  @observable userGroupScheduleItems = [];

  async populateScheduleItems(username) {
    try {
      const scheduleItems = await findUserScheduleItems(username);
      const semester = await findSemester();
      runInAction(() => {
        // populate with fake data if no items, so bigCalendar will show #hack
        this.scheduleItems = scheduleItems.data.length > 0 ?
          scheduleItems.data : this.scheduleItems;
        this.semester = semester.data[0]; // eslint-disable-line
        this.donePopulating = true;
      });
    } catch (e) {
      swal('Error', 'Error populating personal schedule items', 'error');
    }
  }

  @action
  async addScheduleItem(
    title, description, start, end, location,
    assignedTo, itemType, moduleCode, groupId,
  ) {
    if (!title || !description || !start || !end || !location || !itemType) {
      swal('Warning!', 'Please make sure all fields are entered.', 'warning');
    } else if (start > end) {
      swal('Time Error!', 'Please make sure start time is earlier than end time.', 'warning');
    } else {
      const createdBy = JSON.parse(localStorage.getItem('currentUser'));
      const dType = 'ScheduleItem';
      const newScheduleItem = new ScheduleItem(
        title, description, start, end, location, createdBy,
        assignedTo, itemType, moduleCode, groupId, dType,
      );
      // const scheduleItem = await axios.post('/scheduleitem', newScheduleItem);
      try {
        const scheduleItem = await createScheduleItem(newScheduleItem);
        this.scheduleItems.push(scheduleItem.data);
        UtilStore.openSnackbar(`${scheduleItem.data.title} added to calendar`);
      } catch (e) {
        swal('Error', 'Error adding schedule item', 'error');
      }
    }
  }

  @action
  async updateScheduleItem(id, title, description, start, end, location) {
    if (!title || !description || !start || !end || !location) {
      swal('Warning!', 'Please make sure all fields are entered.', 'warning');
    } else if (start > end) {
      swal('Time Error!', 'Please make sure start time is earlier than end time.', 'warning');
    } else {
      const index = this.scheduleItems.findIndex(scheduleItem => scheduleItem.id === id);
      const startDate = moment(start).format('YYYY-MM-DDTHH:mm:ss');
      const endDate = moment(end).format('YYYY-MM-DDTHH:mm:ss');
      const updatedScheduleItem = {
        ...this.scheduleItems[index],
        title,
        description,
        startDate,
        endDate,
      };
      try {
        await editScheduleItem(id, updatedScheduleItem);
        UtilStore.openSnackbar('Event updated');
        this.scheduleItems[index].title = title;
        this.scheduleItems[index].description = description;
        this.scheduleItems[index].startDate = startDate;
        this.scheduleItems[index].endDate = endDate;
        this.scheduleItems[index].location = location;
      } catch (e) {
        swal('Error', 'Error editing  schedule item', 'error');
      }
    }
  }

  @action
  async removeScheduleItem(scheduleItemId) {
    try {
      await deleteScheduleItem(scheduleItemId);
      const index =
        this.scheduleItems.findIndex(scheduleItem => scheduleItem.id === scheduleItemId);
      const deletedScheduleItem = this.scheduleItems[index];
      this.scheduleItems.splice(index, 1);
      UtilStore.openSnackbar(`${deletedScheduleItem.title} deleted from calendar`);
    } catch (e) {
      swal('Error', 'Error deleting schedule item', 'error');
    }
  }

  @action
  getModuleKeyDates(moduleCode) {
    return this.sortedUpcomingKeyDates.filter(scheduleItem => (scheduleItem.itemType === 'assessment' || scheduleItem.itemType === 'task') && scheduleItem.moduleCode === moduleCode);
  }

  @action
  getGroupKeyDates(groupId) {
    return this.sortedUpcomingKeyDates.filter(scheduleItem => (scheduleItem.itemType === 'meeting' || scheduleItem.itemType === 'task') && scheduleItem.groupId === groupId);
  }

  getUpcomingScheduleItems(scheduleItems) {
    return scheduleItems.filter(scheduleItemDate =>
      new Date(scheduleItemDate.startDate) > new Date());
  }

  sortScheduleItemsAsc(scheduleItems) {
    return scheduleItems.sort((a, b) => new Date(a.startDate) - new Date(b.startDate));
  }

  sortScheduleItemsDesc(scheduleItems) {
    return scheduleItems.sort((a, b) => new Date(b.startDate) - new Date(a.startDate));
  }

  @computed
  get sortedUpcomingKeyDates() {
    const upcomingScheduleItems = this.getUpcomingScheduleItems(this.scheduleItems);
    const sortedScheduleItems = this.sortScheduleItemsAsc(upcomingScheduleItems);
    return sortedScheduleItems;
  }

  @action
  getModuleKeyDates(moduleCode) {
    return this.sortedUpcomingKeyDates.filter(scheduleItem => (scheduleItem.itemType === 'assessment' || scheduleItem.itemType === 'task') && scheduleItem.moduleCode === moduleCode);
  }

  @action
  getGroupKeyDates(groupId) {
    return this.sortedUpcomingKeyDates.filter(scheduleItem => (scheduleItem.itemType === 'meeting' || scheduleItem.itemType === 'task') && scheduleItem.groupId === groupId);
  }

  @action
  getKeyDates(itemType) {
    return this.sortedUpcomingKeyDates.filter(scheduleItem => scheduleItem.itemType === itemType);
  }

  @computed
  get personalItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'personal');
  }
  @computed
  get timetableItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'timetable');
  }
  @computed
  get meetingItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'meeting');
  }
  @computed
  get assessmentItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'assessment');
  }
  @computed
  get taskItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'task');
  }

  @computed
  get semesterNumberOfWeeks() {
    return moment(this.semester.endDate).diff(moment(this.semester.startDate), 'week') + 1;
  }

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
}

export default new ScheduleItemStore();
