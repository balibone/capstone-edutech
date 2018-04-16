import { observable, action, computed, runInAction } from 'mobx';
import moment from 'moment';
import swal from 'sweetalert';

import ScheduleItem from './ScheduleItem';
import GroupStore from '../GroupStore/GroupStore';
import AnnouncementStore from '../AnnouncementStore/AnnouncementStore';
import UtilStore from '../UtilStore/UtilStore';
import { findScheduleItem, findGroupScheduleItems, createScheduleItem, editScheduleItem, deleteScheduleItem } from '../../services/scheduleItemApi';

class GroupScheduleItemStore {
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
  @observable collabMeeting;
  @observable doneFetchingCollabMeeting = false;

  async populateGroupScheduleItems(username) {
    try {
      const scheduleItems = await findGroupScheduleItems(username);
      runInAction(() => {
        // populate with fake data if no items, so bigCalendar will show #hack
        this.scheduleItems = scheduleItems.data.length > 0 ?
          scheduleItems.data : this.scheduleItems;
        this.donePopulating = true;
      });
    } catch (e) {
      swal('Error', 'Error populating personal schedule items', 'error');
    }
  }

  async fetchCollabMeeting(id) {
    try {
      console.log('fetchCollabMeeting id', id)
      const meeting = await findScheduleItem(id);
      console.log('meeting', meeting.data)
      runInAction(() => {
        // populate with fake data if no items, so bigCalendar will show #hack
        this.collabMeeting = meeting.data
        this.doneFetchingCollabMeeting = true;
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
      // UtilStore.openSnackbar('WARNING: Please make sure all fields are entered.');
    } else if (start > end) {
      swal('Time Error!', 'Please make sure start time is earlier than end time.', 'warning');
      // UtilStore.openSnackbar('TIME ERROR: Please make sure start time is earlier than end time.');
    } else {
      const createdBy = JSON.parse(localStorage.getItem('currentUser'));
      const dType = 'ScheduleItem';
      const newScheduleItem = new ScheduleItem(
        title, description, start, end, location, createdBy,
        assignedTo, itemType, moduleCode, groupId, dType,
      );
      try {
        const scheduleItem = await createScheduleItem(newScheduleItem);
        this.scheduleItems.push(scheduleItem.data);
        UtilStore.openSnackbar(`${scheduleItem.data.title} created as Meeting`);
        AnnouncementStore.postAnnouncement(
          GroupStore.selectedGroup.title,
           `Meeting ${title} scheduled`,
           GroupStore.selectedGroup.members,
           `/group/${GroupStore.selectedGroup.id}?tabKey=Meetings`,
        );
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
        UtilStore.openSnackbar('Meeting updated');
        this.scheduleItems[index].title = title;
        this.scheduleItems[index].description = description;
        this.scheduleItems[index].startDate = startDate;
        this.scheduleItems[index].endDate = endDate;
        this.scheduleItems[index].location = location;
      } catch (e) {
        swal('Error', 'Error editing meeting', 'error');
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
      UtilStore.openSnackbar(`${deletedScheduleItem.title} deleted`);
    } catch (e) {
      swal('Error', 'Error deleting schedule item', 'error');
    }
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
  @computed
  get sortedUpcomingMeetings() {
    const upcomingScheduleItems = this.getUpcomingScheduleItems(this.scheduleItems);
    const sortedScheduleItems = this.sortScheduleItemsAsc(upcomingScheduleItems);
    const sortedUpcomingMeetings = sortedScheduleItems.filter(scheduleItem =>
      (scheduleItem.itemType === 'meeting' && scheduleItem.groupId === GroupStore.selectedGroup.id));
    return sortedUpcomingMeetings;
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
    return this.sortScheduleItemsDesc(this.scheduleItems.filter(scheduleItem => (scheduleItem.itemType === 'meeting' && scheduleItem.groupId === GroupStore.selectedGroup.id)));
  }
  @computed
  get assessmentItems() {
    return this.scheduleItems.filter(scheduleItem => scheduleItem.itemType === 'assessment');
  }
  @computed
  get taskItems() {
    return this.scheduleItems.filter(scheduleItem => (scheduleItem.itemType === 'task' && scheduleItem.groupId === GroupStore.selectedGroup.id));
  }
  @computed
  get groupItems() {
    return this.taskItems.concat(this.meetingItems);
  }

  @action
  getScheduleItem(scheduleItemId) {
    const index =
      this.scheduleItems.findIndex(scheduleItem => scheduleItem.id === scheduleItemId);
    return this.scheduleItems[index];
  }

  @action
  setCollabMeeting(scheduleItemId) {
    console.log('scheduleItems', this.scheduleItems)
    const index =
      this.scheduleItems.findIndex(scheduleItem => scheduleItem.id === scheduleItemId);
      console.log('index', index)
    this.collabMeeting = this.scheduleItems[index];
    console.log('this.collabMeeting', this.collabMeeting);
  }
}

export default new GroupScheduleItemStore();
