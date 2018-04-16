import React, { Component } from 'react';
import { Paper, RaisedButton, FlatButton, Dialog } from 'material-ui';
import { Tabs, Tab, Col, Row, Button, FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { DateTimePicker, DropdownList } from 'react-widgets';
import momentLocalizer from 'react-widgets-moment';
import moment from 'moment';
import swal from 'sweetalert';

import 'react-widgets/dist/css/react-widgets.css';

import TaskAllocationChart from './TaskAllocationChart';
import SingleGroupTask from './SingleGroupTask';
import GroupTaskStore from '../../../stores/TaskStore/GroupTaskStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import './styles.css';
// create a viewModel singleton
// const GroupTaskStore = new GroupTaskStore();
// setup react-widgets moment localisation
moment.locale('en');
momentLocalizer();

@observer
export default class GroupTask extends Component {
  state = {
    taskTitle: null,
    taskDate: null,
    taskAssignee: null,
    activeTabKey: 2,
    openChartDialog: false,
  }

  getGroupMemberNames() {
    const group = GroupStore.selectedGroup;
    const { members } = group;
    const membersNames = members.map(member => member.username);
    return membersNames;
  }

  addGroupTask() {
    if (this.state.taskTitle) {
      GroupTaskStore.addGroupTask(
        this.state.taskTitle,
        this.state.taskDate,
        this.state.taskAssignee,
        this.props.groupId,
      );
      this.setState({
        taskTitle: '',
        taskAssignee: null,
        taskDate: null,
      });
    } else {
      swal('Ooops!', 'Task name cannot be empty', 'error');
    }
  }

  renderBacklogGroupTasks() {
    if (!GroupTaskStore.backlogTasks || GroupTaskStore.backlogTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> No To Dos in this group currently. </p>
        </div>
      );
    }
    return GroupTaskStore.backlogTasks.map(task =>
      <SingleGroupTask key={task.id} task={task} groupTaskStore={GroupTaskStore} groupMemberNames={this.getGroupMemberNames()} />);
  }

  renderCurrentGroupTasks() {
    if (!GroupTaskStore.currentTasks || GroupTaskStore.currentTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> No current tasks in this group.</p>
          <RaisedButton primary label="Add Task from To Do" onClick={() => this.setState({ activeTabKey: 1 })} />
        </div>
      );
    }
    return GroupTaskStore.currentTasks.map(task =>
      <SingleGroupTask key={task.id} task={task} groupTaskStore={GroupTaskStore} groupMemberNames={this.getGroupMemberNames()} />);
  }

  renderCompletedGroupTasks() {
    if (!GroupTaskStore.completedTasks || GroupTaskStore.completedTasks.length < 1) {
      return (
        <div>
          <p className="noTasks lead"> Time to get some things done.</p>
          <RaisedButton primary label="Work on Current Tasks" onClick={() => this.setState({ activeTabKey: 2 })} />
        </div>
      );
    }
    return GroupTaskStore.completedTasks.map(task =>
      <SingleGroupTask key={task.id} task={task} groupTaskStore={GroupTaskStore} />);
  }

  renderTaskInput() {
    return (
      <div>
        <Row>
          <Col md={12}>
            <FormControl
              type="text"
              placeholder="Add group task"
              value={this.state.taskTitle}
              onChange={e => this.setState({ taskTitle: e.target.value })}
              onFocus={() => this.setState({ activeTabKey: 1 })}
            />
          </Col>
        </Row>
        <Row className="smallTopGap">
          <Col md={6}>
            <DropdownList
              data={this.getGroupMemberNames()}
              placeholder="Assign a member"
              style={{ textAlign: 'left' }}
              onChange={assignedTo => this.setState({ taskAssignee: assignedTo })}
              value={this.state.taskAssignee}
            />
          </Col>
          <Col md={6}>
            <DateTimePicker
              time={false}
              min={new Date()}
              placeholder="Set deadline"
              onChange={selectedDate => this.setState({ taskDate: selectedDate })}
              value={this.state.taskDate}
            />
          </Col>
        </Row>
        <Row className="smallTopGap">
          <Col md={12}>
            <Button bsStyle="primary" className="pull-right" onClick={() => this.addGroupTask()}>
              Add Task
            </Button>
          </Col>
        </Row>
      </div>
    );
  }
  renderChartDialog() {
    const actions = [
     <FlatButton
       label="Close"
       primary
       onClick={() => this.setState({ openChartDialog: false })}
     />,
   ];
    return (
      <Dialog
        actions={actions}
        modal
        open={this.state.openChartDialog}
      >
        <TaskAllocationChart />
      </Dialog>
    );
  }

  render() {
    return (
      <div className="MyCurrentTasks">
        <h3> {GroupStore.selectedGroup.title} Tasks </h3>
        <FlatButton
          secondary
          label="View Chart"
          icon={<i className="fas fa-chart-bar" />}
          onClick={() => this.setState({ openChartDialog: true })}
        />
        { this.renderTaskInput() }
        {this.renderChartDialog()}
        <Tabs activeKey={this.state.activeTabKey} onSelect={key => this.setState({ activeTabKey: key })} id="myTasksTab">
          <Tab eventKey={1} title="To Do">
            <div className="taskItems">
              {this.renderBacklogGroupTasks()}
            </div>
          </Tab>
          <Tab eventKey={2} title="Doing">
            <div className="taskItems">
              {this.renderCurrentGroupTasks()}
            </div>
          </Tab>
          <Tab eventKey={3} title="Done">
            <div className="taskItems">
              {this.renderCompletedGroupTasks()}
            </div>
          </Tab>
        </Tabs>
      </div>
    );
  }
}
