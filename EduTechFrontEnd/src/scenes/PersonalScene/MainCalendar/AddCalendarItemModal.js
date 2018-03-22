import React, {Component} from 'react';
import { Modal,Button } from 'react-bootstrap';
import Datetime from 'react-datetime';
import moment from 'moment';
import {observer} from 'mobx-react';
import swal from 'sweetalert';

import './react-datetime.css';
import './styles.css';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class AddCalendarItemModal extends Component {

	constructor(){
		super()
		ScheduleItemStore.addFormSuccess = false;
		this.state = {
			startTime: null,
			endTime: null
		}
	}

	componentDidUpdate(prevProps, prevState){
		if (prevProps.formSuccess !== this.props.formSuccess) {
			this.props.handleClose();	    
		}
	}

	changeStartTime(value){
		var startTime = moment(value._d).format('HH:mm')
		this.setState({startTime: startTime})
	}

	changeEndTime(value){
		var endTime = moment(value._d).format('HH:mm')
		this.setState({endTime: endTime});
	}

	addCalendarItem(event){
		event.preventDefault();
		var name = this.refs.name.value.trim();
		var description = this.refs.description.value.trim();
		var location = this.refs.location.value.trim();
		var recurring = this.state.recurringWeek;
		var selectedDate = moment(this.props.selectedDate).format("MMM DD YYYY");
		var createdBy = localStorage.getItem('username');	// to replace with username from self userEntity
		var type = "personal";

		if(!this.state.startTime || !this.state.endTime){
			swal("Warning!", "Input time field is empty.", "warning");
		}else {
			let startTime = selectedDate + " " + this.state.startTime;
			let endTime = selectedDate + " " + this.state.endTime;
			startTime = moment(startTime).format();
			endTime = moment(endTime).format();
			ScheduleItemStore.addScheduleItem(name, description, startTime, endTime, location, createdBy, [], type, "","");
			
		}
	}

	render(){

		return(
			<div>
				<Modal.Body>
					<form id="addForm">
					  <div className="container">
					  	<div className="row">
					  		<div className="col-md-2">
					  			<div className="card" style={{'background': '#87CEFA', 'width': '120px'}}>
								  <div className="card-block">
								    <strong style={{'paddingLeft': '10px'}}>Pick Start Time</strong>
								    <Datetime
								    	dateFormat={false}
								    	onChange={(value)=>this.changeStartTime(value)}/>
								  </div>
								</div>
					  		</div>
					  		<div className="col-md-2">
					  			<div className="card" style={{'background': '#87CEFA', 'width': '120px'}}>
								  <div className="card-block">
								    <strong style={{'paddingLeft': '10px'}}>Pick End Time</strong>
								    <Datetime
								    	dateFormat={false}
								    	onChange={(value)=>this.changeEndTime(value)}/>
								  </div>
								</div>
					  		</div>
					  	</div>
					  </div>
					  	<div className="form-group row mt-2 formGap">
						  <label htmlFor="eventName" className="col-2 col-form-label">Event Name:</label>
						  <div className="col-8">
						    <input className="form-control" type="text" id="eventName" ref="name" />
						  </div>
						</div>

						<div className="form-group row mt-2 formGap">
						  <label htmlFor="description" className="col-2 col-form-label">Description:</label>
						  <div className="col-8">
						    <input className="form-control" type="text" id="description" ref="description" />
						  </div>
						</div>

						<div className="form-group row mt-2 formGap">
						  <label htmlFor="location" className="col-2 col-form-label">Location:</label>
						  <div className="col-8">
						    <input className="form-control" type="text" id="location" ref="location" />
						  </div>
						</div>

					</form>
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={this.props.handleClose.bind(this)}>Close</Button>
					<Button type="submit" bsStyle="primary" onClick={this.addCalendarItem.bind(this)}>Add Calendar Item</Button>
				</Modal.Footer>
	        </div>
		)
	}
}

export default AddCalendarItemModal;