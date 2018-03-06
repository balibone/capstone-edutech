import React, {Component} from 'react';
import { Paper } from 'material-ui';
// import Calendar from 'react-calendar';
import {observer} from 'mobx-react';
import Calendar from 'react-widgets/lib/Calendar'
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import 'react-widgets/dist/css/react-widgets.css'
import './styles.css';

Moment.locale('en');
momentLocalizer();


@observer
class CalendarPanel extends Component {

	constructor(){
		super();
		this.state = {
			currentDate: new Date(),
			selectedDateItem: null
		}
	}


	selectedDay(value){
		var selectedDate = Moment(value).format('MMM DD YYYY');
		var getFromStore = ScheduleItemStore.selectedScheduleItem(selectedDate);
		console.log(getFromStore);
		if(getFromStore.length > 0)
			this.setState({selectedDateItem: getFromStore })
		else
			this.setState({selectedDateItem: null})
	}

	render(){
		console.log(ScheduleItemStore);
		var {selectedDateItem} = this.state;
		var infoArea = (<Paper className="paperDefault">No Event Exists on this day</Paper>);
		if(selectedDateItem !==null){
			console.log("selectedDateItem", selectedDateItem)
			infoArea = selectedDateItem.map((item)=>{
				return (
				<Paper className="paperDefault">
			    	<p><strong>Event Name:</strong> {item.title}</p>
			    	<p><strong>Description:</strong> {item.description}</p>
			    	<p><strong>Time:</strong> {Moment(item.startDate).format("HH:mm")} - {Moment(item.endDate).format("HH:mm")}</p>
			    	<p><strong>Location:</strong> {item.location}</p>
			  	</Paper>
			  	)
			})
			// var {name, description, startTime, endTime, location, createdBy, createdOn} = this.state.selectedDateItem;
		}

		return(
		  <div>
		    <Calendar 
		    	onChange={(value)=>this.selectedDay(value)}
		    />
		    {infoArea}
		  </div>
		)
	}
}

export default CalendarPanel;
;