import { observable, action, computed } from 'mobx';
import Task from './Task';

export default class TaskStore {
    @observable tasks = [{
      id: 1,
      title: 'new',
      isCompleted: true,
      isInProgress: false,
      statusCode: 1,
      groupId: 2,
    }, {
      id: 2,
      title: 'new jugek',
      isCompleted: false,
      isInProgress: true,
      statusCode: 2,
      groupId: 2,
    }, {
      id: 3,
      title: 'new1',
      isCompleted: true,
      isInProgress: false,
      statusCode: 1,
      groupId: 1,
    }, {
      id: 4,
      title: 'new jugek1',
      isCompleted: false,
      isInProgress: true,
      statusCode: 2,
      groupId: 1,
    }];

    @computed
    get currentTasks() {
      return this.tasks.filter(task => task.statusCode < 2);
    }

    @computed
    get completedTasks() {
      return this.tasks.filter(task => task.statusCode > 1);
    }

    @action
    addTask(taskTitle) {
      const newTask = new Task(null, taskTitle, null, null);
      this.tasks.unshift(newTask);
    }

    // remove and deletes the given todo
    @action
    removeTask(task: Task) {
      const index = this.tasks.indexOf(task);
      if (index > -1) {
        this.tasks.splice(index, 1);
      }
    }
}
