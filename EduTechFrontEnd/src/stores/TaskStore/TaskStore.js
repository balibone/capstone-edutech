import { observable, action, computed, runInAction } from 'mobx';
import swal from 'sweetalert';
import moment from 'moment';

import Task from './Task';
import { findUserTasks, createTask, deleteTask, editTask, updateTaskProgress } from '../../services/taskApi';
import UtilStore from '../UtilStore/UtilStore';
import ScheduleItemStore from '../ScheduleItemStore/ScheduleItemStore';

class TaskStore {
    @observable tasks = [];
    @observable donePopulating = false;

    async populateTasks(username) {
      try {
        const userTasks = await findUserTasks(username);
        runInAction(() => {
          this.tasks = userTasks.data;
          this.donePopulating = true;
        });
      } catch (e) {
        swal('Error', 'Error populating tasks', 'error');
      }
    }

    @action
    async addTask(taskTitle) {
      const newTask = new Task(taskTitle, 'personal');
      try {
        const task = await createTask(newTask);
        this.tasks.unshift(task.data);
        UtilStore.openSnackbar(`${task.data.title} added to To Do`);
      } catch (e) {
        swal('Error', 'Error adding task', 'error');
      }
    }

    @action
    async removeTask(task: Task) {
      try {
        const index = this.tasks.indexOf(task);
        if (index > -1) {
          await deleteTask(task.id);
          this.tasks.splice(index, 1);
        }
        UtilStore.openSnackbar(`${task.title} deleted from Task`);
      } catch (e) {
        swal('Error', 'Error deleting task', 'error');
      }
    }

    @action
    async editTask(updatedTask: Task) {
      try {
        await editTask(updatedTask);
        const index = this.tasks.findIndex(task => task.id === updatedTask.id);
        this.tasks[index] = updatedTask;
        UtilStore.openSnackbar('Task editted');
        ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
      } catch (e) {
        swal('Error', 'Error editing task', 'error');
      }
    }

    @action
    async updateTaskProgress(task, progressCode) {
      try {
        await updateTaskProgress(task.id, progressCode);
        task.progressCode = progressCode;
        switch (progressCode) {
          case 0:
            UtilStore.openSnackbar(`${task.title} moved back to To Do`);
            break;
          case 1:
            UtilStore.openSnackbar(`${task.title} moved to Doing`);
            break;
          case 2:
            UtilStore.openSnackbar(`${task.title} moved to Done`);
            break;
          default:
            UtilStore.openSnackbar(`${task.title} progress updated`);
        }
      } catch (e) {
        swal('Error', 'Error updating task progress', 'error');
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
}


export default new TaskStore();
