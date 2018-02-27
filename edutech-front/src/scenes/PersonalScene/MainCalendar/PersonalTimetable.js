import React, { Component } from 'react';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

BigCalendar.momentLocalizer(moment);

@observer
class PersonalTimetable extends Component {

	// getEventArray(){
	// 	let scheduleItemsObjArr = ScheduleItemStore.scheduleItems;
	// 	var scheduleItemTempArr= toJS(scheduleItemsObjArr);
	// 	var scheduleItemArr = [];
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
		// var eventsArr = this.getEventArray();
		console.log("SCHEDULE ITEMS: ", this.props.eventsArray)
		var eventsArray = this.props.eventsArray.filter(event => event.type == "timetable");

		return(
			<div>
				 <BigCalendar 
				    	events = {eventsArray}
				    	selectable
				    	defaultDate = {new Date()}
				    	// views={['week']}
				    	
				    />  
			</div>
		)
	}
}

export default PersonalTimetable;