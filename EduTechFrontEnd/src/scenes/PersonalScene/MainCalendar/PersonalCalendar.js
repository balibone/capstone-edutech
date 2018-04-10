import React, {Component} from 'react';
import { Modal,Button, Well, Row, Col} from 'react-bootstrap';
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
// import AddCalendarItemForm from './AddCalendarItemForm';
// import AddCalendarItemForm from './AddCalendarItemForm';
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
		console.log("Selected SLOT: ", selectedSlot)

		if(selectedDate > today){
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

	eventStyleGetter(event, start, end, isSelected) {
    	var backgroundColor = '#' + event.hexColor;
    	var style = {
	        backgroundColor: backgroundColor,
	        // opacity: 0.8,
	        // color: 'black',
	        // display: 'block'
    	};
    	return { style: style };
	}

	handleCloseModal(){
		this.setState({openCalendarForm: false})
	}

	handleClose() {
		this.setState({ openCalendarForm: false });
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
		  console.log("EVENTS ARR:", eventsArray)
		// eventsArray = eventsArray.filter(event => event.type === "personal" || event.type === "meeting");
		return(
		    <div>
				  <BigCalendar 
				    	events = {eventsArray}
				    	selectable
				    	defaultDate = {new Date()}
				    	onSelectSlot = {(slotInfo) => this.openCalendarForm(slotInfo)}
				    	onSelectEvent = {(event)=> this.eventClicked(event) }
				    	views={['month', 'agenda', 'week']}
				    	popup={true}
				    	popupOffset={{x: 30, y: 20}}
				    	length = {7}
				    	eventPropGetter = {this.eventStyleGetter}
				    	formats = {formats}
		
				    />  
				    <Row>
					    <Col md={2}>
					    	
					    </Col>
					    <Col md={8}>
					    	<Well>
						    	<Row>
						    		<Col md={2}><div className="legend-personal"></div>personal</Col>
						    		<Col md={2}><div className="legend-meeting"></div>meeting</Col>
						    		<Col md={2}><div className="legend-timetable"></div>timetable</Col>
						    		<Col md={2}><div className="legend-assessment"></div>assessment</Col>
						    		<Col md={2}><div className="legend-task"></div>task</Col>
						    	</Row>
					    	</Well>
					    </Col>
				    </Row>
				  		  	
				  	 {/*this.state.openCalendarForm ? <AddCalendarItemForm scheduleItemStore={ScheduleItemStore} selectedDate={this.state.selectedDate} handleCloseAll={this.handleCloseAll.bind(this)}/> : <span></span>*/}
				  	<Modal show={this.state.openCalendarForm} onHide={this.handleClose.bind(this)}>
			          <Modal.Header closeButton>
			            <Modal.Title>Add Calendar Item on {moment(this.state.selectedDate).format('D/M/YY')}</Modal.Title>
			          </Modal.Header>
			          <AddCalendarItemModal selectedDate={this.state.selectedDate} handleClose={this.handleClose.bind(this)} formSuccess={ScheduleItemStore.addFormSuccess}/>
			        </Modal>
				  	
				 
				  
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
