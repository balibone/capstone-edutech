import { observable, action, computed, runInAction } from 'mobx';
import moment from 'moment';
import axios from 'axios';
import swal from 'sweetalert';

import AnnouncementStore from '../AnnouncementStore/AnnouncementStore';
import GroupStore from '../GroupStore/GroupStore';
import UtilStore from '../UtilStore/UtilStore';
import TaskStore from './TaskStore';
import ScheduleItemStore from '../ScheduleItemStore/ScheduleItemStore';

import Task from './Task';

class GroupTaskStore {
    @observable tasks = [];
    @observable groupId;
    @observable donePopulating = false;

    async fetchGroupTasks(groupId) {
      try {
        const groupTasks = await axios.get(`/task/group/${groupId}`);
        runInAction(() => {
          this.tasks = groupTasks.data;
          this.donePopulating = true;
        });
      } catch (e) {
        swal('Error', 'Error populating group tasks', 'error');
      }
    }

    async createTask(task: Task) {
      try {
        const newTask = await axios.post('/task', task);
        this.tasks.unshift(newTask.data);
        if (newTask.data.assignedTo[0] && newTask.data.assignedTo[0].username === localStorage.getItem('username')) {
          TaskStore.populateTasks(localStorage.getItem('username'));
          ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
        }
        AnnouncementStore.postAnnouncement(
          GroupStore.selectedGroup.title,
           `${task.title} created as task`,
           GroupStore.selectedGroup.members,
           `/group/${GroupStore.selectedGroup.id}?tabKey=Tasks`,
        );
        UtilStore.openSnackbar('Task added');
      } catch (e) {
        swal('Error', 'Error adding task', 'error');
      }
    }

    async editTask(task: Task) {
      try {
        if (task.assignedTo[0] && task.assignedTo[0].username === localStorage.getItem('username')) {
          TaskStore.populateTasks(localStorage.getItem('username'));
          ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
        }
        await axios.put(`/task/${task.id}`, task: Task);
        UtilStore.openSnackbar('Task editted');
      } catch (e) {
        swal('Error', 'Error editting task', 'error');
      }
    }

    async deleteTask(taskId, index) {
      try {
        await axios.delete(`/task/${taskId}`);
        if (this.tasks[index].assignedTo[0] && this.tasks[index].assignedTo[0].username === localStorage.getItem('username')) {
          TaskStore.populateTasks(localStorage.getItem('username'));
          ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
        }
        this.tasks.splice(index, 1);
        UtilStore.openSnackbar('Task deleted');
      } catch (e) {
        swal('Error', 'Error deleting task', 'error');
      }
    }

    updateTaskProgress(taskId, progressCode) {
      try {
        axios.put(`/task/${taskId}/${progressCode}`);
        const index = this.tasks.findIndex(task => task.id === taskId);
        if (this.tasks[index].assignedTo[0] && this.tasks[index].assignedTo[0].username === localStorage.getItem('username')) {
          TaskStore.populateTasks(localStorage.getItem('username'));
          ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
        }
        UtilStore.openSnackbar('Task progress updated');
      } catch (e) {
        swal('Error', 'Error updating task progress', 'error');
      }
    }

    async verifyTask(task) {
      const username = localStorage.getItem('username');
      try {
        await axios.put(`/task/verify/${task.id}/${username}`);
        task.progressCode = 3;
        if (task.assignedTo[0] && task.assignedTo[0].username === localStorage.getItem('username')) {
          TaskStore.populateTasks(localStorage.getItem('username'));
        }
        UtilStore.openSnackbar('Task verified');
      } catch (e) {
        swal('Error', 'Error verifying task', 'error');
      }
    }

    @computed
    get backlogTasks() {
      return this.tasks.filter(task => task.progressCode === 0);
    }

    @computed
    get currentTasks() {
      return this.tasks.filter(task => task.progressCode === 1);
    }

    @computed
    get completedTasks() {
      return this.tasks.filter(task => task.progressCode > 1);
    }
    @computed
    get uncompletedTasks() {
      return this.tasks.filter(task => task.progressCode < 2);
    }

    @computed
    get taskProgressData() {
      return [this.backlogTasks.length, this.currentTasks.length, this.completedTasks.length];
    }

    @computed
    get taskDeadlineInAWeek() {
      const tasksWithDeadLine = this.uncompletedTasks.filter(task => task.deadline && moment(task.deadline).format() < moment().add('days', 7).format());
      return tasksWithDeadLine;
    }

    @action
    addGroupTask(title, deadline, assignedTo, groupId) {
      const assignedToArrObj = assignedTo ? [{ username: assignedTo }] : null;
      const formattedDeadline = deadline ? moment(deadline).format('YYYY-MM-DDTHH:mm:ss') : null;
      const newGroupTask = new Task(title, 'group', formattedDeadline, assignedToArrObj, groupId);
      this.createTask(newGroupTask);
    }
    // remove and deletes the given todo
    @action
    removeTask(task: Task) {
      const index = this.tasks.indexOf(task);
      if (index > -1) {
        this.deleteTask(task.id, index);
      }
    }
}

export default new GroupTaskStore();
