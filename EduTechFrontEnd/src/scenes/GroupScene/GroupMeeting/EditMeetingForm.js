import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import moment from 'moment';
import {observer} from 'mobx-react';
import { Tabs, Tab, Col, Row, Button, FormControl, ControlLabel, FormGroup } from 'react-bootstrap';
import { DateTimePicker, DropdownList } from 'react-widgets';
import momentLocalizer from 'react-widgets-moment';

import MeetingStore from '../../../stores/MeetingStore/MeetingStore';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

moment.locale('en');
momentLocalizer();

@observer
class EditMeetingForm extends Component {

	constructor(props){
		super(props)
		let { title, description, location, startDate, endDate, createdBy, groupId } = this.props.meeting;
		this.state={
			title: title,
			location: location,
			description: description,
			startDate: new Date(startDate),
			endDate: new Date(endDate),
			createdBy: createdBy,
			groupId: groupId
		}
	}

	closeEditForm(){
		this.props.closeEditForm();
	}

	editMeeting(event){
		event.preventDefault();
		var itemId = this.props.meeting.id;
		var createdBy = this.props.meeting.createdBy;
		var { title, description, location, startDate, endDate, groupId } = this.state;

		startDate = moment(startDate).format();
		endDate = moment(endDate).format();
		console.log("Edit meeting Start: ", startDate);
		console.log("Edit meeting End: ", endDate)
		console.log("GROUP ID" ,groupId)

		MeetingStore.updateMeeting(itemId, title, description, startDate, endDate, location, createdBy, groupId);

	}

	render(){
		if(MeetingStore.editFormSuccess){
			console.log("EDIT MEETING SUCCESS")
			this.props.closeEditForm();
		}
		return(
			<div className="paperDefault">
				<h4>Edit Meeting</h4>
	           <div className="row mt-1">
	            <label htmlFor="eventName" className="col-2 col-form-label">Meeting Title:</label>
	            <div className="col-8">
	              <input className="form-control" type="text" id="eventName" ref="name" 
	              	value={this.state.title}
	                onChange={(e)=>this.setState({title: e.target.value})}
	              />
	            </div>
	          </div>

	          <div className="row mt-1">
	            <label htmlFor="location" className="col-2 col-form-label">Location:</label>
	            <div className="col-8">
	              <input className="form-control" type="text" id="location" ref="location"
	              value={this.state.location} 
	              onChange={(e)=>this.setState({location: e.target.value})}
	              />
	            </div>
	          </div>

	          <div className="row mt-1">
	            <label htmlFor="comment">Description:</label>
	            <textarea className="form-control" rows="5" id="comment" ref="description"
	            value={this.state.description}
	            onChange={(e)=>this.setState({description: e.target.value})}
	            >
	            </textarea>
	          </div>

	        <Row className="smallTopGap">
	          <Col md={6}>
	            <DateTimePicker
	              min={new Date()}
	              placeholder="Set Start Time"
	              onChange={value => this.setState({ startDate: new Date(value) })}
	              value={this.state.startDate}
	            />
	          </Col>
	          <Col md={6}>
	            <DateTimePicker
	              min={new Date()}
	              placeholder="Set End Time"
	              onChange={value => this.setState({ endDate: new Date(value) })}
	              value={this.state.endDate}
	            />
	          </Col>
	        </Row>
	        
	        <Row className="smallTopGap">
	          <Col md={12}>
	            <Button bsStyle="primary" onClick={this.editMeeting.bind(this)}>
	              Submit
	            </Button>
	            <Button bsStyle="primary" className="pull-right" onClick={this.closeEditForm.bind(this)}>
	              Close
	            </Button>
	          </Col>
	        </Row>
	      </div>
		)
	}
}

export default EditMeetingForm;