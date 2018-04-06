import React, {Component} from 'react';
import moment from 'moment';
import {PageHeader, Button, FormGroup, ControlLabel, FormControl, ListGroupItem, ListGroup} from 'react-bootstrap';
import swal from 'sweetalert';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

function FieldGroup({ id, label, ...props }) {
  return (
    <FormGroup controlId={id}>
      <ControlLabel>{label}</ControlLabel>
      <FormControl {...props} />
    </FormGroup>
  );
}

class IndividualSubmission extends Component {

	constructor(){
		super()
		this.state = {
			file: null
		}
	}

	renderSubmissions(assignment){
		if(assignment.submissions.length > 0){
			return assignment.submissions.map((item) => (
				<ListGroupItem bsStyle="warning">
					{item.fileName}
				</ListGroupItem>
			))
		}else {
			return (<h4 style={{textColor: 'blue'}}>You have no submission.</h4>)
		}
	}

	onSubmit(event){
		event.preventDefault();
		const {id} = this.props.assignment;
		const selectedFile = this.state.file;
		console.log("selectedFile", selectedFile)
		const username = localStorage.getItem('username');
		const title = ""
		if(selectedFile && selectedFile.size>10000000){
			swal("File Size Error!", "Your file size is more than 10MB.", "error");
		}else if(selectedFile){
			AssignmentStore.submitAssignment(id, selectedFile, username, title);
		}



	}

	handleChange(e) {
    	this.setState({file:e.target.files[0]})
  	}

	render(){
		const {assignment} = this.props;
		const deadlineDate = moment(assignment.closeDate).format('dddd, Do MMMM');
		const deadlineTime = moment(assignment.closeDate).format('h:mm a');
		return(
			<div>
				<h3 style={{fontWeight: '150'}}>
				  Deadline: {deadlineDate}, {deadlineTime}
				</h3>

				<form onSubmit={this.onSubmit.bind(this)}>
			        <FormGroup>
			          	<FieldGroup
					      id="formControlsFile"
					      type="file"
					      label="File"
					      onChange={this.handleChange.bind(this)}
					    />
			          	
						<Button className="standardTopGap" type="submit" bsStyle="primary" block>Upload Attachment</Button>
			        </FormGroup>
			    </form>
			    <ListGroup>
			    	{this.renderSubmissions(assignment)}
				</ListGroup>
			</div>
		)
	}
}

export default IndividualSubmission;