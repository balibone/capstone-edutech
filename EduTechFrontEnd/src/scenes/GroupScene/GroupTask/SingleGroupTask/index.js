import React from 'react';
import { observer } from 'mobx-react';
import { Paper, RaisedButton } from 'material-ui';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import swal from 'sweetalert';
import { Row, Col } from 'react-bootstrap';
import { Animated } from 'react-animated-css';


import EditSingleGroupTask from '../EditSingleGroupTask';
import './styles.css';

moment.locale('en');
momentLocalizer();
@observer
export default class SingleGroupTask extends React.Component {
    state = {
      editView: false,
      edittedTitle: this.props.task.title,
      edittedDate: this.props.task.deadline,
      edittedAssignee: this.props.task.assignedTo,
    }

    removeTask(task) {
      this.props.groupTaskStore.removeTask(task);
    }

    handleChangeTitleEdit(e) {
      this.setState({ edittedTitle: e.target.value });
    }

    handleChangeDateEdit(newDateline) {
      this.setState({ edittedDate: newDateline });
    }

    handleChangeAssigneeEdit(newAssignee) {
      this.setState({ edittedAssignee: [{ username: newAssignee }] });
    }

    handleSaveEdit(task) {
      if (this.state.edittedTitle) {
        task.title = this.state.edittedTitle;
        task.deadline = this.state.edittedDate ? moment(this.state.edittedDate).format('YYYY-MM-DDTHH:mm:ss') : null;
        task.assignedTo = this.state.edittedAssignee;
        this.props.groupTaskStore.editTask(task);
        this.setState({ editView: false });
      } else {
        swal('Ooops!', 'Task name cannot be empty', 'error');
      }
    }

    handleCancelEdit(task) {
      this.setState({
        edittedTitle: task.title,
        editView: false,
      });
    }

    handleUpdateProgress(task, progressCode) {
      this.props.groupTaskStore.updateTaskProgress(task.id, progressCode);
      task.progressCode = progressCode;
    }

    handleVerifyTask(task) {
      this.props.groupTaskStore.verifyTask(task);
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
          return (
            <div>
              <RaisedButton secondary style={{ minWidth: 0, minHeight: 0, padding: 0, margin: 0 }} labelStyle={{ padding: '0 10px ', fontSize: '0.8em' }} label="Verify" onClick={() => this.handleVerifyTask(task)} />
              <p><a href="#" className="backlogButton" onClick={() => this.handleUpdateProgress(task, 0)} > Do Later </a></p>
            </div>
          );
        case 3:
          return <i className="fas fa-check-circle fa-2x" />
        default:
          return '';
      }
    }
    /* eslint-enable */

    renderTaskItem(task) {
      const taskTitleClass = task.progressCode > 1 ? 'completedTaskTitle' : 'taskTitle';
      const progressStyle = task.progressCode > 2 ? { textDecoration: 'line-through' } : {};
      const deadlineSpan = task.deadline ? <span className="secondaryTaskInfo" style={progressStyle}><br />Deadline: {moment(task.deadline).format('D MMM, h:mm a')}</span> : '';
      const assignedToSpan = (task.assignedTo && task.assignedTo.length > 0) ? <span className="secondaryTaskInfo" style={progressStyle}><br />Assigned To: {task.assignedTo[0].username}</span> : '';
      const verifiedSpan = task.verifiedBy ? <span className="secondaryTaskInfo verifiedSignature" style={{ textDecoration: 'none' }}>Verified By {task.verifiedBy.username} {moment(task.verifiedAt).fromNow()}</span>
        : '';
      return (
        <span>
          <span className={taskTitleClass} style={progressStyle}> {task.title} &nbsp;
            <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
          </span>
          {verifiedSpan}
          {assignedToSpan}
          {deadlineSpan}
        </span>
      );
    }

    render() {
      const { task } = this.props;

      if (this.state.editView) {
        return (
          <EditSingleGroupTask
            edittedTitle={this.state.edittedTitle}
            edittedDate={this.state.edittedDate}
            edittedAssignee={this.state.edittedAssignee}
            task={task}
            handleChangeTitleEdit={e => this.handleChangeTitleEdit(e)}
            handleChangeDateEdit={newDate => this.handleChangeDateEdit(newDate)}
            handleChangeAssigneeEdit={newAssignee => this.handleChangeAssigneeEdit(newAssignee)}
            handleSaveEdit={() => this.handleSaveEdit(task)}
            handleCancelEdit={() => this.handleCancelEdit(task)}
            removeTask={() => this.removeTask(task)}
            groupMemberNames={this.props.groupMemberNames}
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
