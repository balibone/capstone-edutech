import React, {Component} from 'react';
import moment from 'moment';
import Datetime from 'react-datetime';
import {observer} from 'mobx-react';
import swal from 'sweetalert';
import axios from 'axios';
import './react-datetime.css';
import './styles.css';

@observer
class AddCalendarItemForm extends Component {

	constructor(props){
		super(props)
		this.state = {
			itemAdded: false,
			name: "NanDa",
			description: "",
			// startTime: moment(new Date()).format('DD/MM/YYYY HH:mm a'),
			// endTime: moment(new Date()).format('DD/MM/YYYY HH:mm a'),
			startTime: "",
			endTime: "",
			isRecurring: false,
			location: "",
			createdBy: "",
			recurringWeek: 1,
			createdOn: null  // system auto
		}
	}

	addCalendarItem(){
		const { scheduleItemStore } = this.props;
		var name = this.refs.name.value.trim();
		var description = this.refs.description.value.trim();
		var location = this.refs.location.value.trim();
		var recurring = this.state.recurringWeek;
		var createdAt = moment(new Date()).format('MMM DD YYYY HH:mm a')
		var selectedDate = moment(this.props.selectedDate).format("MMM DD YYYY");
		let startTime = selectedDate + " " + this.state.startTime;
		let endTime = selectedDate + " " + this.state.endTime;
		var user = null;
		// var dataSet = [name, description, startTime, endTime, location, user, createdAt, "nanda", "timetable"]

		var startTimeISO = moment(startTime);
		var endTimeISO = moment(endTime, moment.ISO_8601);
		console.log("ISO startTimeISO: ", startTimeISO);
		console.log("ISO endTimeISO: ", endTimeISO)

		scheduleItemStore.addScheduleItem(name, description, startTime, endTime, location, user, createdAt, "timetable", "module22", "group44");
		this.refs.name.value = "";
        this.refs.description.value ="";
        this.refs.location.value = "";
        this.setState({startTime: ""})
        this.setState({endTime: ""})
		// axios.post('/commoninfraentities.scheduleitem', dataSet)
		// .then((res) => {
		// 	scheduleItemStore.addScheduleItem(name, description, startTime, endTime, location, user, createdAt, "nandastore", "timetable", "module22", "group44");
		// 	this.refs.name.value = "";
		// 	this.refs.description.value ="";
		// 	this.refs.location.value = "";
		// 	this.setState({startTime: ""})
		// 	this.setState({endTime: ""})
		// 	swal(res.data,"Schedule Event Added!" , "success");
		// })
		// .catch((err) => {
		// 	console.log("Error in Adding item: ", err)

		// })
	}

	changeStartTime(value){
		var startTime = moment(value._d).format('HH:mm')
		this.setState({startTime: startTime})
	}

	changeEndTime(value){
		var endTime = moment(value._d).format('HH:mm')
		this.setState({endTime: endTime});
	}

	render(){
		return(
			<div>
				<hr />
				<h4 className="underline">Add Schedule Item on - <strong>{moment(this.props.selectedDate).format('DD/MM/YYYY')}</strong></h4>
				<form>

				  <div className="container">
				  	<div className="pull-right inlineIcon">
					    <i className="fas fa-times" onClick={() => this.props.handleCloseAll()} />							    	
				    </div>
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
				  	<div className="form-group row mt-2">
					  <label htmlFor="eventName" className="col-2 col-form-label">Event Name:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="eventName" ref="name" />
					  </div>
					</div>

					<div className="form-group row mt-2">
					  <label htmlFor="description" className="col-2 col-form-label">Description:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="description" ref="description" />
					  </div>
					</div>

					<div className="form-group row mt-2">
					  <label htmlFor="location" className="col-2 col-form-label">Location:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="location" ref="location" />
					  </div>
					</div>
					
					<label htmlFor={`selectDropdown${this.state.name}`}>Number of Recurring Week &nbsp; </label>
					<select id={`selectDropdown${this.state.name}`} onChange={(e)=> this.setState({recurringWeek: e.target.value || null})} value={this.state.recurringWeek || '1'}>
					  <option value="1">1</option>
					  <option value="2">2</option>
					  <option value="3">3</option>
					  <option value="4">4</option>
					  <option value="5">5</option>
					  <option value="6">6</option>
					  <option value="7">7</option>
					  <option value="8">8</option>
					  <option value="9">9</option>
					  <option value="10">10</option>
					  <option value="11">11</option>
					  <option value="12">12</option>
					</select>

				</form>
				<div className="d-flex justify-content-end">
					<div className="mr-auto p-2 ml-2 mb-1">
				  		<button type="submit" className="btn btn-primary btn-lg" onClick={this.addCalendarItem.bind(this)}>Add Calendar Item</button>
				  	</div>
				</div>

			</div>
		)
	}
}

export default AddCalendarItemForm;
