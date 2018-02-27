import React from 'react';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import moment from 'moment';

import EditSingleGroupTask from '../EditSingleGroupTask';
import './styles.css';

@observer
export default class SingleGroupTask extends React.Component {
    state = {
      editView: false,
      edittedTitle: this.props.task.title,
      edittedDate: this.props.task.deadline,
      edittedAssignee: this.props.task.assignee,
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
      this.setState({ edittedAssignee: newAssignee });
    }

    handleSaveEdit(task) {
      task.title = this.state.edittedTitle;
      task.deadline = this.state.edittedDate;
      task.assignee = this.state.edittedAssignee;
      this.setState({ editView: false });
    }

    handleCancelEdit(task) {
      this.setState({
        edittedTitle: task.title,
        editView: false,
      });
    }

    renderProgressButtons(task) {
      switch (task.statusCode) {
        case 0:
          return <i className="fas fa-play" onClick={() => task.statusCode = 1} />;
        case 1:
          return (
            <div>
              <i className="fas fa-pause" onClick={() => task.statusCode = 0} />&nbsp;
              <i className="fas fa-check" onClick={() => task.statusCode = 2} />
            </div>
          );
        case 2:
          return (
            <div>
              <i className="fas fa-redo" onClick={() => task.statusCode = 0} /> &nbsp;
              <i className="fas fa-thumbs-up" onClick={() => task.statusCode = 3} />
            </div>
          );
        case 3:
          return <i className="fas fa-redo" onClick={() => task.statusCode = 0} />;
        default:
          return <i className="fas fa-play" onClick={() => task.statusCode = 1} />;
      }
    }

    renderTaskItem(task) {
      const {
        title, statusCode, assignee, deadline,
      } = task;
      let progressStyle = {};
      switch (statusCode) {
        case 1:
          progressStyle = { fontStyle: 'italic' };
          break;
        case 3:
          progressStyle = { textDecoration: 'line-through' };
          break;
        default:
          progressStyle = {};
      }

      const assigneeSpan = assignee ? <span className="secondaryTaskInfo"><br />Assigned To: {assignee}</span>
        : '';
      const deadlineSpan = deadline ? <span className="secondaryTaskInfo"><br />Deadline: {moment(deadline).format()}.</span>
        : '';

      return (
        <span style={progressStyle}>
          {title}
          {assigneeSpan}
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
