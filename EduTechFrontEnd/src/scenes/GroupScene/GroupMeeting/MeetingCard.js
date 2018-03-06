import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import moment from 'moment';
import axios from 'axios';
import {observer} from 'mobx-react';
import swal from 'sweetalert';
import momentLocalizer from 'react-widgets-moment';

import MeetingStore from '../../../stores/MeetingStore/MeetingStore';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

import EditMeetingForm from './EditMeetingForm';

moment.locale('en');
momentLocalizer();

@observer
class MeetingCard extends Component {

	constructor(){
		super()
		this.state = {
			showEditForm: false
		}
	}

	removeMeeting(){
		var meetingId = this.props.meeting.id;
		var groupId = this.props.groupId;
		MeetingStore.removeMeeting(meetingId, groupId);
	}

	closeEditForm(){
		this.setState({showEditForm: false})
	}

	openEditForm(){
		console.log("OPEN EDIT FORM")
		MeetingStore.editFormSuccess = false;
		this.setState({showEditForm: true})
	}

	render(){
		console.log("meeting in meeting card", this.props.meeting)
		let { title, description, startDate, endDate, createdBy } = this.props.meeting;
      	const start = moment(startDate).format("h:mm a");
        const end = moment(endDate).format("h:mm a");
        const date = moment(startDate).format("dddd, Do MMMM");

		return(
			<div>
			  <Card className="standardTopGap">
			    <CardTitle title={title} subtitle={` ${date}, ${start} - ${end}`} />
			    <CardText>
			      {description}
			    </CardText>
			    <CardActions>
			      <FlatButton label="Add Minutes" />
			      <FlatButton label="Edit Meeting" onClick={this.openEditForm.bind(this)}/>
			      <FlatButton label="Remove Meeting" onClick={this.removeMeeting.bind(this)}/>
			    </CardActions>
			  </Card>
			  {
			  	this.state.showEditForm ? <EditMeetingForm meeting={this.props.meeting} closeEditForm={this.closeEditForm.bind(this)}/>: <span></span>
			  }
			</div>
		)
	}
}

export default MeetingCard;