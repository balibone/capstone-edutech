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

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

moment.locale('cs')
BigCalendar.momentLocalizer(moment);

@observer
class PersonalCalendar extends Component {

	constructor(){
	    super()

		    this.state={
		        datepicked: null,
		        openCalendarForm: false,
		        selectedDate: null,
		        openEditForm: false,
		        selectedEvent: null,
		        openCalendarCard: false
	          }
	}

	eventClicked(event){
		console.log("EVENT: ", event)
		this.setState({openCalendarCard: true, selectedEvent: event, openCalendarForm:false, openEditForm: false})
	}

	openCalendarForm(selectedSlot){
		var selectedDate = selectedSlot.end;
		this.setState({openCalendarForm: true, selectedDate: selectedDate, openEditForm:false})
	}

	handleCloseAll(){
		console.log("close all")
		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
	}

	editFormOpen(){
		this.setState({openEditForm: true, openCalendarCard: false})
	}

	// getEventArray(){
	// 	let scheduleItemsObjArr = ScheduleItemStore.scheduleItems;
	// 	var scheduleItemTempArr= toJS(scheduleItemsObjArr);
	// 	var scheduleItemArr = [];
	// 	console.log("SCHEDULE ITEMS: ", scheduleItemTempArr)
	// 	if(scheduleItemTempArr && scheduleItemTempArr.length>0){
	// 		for(var i=0 ; i<scheduleItemTempArr.length ; i++){
	// 			scheduleItemArr = scheduleItemArr.concat({
	// 				id: scheduleItemTempArr[i]._id,
	// 				title: scheduleItemTempArr[i].name, 
	// 				allDay: false, 
	// 				start: scheduleItemTempArr[i].startTime, 
	// 				end: scheduleItemTempArr[i].endTime, 
	// 				description: scheduleItemTempArr[i].description,
	// 				location: scheduleItemTempArr[i].location,
	// 				isRecurring: scheduleItemTempArr[i].isRecurring,
	// 				createdBy: scheduleItemTempArr[i].createdBy
	// 			})
	// 		}
	// 	}
	// 	return scheduleItemArr;
	// }

	render(){

		// var eventsArray = this.props.eventsArray.filter(event => event.type != "timetable");

		return(
		    <div>
					
				  <BigCalendar 
				    	events = {this.props.eventsArray}
				    	selectable
				    	defaultDate = {new Date()}
				    	onSelectSlot = {(slotInfo) => this.openCalendarForm(slotInfo)}
				    	onSelectEvent = {(event)=> this.eventClicked(event) }
				    	// views={['week']}
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length = {7}
				    />  
				  
				  {
				  	this.state.openCalendarForm ? <AddCalendarItemForm scheduleItemStore={ScheduleItemStore} selectedDate={this.state.selectedDate} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>
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