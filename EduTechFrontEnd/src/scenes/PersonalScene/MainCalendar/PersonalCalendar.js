import React, {Component} from 'react';
// import { Paper } from 'material-ui';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import BigCalendar from 'react-big-calendar';
import {blue500, red500, greenA200} from 'material-ui/styles/colors';
import SvgIcon from 'material-ui/SvgIcon';
import moment from 'moment';
import axios from 'axios';
import swal from 'sweetalert';

import './styles.css';
import 'react-big-calendar/lib/css/react-big-calendar.css';

import SingleCalendarCard from './SingleCalendarCard';
import AddCalendarItemForm from './AddCalendarItemForm';
import EditCalendarItemForm from './EditCalendarItemForm';
import AddCalendarItemModal from './AddCalendarItemModal';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

moment.locale('en')
BigCalendar.momentLocalizer(moment);

@observer
class PersonalCalendar extends Component {

	constructor(){
	    super()

		    this.state={
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

	openCalendarForm(selectedSlot){
		var selectedDate = new Date(selectedSlot.end);
		var today = new Date();
		if(selectedDate > today){
			this.setState({openCalendarForm: true, selectedDate: selectedDate, openEditForm:false, openCalendarCard:false})
		}
	}

	handleCloseAll(){
		console.log("HANDLING CLOSE ALL")
		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
	}

	editFormOpen(){
		ScheduleItemStore.editFormSuccess = false;
		this.setState({openEditForm: true, openCalendarCard: false})
	}

	eventStyleGetter(event, start, end, isSelected) {
    	var backgroundColor = '#' + event.hexColor;
    	var style = {
	        backgroundColor: backgroundColor,
	        opacity: 0.8,
	        color: 'black',
	        display: 'block'
    	};
    	return { style: style };
	}

	handleCloseModal(){
		this.setState({openCalendarForm: false})
	}

	render(){
		let eventsArray = this.props.eventsArray;

		let formats = {
		    timeGutterFormat: 'HH:mm',
		    eventTimeRangeFormat: ({
		        start,
		        end
		      }, culture, local) =>
		      local.format(start, 'HH:mm', culture) + '-' +
		      local.format(end, 'HH:mm', culture),
		    dayFormat: 'DD-MM' + ' ' + 'dd',
		    agendaTimeRangeFormat: ({
		        start,
		        end
		      }, culture, local) =>
		      local.format(start, 'HH:mm', culture) + '-' +
		      local.format(end, 'HH:mm', culture),
		    agendaDateFormat: 'DD-MM' + ' ' + 'dd',

		  }

		// eventsArray = eventsArray.filter(event => event.type === "personal" || event.type === "meeting");
		return(
		    <div>
				  <BigCalendar 
				    	events = {eventsArray}
				    	selectable
				    	defaultDate = {new Date()}
				    	onSelectSlot = {(slotInfo) => this.openCalendarForm(slotInfo)}
				    	onSelectEvent = {(event)=> this.eventClicked(event) }
				    	views={['month', 'agenda']}
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length = {7}
				    	eventPropGetter = {this.eventStyleGetter}
				    	formats = {formats}
		
				    />  
				  
				  {
				  	// this.state.openCalendarForm ? <AddCalendarItemForm scheduleItemStore={ScheduleItemStore} selectedDate={this.state.selectedDate} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>
				  	this.state.openCalendarForm ? <AddCalendarItemModal 
				  		scheduleItemStore={ScheduleItemStore} 
				  		show={this.state.openCalendarForm} 
				  		selectedDate={this.state.selectedDate} 
				  		handleCloseModal={this.handleCloseModal.bind(this)}/> : <span></span>
				  }
				  {
				  	this.state.openCalendarCard ? <SingleCalendarCard
					  	selectedEvent={this.state.selectedEvent}
					  	handleCloseAll={this.handleCloseAll.bind(this)}
					  	editFormOpen={this.editFormOpen.bind(this)}
				  	/> : (<span></span>)
				  }
				  {
				  	this.state.openEditForm ? <EditCalendarItemForm scheduleItemStore={ScheduleItemStore} event={this.state.selectedEvent} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>
				  }
			</div>

		)
	}
}


export default PersonalCalendar;
