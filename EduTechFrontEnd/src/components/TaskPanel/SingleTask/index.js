import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import moment from 'moment';
import swal from 'sweetalert';

import EditSingleTask from '../EditSingleTask';
import './styles.css';

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
        task.deadline = this.state.edittedDate;
        this.props.taskStore.editTask(task);
        this.setState({ editView: false });
      } else {
        swal("Ooops!", "Task name cannot be empty", "error")
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
      this.props.taskStore.removeTask(task);
    }
    // End EditSingleTask Operations

    handleUpdateProgress(task, progressCode) {
      this.props.taskStore.updateTaskProgress(task.id, progressCode);
      task.progressCode = progressCode;
    }

    renderProgressButtons(task) {
      switch (task.progressCode) {
        case 0:
          return <i className="fas fa-play" tabIndex={0} role="button" onClick={() => this.handleUpdateProgress(task, 1)} />;
        case 1:
          return (
            <div>
              <i className="fas fa-pause" onClick={() => this.handleUpdateProgress(task, 0)} />&nbsp;
              <i className="fas fa-check" onClick={() => this.handleUpdateProgress(task, 2)} />
            </div>
          );
        case 2:
        case 3:
          return <i className="fas fa-redo" onClick={() => this.handleUpdateProgress(task, 0)} />;
        default:
          return <i className="fas fa-play" onClick={() => this.handleUpdateProgress(task, 1)} />;
      }
    }

    renderTaskItem(task) {
      const { title, progressCode, deadline } = task;
      let progressStyle;
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

      const deadlineSpan = deadline ? <span className="secondaryTaskInfo"><br />Deadline: {moment(deadline).endOf('day').format('Do MMMM YYYY')}</span> : '';

      return (
        <span style={progressStyle}>
          {title}
          {deadlineSpan}
          <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
        </span>
      );
    }

    render() {
      const { task, type } = this.props;

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
            className="taskItem paperDefault"
          />
        );
      }
      return (
        <Paper className="taskItem paperDefault">
          {this.renderTaskItem(task, type)}
          <div className="pull-right taskActions">
            {this.renderProgressButtons(task)}
          </div>
        </Paper>
      );
    }
}
