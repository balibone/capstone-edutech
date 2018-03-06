import React from 'react';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import swal from 'sweetalert';

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
      if(this.state.edittedTitle) {
        task.title = this.state.edittedTitle;
        task.deadline = this.state.edittedDate;
        task.assignedTo = this.state.edittedAssignee;
        this.props.groupTaskStore.editTask(task);
        this.setState({ editView: false });
      } else {
        swal("Ooops!", "Task name cannot be empty", "error");
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

    renderProgressButtons(task) {
      switch (task.progressCode) {
        case 0:
          return <i className="fas fa-play" onClick={() => this.handleUpdateProgress(task, 1)} />;
        case 1:
          return (
            <div>
              <i className="fas fa-pause" onClick={() => this.handleUpdateProgress(task, 0)} />&nbsp;
              <i className="fas fa-check" onClick={() => this.handleUpdateProgress(task, 2)} />
            </div>
          );
        case 2:
          return (
            <div>
              <i className="fas fa-redo" onClick={() => this.handleUpdateProgress(task, 0)} /> &nbsp;
              <i className="fas fa-thumbs-up" onClick={() => this.handleVerifyTask(task)} />
            </div>
          );
        case 3:
          return <i className="fas fa-redo" onClick={() => this.handleUpdateProgress(task, 0)} />;
        default:
          return <i className="fas fa-play" onClick={() => this.handleUpdateProgress(task, 1)} />;
      }
    }

    renderTaskItem(task) {
      const {
        title, progressCode, assignedTo, deadline, verifiedBy, verifiedAt
      } = task;
      let progressStyle = {};
      switch (progressCode) {
        case 1:
          progressStyle = { fontStyle: 'italic' };
          break;
        case 3:
          progressStyle = { textDecoration: 'line-through' };
          break;
        default:
          progressStyle = {};
      }

      const assignedToSpan = (assignedTo && assignedTo.length > 0)  ? <span className="secondaryTaskInfo"  style={progressStyle}><br />Assigned To: {assignedTo[0].username}</span>
        : '';
      const deadlineSpan = deadline ? <span className="secondaryTaskInfo" style={progressStyle}><br />Deadline: {moment(deadline).format('Do MMMM YYYY')}</span>
        : '';
      const verifiedSpan = verifiedBy ? <span className="secondaryTaskInfo verifiedSignature" style={{ textDecoration: 'none' }}>Verified By {verifiedBy.username} {moment(verifiedAt).fromNow()}</span>
        : '';

      return (
        <span>
          <span style={progressStyle}>{title}</span> &nbsp; {verifiedSpan}
          {assignedToSpan}
          {deadlineSpan}
          <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
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

      return (
        <Paper className="taskItem paperDefault">
          {this.renderTaskItem(task)}
          <div className="pull-right taskActions">
            {this.renderProgressButtons(task)}
          </div>
        </Paper>
      );
    }
}
