import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Paper, RaisedButton } from 'material-ui';
import { Row, Col } from 'react-bootstrap';
import swal from 'sweetalert';
import moment from 'moment';
import { Animated } from 'react-animated-css';

import TaskStore from '../../../../stores/TaskStore/TaskStore';
import EditSingleTask from '../EditSingleTask';
import './style.css';

@observer
export default class SingleTask extends Component {
  state = {
    editView: false,
    edittedTitle: this.props.task.title,
    edittedDate: this.props.task.deadline,
  }

  // Start EditSingleTask Operations
  handleChangeTitleEdit(e) {
    this.setState({ edittedTitle: e.target.value });
  }

  handleChangeDateEdit(newDateline) {
    this.setState({ edittedDate: newDateline });
  }

  handleSaveEdit(task) {
    if (this.state.edittedTitle) {
      task.title = this.state.edittedTitle;
      task.deadline = this.state.edittedDate ? moment(this.state.edittedDate).format('YYYY-MM-DDTHH:mm:ss') : null;
      TaskStore.editTask(task);
      this.setState({ editView: false });
    } else {
      swal('Ooops!', 'Task name cannot be empty', 'error');
    }
  }

  handleCancelEdit(task) {
    this.setState({
      edittedTitle: task.title,
      edittedDate: task.deadline,
      editView: false,
    });
  }

  removeTask(task) {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this item!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        TaskStore.removeTask(task);
      }
    });
  }

  handleUpdateProgress(task, progressCode) {
    TaskStore.updateTaskProgress(task, progressCode);
  }
  /* eslint-disable */
  renderProgressButtons(task) {
    switch (task.progressCode) {
      case 0:
        return <RaisedButton secondary style={{ minWidth: 0, minHeight: 0, padding: 0, margin: 0 }} labelStyle={{ padding: '0 10px', fontSize: '0.8em' }} label="Start" onClick={() => this.handleUpdateProgress(task, 1)} />
      case 1:
        return (
          <div>
            <RaisedButton secondary style={{ minWidth: 0, minHeight: 0, padding: 0, margin: 0 }} labelStyle={{ padding: '0 10px ', fontSize: '0.8em' }} label="Done" onClick={() => this.handleUpdateProgress(task, 2)} />
            <p><a href="#" className="backlogButton" onClick={() => this.handleUpdateProgress(task, 0)} > Do Later </a></p>
          </div>
        );
      case 2:
      case 3:
      default:
        return '';
    }
  }
  /* eslint-enable */

  renderTaskItem(task) {
    const taskTitleClass = task.progressCode > 1 ? 'completedTaskTitle' : 'taskTitle';
    const deadlineSpan = task.deadline ? <span className="secondaryTaskInfo"><br />Deadline: {moment(task.deadline).format('D MMM, h:mm a')}</span> : '';
    return (
      <span>
        <span className={taskTitleClass}> {task.title} &nbsp;
          <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
        </span>
        {deadlineSpan}
      </span>
    );
  }
  render() {
    const { task } = this.props;

    if (this.state.editView) {
        return (
          <EditSingleTask
            task={task}
            edittedTitle={this.state.edittedTitle}
            edittedDate={this.state.edittedDate}
            handleChangeTitleEdit={e => this.handleChangeTitleEdit(e)}
            handleChangeDateEdit={newDate => this.handleChangeDateEdit(newDate)}
            handleSaveEdit={() => this.handleSaveEdit(task)}
            handleCancelEdit={() => this.handleCancelEdit(task)}
            removeTask={() => this.removeTask(task)}
          />
        );
      }

    const taskItemClass = task.progressCode > 1 ? 'completedTaskItem' : 'taskItem';
    return (
      <Animated animationIn="fadeInDown" animationOut="fadeOutUp" isVisible>
        <Paper className={taskItemClass}>
          <Row>
          <Col md={9}>
            {this.renderTaskItem(task)}
          </Col>
          <Col md={3}>
            <div className="taskActions pull-right">
              {this.renderProgressButtons(task)}
            </div>
          </Col>
          </Row>
        </Paper>
      </Animated>
    );
  }
}
