import React, { Component } from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import { Paper, Tabs, Tab } from 'material-ui';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import axios from 'axios';

import 'react-widgets/dist/css/react-widgets.css';
import 'react-big-calendar/lib/css/react-big-calendar.css';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import MeetingStore from '../../../stores/MeetingStore/MeetingStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import MergeGroupStore from '../../../stores/GroupStore/MergeGroupStore';

import ProjectGroupCalendar from './ProjectGroupCalendar';

moment.locale('en')
BigCalendar.momentLocalizer(moment);

@observer
class MergeCalendar extends Component {

	constructor(props){
		super(props);
	}

	// getEventArray(membersScheduleItems){
	// 	let scheduleItemArr = [];
	// 	let timetableAssignedTo = [];

	// 	console.log("THIS GROUP ID", toJS(GroupStore.selectedGroup));
	// 	let membersInGroup = toJS(GroupStore.selectedGroup).members;

	// 	if(membersScheduleItems && membersScheduleItems.length>0){
	// 		for(var i=0 ; i<membersScheduleItems.length ; i++){
	// 			var namesArr =[];
	// 			console.log(" membersScheduleItems ",membersScheduleItems[i]);
	// 			if(membersScheduleItems[i].assignedTo || membersScheduleItems[i].type === "timetable"){
	// 				if(membersScheduleItems[i].type === "timetable"){
	// 					namesArr = this.filterAssignedTo(membersScheduleItems[i], membersInGroup);
	// 				} else {
	// 					for(var j=0 ; j<membersScheduleItems[i].assignedTo.length ; j++){
	// 						namesArr.push(membersScheduleItems[i].assignedTo[j].username);
	// 					}
	// 				}

	// 				console.log("NAME ARR before join", namesArr)

	// 				scheduleItemArr = scheduleItemArr.concat({
	// 					id: membersScheduleItems[i].id,
	// 					title: namesArr.join(", "), 
	// 					allDay: false, 
	// 					start: new Date(membersScheduleItems[i].startDate), 
	// 					end: new Date(membersScheduleItems[i].endDate), 
	// 					description: membersScheduleItems[i].description,
	// 					location: membersScheduleItems[i].location,
	// 					type: membersScheduleItems[i].type,
	// 					createdBy: membersScheduleItems[i].createdBy
	// 				})
	// 			}
	// 		}
	// 	}
	// 	return scheduleItemArr;
	// }

	getEventArray(membersScheduleItems){
		var scheduleItemArr = [];
		
		console.log("SCHEDULE ITEMS: ", membersScheduleItems)
		if(membersScheduleItems && membersScheduleItems.length>0){
			for(var i=0 ; i<membersScheduleItems.length ; i++){
				var namesArr =[];
				if(membersScheduleItems[i].type === "timetable"){
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
				}
			}
		}
		return scheduleItemArr;
	}


	render(){
		let membersScheduleItems = toJS(ScheduleItemStore.userGroupScheduleItems);
		console.log("MEMBERS SCHEDULE ITEMS: ", membersScheduleItems);
		let eventsArray = this.getEventArray(membersScheduleItems);
		console.log("EVENTS ARRAY: ", eventsArray);


		return(
			<div id="merge-calendar">
				<Tabs>
			  	<Tab label="Group Schedule" style={{background: 'cadetblue'}}>
			      <ProjectGroupCalendar eventsArray={membersScheduleItems} />
			    </Tab>
			    <Tab label="Group Timetable" style={{background: 'cadetblue'}} >
			      <div>
			        <BigCalendar 
				    	events = {eventsArray}
				    	selectable
				    	defaultDate = {new Date()}
				    	views={['month','agenda']}
				    	drilldownView="agenda"
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length = {7}
				    /> 
			      </div>
			    </Tab>
			  </Tabs>
				 
			</div>
		)
	}
}

export default MergeCalendar;