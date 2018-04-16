import React, { Component } from 'react';
import { toJS } from 'mobx';
import { observer } from 'mobx-react';
import BigCalendar from 'react-big-calendar';
import {blue500, red500, greenA200} from 'material-ui/styles/colors';
import SvgIcon from 'material-ui/SvgIcon';
import moment from 'moment';
import axios from 'axios';
import swal from 'sweetalert';

import './styles.css';
// import 'react-big-calendar/lib/css/react-big-calendar.css';


import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

moment.locale('en')
BigCalendar.momentLocalizer(moment);

@observer
class ProjectGroupCalendar extends Component {
	constructor() {
	    super()
		    this.state = {
		        openCalendarForm: false,
		        selectedDate: null,
		        openEditForm: false,
		        selectedEvent: null,
		        openCalendarCard: false,
	 			}
	}
	handleCloseAll() {
		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
	}
	editFormOpen() {
		ScheduleItemStore.editFormSuccess = false;
		this.setState({ openEditForm: true, openCalendarCard: false })
	}
	getEventArray(membersScheduleItems) {
		let scheduleItemArr = [];
		let namesArr;
		if (membersScheduleItems && membersScheduleItems.length>0) {
			for (let i = 0; i < membersScheduleItems.length; i++) {
				namesArr = [];
				if (membersScheduleItems[i].itemType === 'meeting' || membersScheduleItems[i].itemType === 'timetable') {
					for(var j=0 ; j<membersScheduleItems[i].assignedTo.length ; j++) {
						namesArr.push(membersScheduleItems[i].assignedTo[j].username);
					}
					scheduleItemArr = scheduleItemArr.concat({
						id: membersScheduleItems[i].id,
						title: namesArr.join(', '),
						allDay: false,
						start: new Date(membersScheduleItems[i].startDate),
						end: new Date(membersScheduleItems[i].endDate),
						description: membersScheduleItems[i].description,
						location: membersScheduleItems[i].location,
						type: membersScheduleItems[i].itemType,
						createdBy: membersScheduleItems[i].createdBy
					})
				} else {
					scheduleItemArr = scheduleItemArr.concat({
						id: membersScheduleItems[i].id,
						title: membersScheduleItems[i].assignedTo[0].username,
						allDay: false,
						start: new Date(membersScheduleItems[i].startDate),
						end: new Date(membersScheduleItems[i].endDate),
						description: membersScheduleItems[i].description,
						location: membersScheduleItems[i].location,
						type: membersScheduleItems[i].itemType,
						createdBy: membersScheduleItems[i].createdBy
					})
				}
			}
		}
		return scheduleItemArr;
	}
	openCalendarForm(selectedSlot) {
		const selectedDate = new Date(selectedSlot.end);
		const today = new Date();
		if (selectedDate < today) {
			console.log('do nothing')
		} else {
			this.setState({
				openCalendarForm: true, selectedDate: selectedDate,
				openEditForm:false, openCalendarCard:false
			})
		}
	}
	eventClicked(event) {
		this.setState({ openCalendarCard: true, selectedEvent: event, openCalendarForm:false, openEditForm: false })
	}
	render() {
		const { eventsArray } = this.props;
		const events = this.getEventArray(eventsArray);
		return (
		    <div>
				  <BigCalendar
				    	events = {events}
				    	selectable
				    	defaultDate = {new Date()}
				    	onSelectSlot = {(slotInfo) => this.openCalendarForm(slotInfo)}
				    	onSelectEvent = {(event)=> this.eventClicked(event) }
				    	views={['month', 'agenda']}
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length={7}
				    />


			</div>

		)
	}
}


export default ProjectGroupCalendar;
