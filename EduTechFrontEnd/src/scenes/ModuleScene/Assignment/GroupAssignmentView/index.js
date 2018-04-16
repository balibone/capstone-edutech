import React, { Component } from 'react';
import { Button, FormGroup, ListGroup, ListGroupItem, ControlLabel, FormControl } from 'react-bootstrap';
import moment from 'moment';
import swal from 'sweetalert';
import _ from 'lodash';

import AssignmentStore from '../../../../stores/ModuleStore/AssignmentStore';

class GroupAssignmentView extends Component {

  constructor() {
    super()
    this.state = {
      file: null,
    }
  }
  onSubmit() {
    // event.preventDefault();
    const { file } = this.state;
    const username = localStorage.getItem('username');
    const { id } = this.props.assignment;
    const title = '';
    if (file && file.size > 10000000) {
      swal('File Size Error!', 'Your file size is more than 10MB.', 'error');
    } else if (file === null) {
      swal('Warning!', 'Please choose the file first.', 'error');
    } else {
      AssignmentStore.submitAssignment(title, file, username, id);
      this.setState({ file: null })
    }
  }
  handleChange(e) {
    console.log("target files: ", e.target.files)
    this.setState({ file: e.target.files[0] })
  }
  downloadFile(attachmentId, fileName) {
    const { id } = this.props.assignment;
    AssignmentStore.downloadAssignment(id, attachmentId, fileName);
  }
  renderDownloadBtn(attachmentId, fileName) {
    console.log('attarchment id', attachmentId)
    return (<Button bsStyle="primary" bsSize="small" className="pull-right" onClick={() => this.downloadFile(attachmentId, fileName)}>Download</Button>)
  }
  renderFileName(fileName) {
    if (fileName.includes('qup')) {
      const newFileName = _.split(fileName, 'qup', 2)
      console.log('newFileName', newFileName[1]);
      return newFileName[1];
    }
    return fileName;
  }
  renderSubmissions(assignment) {
    if (assignment.submissions.length > 0) {
      return assignment.submissions.map(item => (
        <ListGroupItem bsStyle="warning">
          {this.renderFileName(item.fileName)}
          {this.renderDownloadBtn(item.id, item.fileName)}
          <p>Submitted By: {item.createdBy.username} at {moment(item.createdAt).format('DD/MM/YY hh:mm')}</p>
        </ListGroupItem>
      ))
    }
    return (<h4 style={{ textColor: 'blue' }}>You have no submission.</h4>)
  }
  renderStudentListView(assignment) {
    const { members } = assignment.module;
    if (members) {
      return members.map((member) => {
          if (member.userType === 'student') {
            return (<li>{member.username}</li>)
          }
          return '';
        }
       );
    }
    return '';
  }
  renderSubmitArea() {
    const userType = localStorage.getItem('userType');
    if (userType === 'student') {
      return (
        <form className="standardTopGap">
        <input type="file" onChange={this.handleChange.bind(this)} size="1000" />
        <Button className="standardTopGap" bsStyle="primary" block onClick={() => this.onSubmit()}>Submit Assignment</Button>
        </form>
      )
    }
    return '';
  }
  render() {
    const { assignment } = this.props;
		const deadlineDate = moment(assignment.closeDate).format('dddd, Do MMMM');
		const deadlineTime = moment(assignment.closeDate).format('h:mm a');
    return (
        <div>
				<h3 style={{ fontWeight: '150' }}>
				  Deadline: {deadlineDate}, {deadlineTime}
				</h3>
          {this.renderSubmitArea()}
			    <ListGroup className="standardTopGap">
			    	{this.renderSubmissions(assignment)}
  	    </ListGroup>
        </div>
    );
  }
}

export default GroupAssignmentView;
