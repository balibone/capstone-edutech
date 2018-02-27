import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { Tabs, Tab, Col, Row, Button, FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { DateTimePicker, DropdownList } from 'react-widgets';
import momentLocalizer from 'react-widgets-moment';
import moment from 'moment';

import 'react-widgets/dist/css/react-widgets.css';

import SingleGroupTask from './SingleGroupTask';
import GroupTaskStore from '../../../stores/TaskStore/GroupTaskStore';
// create a viewModel singleton
const groupTaskStore = new GroupTaskStore();
// setup react-widgets moment localisation
moment.locale('en');
momentLocalizer();

@observer
export default class GroupTask extends Component {
  state = {
    taskTitle: '',
    taskDate: null,
    taskAssignee: '',
  }

  componentWillMount() {
    groupTaskStore.groupId = this.props.groupId;
  }

  addTask(e) {
    if (e.which === 13) {
      groupTaskStore.addTask(e.target.value);
      e.target.value = '';
    }
  }

  handleEnterPress(e) {
    if (e.which === 13) {
      this.addGroupTask();
    }
  }

  addGroupTask() {
    groupTaskStore.addGroupTask(
      this.props.groupId,
      this.state.taskTitle,
      this.state.taskAssignee,
      this.state.taskDate,
    );

    this.setState({
      taskTitle: '',
      taskAssignee: '',
      taskDate: null,
    });
  }

  renderCurrentGroupTasks() {
    return groupTaskStore.currentTasks.map(task =>
      <SingleGroupTask key={task.id} task={task} groupTaskStore={groupTaskStore} />);
  }

  renderCompletedGroupTasks() {
    return groupTaskStore.completedTasks.map(task =>
      <SingleGroupTask key={task.id} task={task} groupTaskStore={groupTaskStore} />);
  }

  renderTaskInput() {
    return (
      <div>
        <Row>
          <Col md={12}>
            <FormControl
              type="text"
              placeholder="Add group task"
              onKeyPress={e => this.handleEnterPress(e)}
              value={this.state.taskTitle}
              onChange={e => this.setState({ taskTitle: e.target.value })}
            />
          </Col>
        </Row>
        <Row className="smallTopGap">
          <Col md={6}>
            <DropdownList
              data={['ahmed', 'yusuf', 'ben', 'david']}
              placeholder="Assign a member"
              onChange={assignee => this.setState({ taskAssignee: assignee })}
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

  render() {
    return (
      <Paper className="standardTopGap paperDefault">
        { this.renderTaskInput() }
        <Tabs defaultActiveKey={1} className="standardTopGap">
          <Tab eventKey={1} title="Current">
            <div className="taskList">
              { this.renderCurrentGroupTasks() }
            </div>
          </Tab>
          <Tab eventKey={2} title="Completed">
            <div className="taskList">
              { this.renderCompletedGroupTasks() }
            </div>
          </Tab>
        </Tabs>
      </Paper>
    );
  }
}
