import React, { Component } from 'react';
import { Paper, RaisedButton } from 'material-ui';
import { observer } from 'mobx-react';
import { Link } from 'react-router-dom';
import { FormControl, Tabs, Tab } from 'react-bootstrap';
import 'react-big-calendar/lib/css/react-big-calendar.css';

import TaskStore from '../../../stores/TaskStore/TaskStore';
import SingleTask from './SingleTask';
import './style.css';

@observer
class MyTasks extends Component {
  state = {
    activeTabKey: 2,
  }
  addTask(e) {
    if (e.target.value && e.which === 13) {
      TaskStore.addTask(e.target.value);
      e.target.value = '';
    }
  }
  renderBacklogTasks() {
    if (!TaskStore.backlogTasks || TaskStore.backlogTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> You have no backlog tasks currently. </p>
        </div>
      );
    }
    return TaskStore.backlogTasks.map(task => <SingleTask key={task.id} task={task} />);
  }
  renderCurrentTasks() {
    if (!TaskStore.currentTasks || TaskStore.currentTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> You have no current tasks.</p>
          <RaisedButton primary label="Add Task from Backlog" onClick={() => this.setState({ activeTabKey: 1 })} />
        </div>
      );
    }
    return TaskStore.currentTasks.map(task => <SingleTask key={task.id} task={task} />);
  }
  renderCompletedTasks() {
    if (!TaskStore.completedTasks || TaskStore.completedTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> Time to get some things done.</p>
          <RaisedButton primary label="Work on Current Tasks" onClick={() => this.setState({ activeTabKey: 2 })} />
        </div>
      );
    }
    return TaskStore.completedTasks.map(task => <SingleTask key={task.id} task={task} />);
  }
  renderExpandViewButton() {
    if (this.props.expandViewButton) {
      return (
        <RaisedButton
          label="Expand Tasks View"
          labelPosition="before"
          icon={<i className="fas fa-expand" />}
          fullWidth
          containerElement={<Link to="mytasks" />} // eslint-disable-line
          className="expandButton"
        />
      );
    }
    return '';
  }
	render() {
		return (
      <Paper className="MyCurrentTasks">
        <h3> My Tasks </h3>
        <FormControl
          type="text"
          className="taskInput"
          placeholder="Add a task"
          onKeyPress={e => this.addTask(e)}
          onFocus={() => this.setState({ activeTabKey: 1 })}
        />
        <Tabs activeKey={this.state.activeTabKey} onSelect={key => this.setState({ activeTabKey: key })} id="myTasksTab">
          <Tab eventKey={1} title="To Do">
            <div className="taskItems">
              {this.renderBacklogTasks()}
            </div>
          </Tab>
          <Tab eventKey={2} title="Doing">
            <div className="taskItems">
              {this.renderCurrentTasks()}
            </div>
          </Tab>
          <Tab eventKey={3} title="Done">
            <div className="taskItems">
              {this.renderCompletedTasks()}
            </div>
          </Tab>
        </Tabs>
        {this.renderExpandViewButton()}
      </Paper>
		);
	}
}

export default MyTasks;
