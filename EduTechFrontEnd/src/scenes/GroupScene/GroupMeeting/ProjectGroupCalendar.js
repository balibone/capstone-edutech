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

import SingleCalendarCard from '../../PersonalScene/MainCalendar/SingleCalendarCard';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

moment.locale('en')
BigCalendar.momentLocalizer(moment);

@observer
class ProjectGroupCalendar extends Component {

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
		if(selectedDate < today){

		} else{
			this.setState({openCalendarForm: true, selectedDate: selectedDate, openEditForm:false, openCalendarCard:false})
		}
	}

	handleCloseAll(){
		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
	}

	editFormOpen(){
		ScheduleItemStore.editFormSuccess = false;
		this.setState({openEditForm: true, openCalendarCard: false})
	}

	getEventArray(membersScheduleItems){
		var scheduleItemArr = [];
		
		console.log("SCHEDULE ITEMS: ", membersScheduleItems)
		if(membersScheduleItems && membersScheduleItems.length>0){
			for(var i=0 ; i<membersScheduleItems.length ; i++){
				var namesArr =[];
				if(membersScheduleItems[i].type === "meeting" || membersScheduleItems[i].type === "timetable"){
					for(var j=0 ; j<membersScheduleItems[i].assignedTo.length ; j++){
						namesArr.push(membersScheduleItems[i].assignedTo[j].username);
					}

					scheduleItemArr = scheduleItemArr.concat({
						id: membersScheduleItems[i].id,
						title: namesArr.join(", "), 
						allDay: false, 
						start: new Date(membersScheduleItems[i].startDate), 
						end: new Date(membersScheduleItems[i].endDate), 
						description: membersScheduleItems[i].description,
						location: membersScheduleItems[i].location,
						type: membersScheduleItems[i].type,
						createdBy: membersScheduleItems[i].createdBy
					})
				} else{
					scheduleItemArr = scheduleItemArr.concat({
						id: membersScheduleItems[i].id,
						title: membersScheduleItems[i].assignedTo[0].username, 
						allDay: false, 
						start: new Date(membersScheduleItems[i].startDate), 
						end: new Date(membersScheduleItems[i].endDate), 
						description: membersScheduleItems[i].description,
						location: membersScheduleItems[i].location,
						type: membersScheduleItems[i].type,
						createdBy: membersScheduleItems[i].createdBy
					})
				}

			}
		}


		return scheduleItemArr;
	}

	render(){
		let eventsArray = this.props.eventsArray;
		// eventsArray = eventsArray.filter(event => event.type === "meeting");
		let events = this.getEventArray(eventsArray);

		return(
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
				  
				  {
				  	this.state.openCalendarCard ? <SingleCalendarCard 
					  	selectedEvent={this.state.selectedEvent} 
					  	handleCloseAll={this.handleCloseAll.bind(this)} 
					  	editFormOpen={this.editFormOpen.bind(this)}
				  	/> : (<span></span>)
				  }
			</div>  
			
		)
	}
}


export default ProjectGroupCalendar;