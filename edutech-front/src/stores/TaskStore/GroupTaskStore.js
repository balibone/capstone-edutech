import { observable, action, computed } from 'mobx';
import Task from './Task';

export default class GroupTaskStore {
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

    @observable groupId;

    @computed
    get currentTasks() {
      return this.tasks.filter(task => task.statusCode < 2);
    }

    @computed
    get completedTasks() {
      return this.tasks.filter(task => task.statusCode > 1);
    }

    @action
    addGroupTask(groupId, title, assignee, deadline) {
      const newGroupTask = new Task(groupId, title, assignee, deadline);
      this.tasks.unshift(newGroupTask);
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
