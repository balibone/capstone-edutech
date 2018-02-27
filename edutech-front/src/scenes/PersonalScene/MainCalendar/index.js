import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {Tabs, Tab} from 'material-ui/Tabs';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import moment from 'moment';

import PersonalCalendar from './PersonalCalendar';
import PersonalTimetable from './PersonalTimetable';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

const styles = {
  headline: {
    fontSize: 24,
    marginBottom: 12,
    fontWeight: 400
  },
};

@observer
class MainCalendar extends Component {

	getEventArray(){
		let scheduleItemsObjArr = ScheduleItemStore.scheduleItems;
		var scheduleItemTempArr= toJS(scheduleItemsObjArr);
		var scheduleItemArr = [];
		console.log("SCHEDULE ITEMS: ", scheduleItemTempArr)
		if(scheduleItemTempArr && scheduleItemTempArr.length>0){
			for(var i=0 ; i<scheduleItemTempArr.length ; i++){
				scheduleItemArr = scheduleItemArr.concat({
					id: scheduleItemTempArr[i].id,
					title: scheduleItemTempArr[i].title, 
					allDay: false, 
					start: scheduleItemTempArr[i].startDate, 
					end: scheduleItemTempArr[i].endDate, 
					description: scheduleItemTempArr[i].description,
					location: scheduleItemTempArr[i].location,
					type: scheduleItemTempArr[i].type,
					createdBy: scheduleItemTempArr[i].createdBy
				})
			}
		}
		return scheduleItemArr;
	}


	render(){
		let eventsArray = this.getEventArray();
		console.log("EVENTS", eventsArray)

		return(
			<Paper className="standardTopGap standardBottomGap paperDefault">
			  <Tabs>
			  	<Tab label="Personal Calendar" style={{background: 'cadetblue'}}>
			      <PersonalCalendar eventsArray={eventsArray} />
			    </Tab>
			    <Tab label="Personal Timetable" style={{background: 'cadetblue'}} >
			      <div>
			        <PersonalTimetable eventsArray={eventsArray} />
			      </div>
			    </Tab>
			  </Tabs>
		  </Paper>
		)
	}
}

// const MainCalendar = () => (
//   <Paper className="standardTopGap standardBottomGap paperDefault">
// 	  <Tabs>
// 	  	<Tab label="Personal Calendar" style={{background: 'cadetblue'}}>
// 	      <PersonalCalendar />
// 	    </Tab>
// 	    <Tab label="Personal Timetable" style={{background: 'cadetblue'}} >
// 	      <div>
// 	        <h2 style={styles.headline}>Tab Two</h2>
// 	        <PersonalTimetable />
// 	      </div>
// 	    </Tab>
// 	  </Tabs>
//   </Paper>
// );


// BigCalendar.momentLocalizer(moment);

// @observer
// class MainCalendar extends Component {

// 	constructor(){
// 	    super()

// 		    this.state={
// 		        datepicked: null,
// 		        openCalendarForm: false,
// 		        selectedDate: null,
// 		        openEditForm: false,
// 		        selectedEvent: null,
// 		        openCalendarCard: false
// 	          }
// 	}

// 	eventClicked(event){
// 		console.log("EVENT: ", event)
// 		this.setState({openCalendarCard: true, selectedEvent: event, openCalendarForm:false, openEditForm: false})
// 	}

// 	openCalendarForm(selectedSlot){
// 		var selectedDate = selectedSlot.end;
// 		this.setState({openCalendarForm: true, selectedDate: selectedDate, openEditForm:false})
// 	}

// 	handleCloseAll(){
// 		console.log("close all")
// 		this.setState({openCalendarCard: false, openEditForm: false, openCalendarForm: false});
// 	}

// 	editFormOpen(){
// 		this.setState({openEditForm: true, openCalendarCard: false})
// 	}

// 	getEventArray(){
// 		let scheduleItemsObjArr = ScheduleItemStore.scheduleItems;
// 		var scheduleItemTempArr= toJS(scheduleItemsObjArr);
// 		var scheduleItemArr = [];
// 		console.log("SCHEDULE ITEMS: ", scheduleItemTempArr)
// 		if(scheduleItemTempArr && scheduleItemTempArr.length>0){
// 			for(var i=0 ; i<scheduleItemTempArr.length ; i++){
// 				scheduleItemArr = scheduleItemArr.concat({
// 					id: scheduleItemTempArr[i]._id,
// 					title: scheduleItemTempArr[i].name, 
// 					allDay: false, 
// 					start: scheduleItemTempArr[i].startTime, 
// 					end: scheduleItemTempArr[i].endTime, 
// 					description: scheduleItemTempArr[i].description,
// 					location: scheduleItemTempArr[i].location,
// 					isRecurring: scheduleItemTempArr[i].isRecurring,
// 					createdBy: scheduleItemTempArr[i].createdBy
// 				})
// 			}
// 		}
// 		return scheduleItemArr;
// 	}

// 	render(){
// 		let scheduleItemArr = this.getEventArray();

// 		return(
// 			<Paper className="standardTopGap standardBottomGap paperDefault">
// 			    <div>
// 					<h4>My Personal Calendar</h4>
// 					<div>
// 					  <BigCalendar 
// 					    	events = {scheduleItemArr}
// 					    	selectable
// 					    	defaultDate = {new Date()}
// 					    	onSelectSlot = {(slotInfo) => this.openCalendarForm(slotInfo)}
// 					    	onSelectEvent = {(event)=> this.eventClicked(event) }
// 					    	views={['month', 'agenda']}
// 					    	popup={true}
// 					    	popupOffset={{x: 30, y: 20}}
// 					    	length = {7}
// 					    />  
// 					  </div>
// 					  {
// 					  	this.state.openCalendarForm ? <AddCalendarItemForm scheduleItemStore={ScheduleItemStore} selectedDate={this.state.selectedDate} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>
// 					  }
// 					  {
// 					  	this.state.openCalendarCard ? <SingleCalendarCard 
// 						  	selectedEvent={this.state.selectedEvent} 
// 						  	handleCloseAll={this.handleCloseAll.bind(this)} 
// 						  	editFormOpen={this.editFormOpen.bind(this)}
// 					  	/> : (<span></span>)
// 					  }
// 					  {
// 					  	this.state.openEditForm ? <EditCalendarItemForm scheduleItemStore={ScheduleItemStore} event={this.state.selectedEvent} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>
// 					  }
// 				</div>  
// 			</Paper>
// 		)
// 	}
// }


export default MainCalendar;