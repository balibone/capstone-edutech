import { observable, action, computed, runInAction } from 'mobx';
import axios from 'axios';

import Task from './Task';

export default class GroupTaskStore {
    @observable tasks = [];

    @observable groupId;

    // constructor() {
    //   this.fetchGroupTasks(FeedStore.currentPageId);
    // }

    async fetchGroupTasks(groupId) {
      const groupTasks = await axios.get(`/task/group/${groupId}`);
      runInAction(() => {
        this.tasks = groupTasks.data;
      });
    }

    async createTask(task: Task) {
      await axios.post('/task', task);
      // this.tasks.unshift(task);
      this.fetchGroupTasks(this.groupId);
    }

    async editTask(task: Task) {
      await axios.put(`/task/${task.id}`, task: Task);
      this.fetchGroupTasks(this.groupId);
    }

    async deleteTask(taskId) {
      await axios.delete(`/task/${taskId}`);
      this.fetchGroupTasks(this.groupId);
    }

    updateTaskProgress(taskId, progressCode) {
      axios.put(`/task/${taskId}/${progressCode}`);
    }

    async verifyTask(task) {
      const username = localStorage.getItem('username');
      await axios.put(`/task/verify/${task.id}/${username}`);
      this.fetchGroupTasks(this.groupId);
    }

    @computed
    get currentTasks() {
      return this.tasks.filter(task => task.progressCode < 2);
    }

    @computed
    get completedTasks() {
      return this.tasks.filter(task => task.progressCode > 1);
    }

    @action
    addGroupTask(title, deadline, assignedTo, groupId) {
      const assignedToArrObj = assignedTo ? [{ username: assignedTo }] : null;
      const newGroupTask = new Task(title, 'group', deadline, assignedToArrObj, groupId);
      this.createTask(newGroupTask);
    }
    // remove and deletes the given todo
    @action
    removeTask(task: Task) {
      const index = this.tasks.indexOf(task);
      if (index > -1) {
        this.deleteTask(task.id);
        this.tasks.splice(index, 1);
      }
    }
}
