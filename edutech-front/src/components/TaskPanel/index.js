import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { FormControl, Tabs, Tab } from 'react-bootstrap';
import { observer } from 'mobx-react';

import SingleTask from './SingleTask';
import TaskStore from '../../stores/TaskStore/TaskStore';
// create a viewModel singleton
const taskStore = new TaskStore();

@observer
export class TaskPanel extends Component {
  addTask(e) {
    if (e.which === 13) {
      taskStore.addTask(e.target.value);
      e.target.value = '';
    }
  }

  renderCurrentTasks() {
    return taskStore.currentTasks.map(task =>
      <SingleTask key={task.id} task={task} taskStore={taskStore} />);
  }

  renderCompletedTasks() {
    return taskStore.completedTasks.map(task =>
      <SingleTask key={task.id} task={task} taskStore={taskStore} />);
  }

  render() {
    return (
      <Paper className="standardTopGap paperDefault">
        <FormControl type="text" placeholder="Add task" onKeyPress={e => this.addTask(e)} />
        <Tabs defaultActiveKey={1} className="standardTopGap">
          <Tab eventKey={1} title="Current">
            <div className="taskList">
              { this.renderCurrentTasks() }
            </div>
          </Tab>
          <Tab eventKey={2} title="Completed">
            <div className="taskList">
              { this.renderCompletedTasks() }
            </div>
          </Tab>
        </Tabs>
      </Paper>
    );
  }
}

export default TaskPanel;
