import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {Tabs, Tab} from 'material-ui/Tabs';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import moment from 'moment';

import PersonalCalendar from './PersonalCalendar';
import PersonalTimetable from './PersonalTimetable';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class MainCalendar extends Component {

	getEventArray(){
		let scheduleItemsObjArr = ScheduleItemStore.scheduleItems;
		var scheduleItemTempArr= toJS(scheduleItemsObjArr);
		var scheduleItemArr = [];
		console.log("SCHEDULE ITEMS: ", scheduleItemTempArr)
		if(scheduleItemTempArr && scheduleItemTempArr.length>0){
			for(var i=0 ; i<scheduleItemTempArr.length ; i++){
				var color = "";
				switch(scheduleItemTempArr[i].type){
					case "personal": color = '006400'
						break;
					case "meeting": color = 'FF8C00'
						break;
					case "timetable": color = '9932CC'
						break;
					case "assessment": color = '00BFFF'
						break;
				}
				scheduleItemArr = scheduleItemArr.concat({
					id: scheduleItemTempArr[i].id,
					title: scheduleItemTempArr[i].title, 
					allDay: false, 
					start: new Date(scheduleItemTempArr[i].startDate), 
					end: new Date(scheduleItemTempArr[i].endDate), 
					description: scheduleItemTempArr[i].description,
					location: scheduleItemTempArr[i].location,
					type: scheduleItemTempArr[i].type,
					createdBy: scheduleItemTempArr[i].createdBy,
					hexColor: color
				})
			}
		}
		return scheduleItemArr;
	}


	render(){
		let eventsArray = this.getEventArray();
		{console.log("DAY OF THE WEEK TEST", moment("2018-04-02").isoWeekday())}

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

export default MainCalendar;