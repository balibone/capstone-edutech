import React, { Component } from 'react';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import { Dialog } from 'material-ui';
import { Modal, Button, Row, Col, ControlLabel, FormControl } from 'react-bootstrap';
import Autosuggest from 'react-bootstrap-autosuggest';
import { DateTimePicker } from 'react-widgets';
import moment from 'moment';
import axios from 'axios';
import {observer} from 'mobx-react';
import swal from 'sweetalert';
import momentLocalizer from 'react-widgets-moment';

import MeetingStore from '../../../stores/MeetingStore/MeetingStore';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import GroupScheduleItemStore from '../../../stores/ScheduleItemStore/GroupScheduleItemStore';

moment.locale('en');
momentLocalizer();

@observer
class MeetingCard extends Component {
	state = {
		openEditForm: false,
		edittedTitle: '',
		edittedDescription: '',
		edittedLocation: '',
		edittedStart: null,
		edittedEnd: null,
	}
	componentWillMount() {
		let { title, description, startDate, endDate, location, createdBy, type, id } = this.props.meeting;
		this.setState({
      edittedTitle: title,
      edittedDescription: description,
      edittedLocation: location,
      edittedStart: new Date(startDate),
      edittedEnd: new Date(endDate),
    });
  }
  getActions(type, handleCloseDialog, eventId) {
    if (this.state.openEditForm) {
      const actions = [<FlatButton label="Cancel" primary onClick={() => this.handleCancelEdit()} />];
      actions.push(<FlatButton label="Save" primary onClick={() => this.handleSaveEdit(eventId)} />);
      return actions;
    }
    const actions = [<FlatButton label="Close" primary onClick={handleCloseDialog} />];
    if (type === 'personal') {
      actions.push(<FlatButton label="Remove" primary onClick={() => this.handleRemoveScheduleItem(eventId, handleCloseDialog)} />);
      actions.push(<FlatButton label="Edit" primary onClick={() => this.setState({ openEditForm: true })} />);
    }
    return actions;
  }
  handleCancelEdit() {
		let { title, description, startDate, endDate, location, createdBy, type, id } = this.props.meeting;
    this.setState({
      edittedTitle: title,
      edittedDescription: description,
      edittedLocation: location,
      edittedStart: startDate,
      edittedEnd: endDate,
      openEditForm: false,
    });
  }
  handleSaveEdit(eventId) {
    const {
      edittedTitle, edittedDescription, edittedStart, edittedEnd, edittedLocation,
    } = this.state;
    GroupScheduleItemStore.updateScheduleItem(
        eventId, edittedTitle, edittedDescription,
        edittedStart, edittedEnd, edittedLocation,
      );
    this.setState({ openEditForm: false });
  }
  handleRemoveScheduleItem(eventId, handleCloseDialog) {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this item!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        ScheduleItemStore.removeScheduleItem(eventId);
        handleCloseDialog();
      }
    });
  }
	removeMeeting() {
		const meetingId = this.props.meeting.id;
		const { groupId } = this.props.groupId;
		swal({
		  title: 'Are you sure?',
		  text: 'You will not be able to recover this item!',
		  icon: 'warning',
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
  			GroupScheduleItemStore.removeScheduleItem(meetingId, groupId);
		  }
		});
	}
	openMinutePageFunc() {
		MeetingStore.getSelectedMeeting(this.props.meeting);
		this.props.openMinutePage();
	}
	handleCloseCalendarForm() {
		const { title, description, location, startDate, endDate, createdBy, type, id } = this.props.meeting;
		this.setState({
			edittedTitle: title,
			edittedDescription: description,
			edittedLocation: location,
			edittedStart: startDate,
			edittedEnd: endDate,
			openEditForm: false,
		});
	}
	renderEditMeetingDialog() {
	const { title, description, startDate, endDate, createdBy, itemType, id } = this.props.meeting;
	return (
		<Dialog
        actions={this.getActions(itemType, this.handleCloseDialog, id)}
        modal={false}
        open={this.state.openEditForm}
        onRequestClose={this.handleCloseDialog}
      >
			<div>
					<Row className="calendarDateFormField">
						<Col md={6}>
							<ControlLabel>From</ControlLabel>
							<DateTimePicker
								defaultValue={this.state.edittedStart}
								onChange={newStart => this.setState({ edittedStart: newStart })}
							/>
						</Col>
						<Col md={6}>
							<ControlLabel>To</ControlLabel>
							<DateTimePicker
								defaultValue={this.state.edittedEnd}
								onChange={newEnd => this.setState({ edittedEnd: newEnd })}
							/>
						</Col>
					</Row>
					<Row className="calendarFormField">
						<Col md={12}>
								<ControlLabel>Location</ControlLabel>
								<Autosuggest
									value={this.state.edittedLocation}
									datalist={JSON.parse(localStorage.getItem('NUSVenues'))}
									placeholder="Enter schedule location"
									onChange={venue => this.setState({ edittedLocation: venue })}
								/>
						</Col>
					</Row>
					<Row className="calendarFormField">
						<Col md={12}>
								<ControlLabel>Title</ControlLabel>
								<FormControl
									type="text"
									value={this.state.edittedTitle}
									placeholder="Enter schedule title"
									onChange={e => this.setState({ edittedTitle: e.target.value })}
								/>
						</Col>
					</Row>
					<Row className="calendarFormField">
						<Col md={12}>
								<ControlLabel>Description</ControlLabel>
								<FormControl
									type="text"
									value={this.state.edittedDescription}
									placeholder="Enter schedule description"
									onChange={e => this.setState({ edittedDescription: e.target.value })}
								/>
						</Col>
					</Row>
			</div>
		</Dialog>
	);
}

	render() {
		const { title, description, startDate, endDate } = this.props.meeting;
      	const start = moment(startDate).format("h:mm a");
        const end = moment(endDate).format("h:mm a");
        const date = moment(startDate).format("dddd, Do MMMM");
		return (
			<div>
			  <Card className="standardTopGap">
			    <CardTitle title={title} subtitle={` ${date}, ${start} - ${end}`} />
			    <CardText>
			      {description}
			    </CardText>
			    <CardActions>
			    	<FlatButton label="View Minutes" onClick={() => this.openMinutePageFunc()} />
			      	<FlatButton label="Edit Meeting" onClick={() => this.setState({ openEditForm: true })} />
			      	<FlatButton label="Remove Meeting" onClick={this.removeMeeting.bind(this)}/>
			    </CardActions>
			  </Card>
				{ this.renderEditMeetingDialog() }
			</div>
		)
	}
}

export default MeetingCard;
