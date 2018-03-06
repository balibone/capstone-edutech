import { observable, action, computed, runInAction } from 'mobx';
import axios from 'axios';
import Task from './Task';

import { findUserTasks } from '../../services/taskAPI';

export default class TaskStore {
    @observable tasks = [];

    constructor() {
      this.fetchUserTasks();
    }

    async fetchUserTasks() {
      const userTasks = await findUserTasks(localStorage.getItem('username'));
      runInAction(() => {
        this.tasks = userTasks.data;
      });
    }

    async createTask(task: Task) {
      await axios.post('/task', task);
      this.fetchUserTasks();
    }

    async editTask(task: Task) {
      await axios.put(`/task/${task.id}`, task: Task);
      this.fetchUserTasks();
    }

    deleteTask(taskId) {
      axios.delete(`/task/${taskId}`);
    }

    updateTaskProgress(taskId, progressCode) {
      axios.put(`/task/${taskId}/${progressCode}`);
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
    addTask(taskTitle) {
      const newTask = new Task(taskTitle, 'personal');
      this.createTask(newTask);
    }

    // remove and deletes the given todo
    @action
    async removeTask(task: Task) {
      const index = this.tasks.indexOf(task);
      if (index > -1) {
        this.deleteTask(task.id);
        this.tasks.splice(index, 1);
      }
    }
}
