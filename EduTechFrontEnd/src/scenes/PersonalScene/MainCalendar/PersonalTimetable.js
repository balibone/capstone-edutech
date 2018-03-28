import React, { Component } from 'react';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';

import SingleCalendarCard from './SingleCalendarCard';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

BigCalendar.momentLocalizer(moment);

@observer
class PersonalTimetable extends Component {

	constructor(){
		super()
		this.state = {
			datepicked: null,
	        openCalendarForm: false,
	        selectedDate: null,
	        openEditForm: false,
	        selectedEvent: null,
	        openCalendarCard: false
		}
	}

	eventClicked(event){
		this.setState({openCalendarCard: true, selectedEvent: event, openCalendarForm:false, openEditForm: false})
	}

	handleCloseAll(){
		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
	}

	eventStyleGetter(event, start, end, isSelected) {
    	var backgroundColor = '#' + event.hexColor;
    	var style = {
	        backgroundColor: backgroundColor,
	        borderRadius: '0px',
	        opacity: 0.8,
	        color: 'black',
	        border: '0px',
	        display: 'block'
    	};
    	return { style: style };
	}

	render(){
		let today = moment();
		let am6 = today.set('hour', 6).set('minutes', 0).toDate();
		var eventsArray = this.props.eventsArray.filter(event => event.type == "timetable");

		return(
			<div>
				 <BigCalendar 
				    	events = {eventsArray}
						step={60}
				    	defaultDate = {new Date()}
				    	min={am6}
				    	length={7}
				    	onSelectEvent = {(event)=> this.eventClicked(event) }
				    	views={['work_week', 'month']}
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length = {7}
				    	eventPropGetter = {this.eventStyleGetter}
				    	
				    />  
				{
				  	this.state.openCalendarCard ? <SingleCalendarCard 
					  	selectedEvent={this.state.selectedEvent} 
					  	handleCloseAll={this.handleCloseAll.bind(this)} 
				  	/> : (<span></span>)
				}
			</div>
		)
	}
}

export default PersonalTimetable;