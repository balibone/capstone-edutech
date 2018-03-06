import React, {Component} from 'react';
import moment from 'moment';
import Datetime from 'react-datetime';
import { DropdownButton, MenuItem } from 'react-bootstrap';
import {observer} from 'mobx-react';
import swal from 'sweetalert';
import axios from 'axios';
import './react-datetime.css';
import './styles.css';

@observer
class AddCalendarItemForm extends Component {

	constructor(props){
		super(props)
		this.props.scheduleItemStore.addFormSuccess = false;
		this.state = {
			itemAdded: false,
			name: "NanDa",
			description: "",
			startTime: null,
			endTime: null,
			isRecurring: false,
			location: "",
			createdBy: "",
			recurringWeek: 1,
		}
	}

	addCalendarItem(event){
		event.preventDefault();
		const { scheduleItemStore } = this.props;
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
			scheduleItemStore.addScheduleItem(name, description, startTime, endTime, location, createdBy, [], type, "","");
			console.log(scheduleItemStore.addFormSuccess)
			if(scheduleItemStore.addFormSuccess){
				console.log("SUCCESS")
				this.refs.name.value = "";
		        this.refs.description.value ="";
		        this.refs.location.value = "";
		        this.setState({startTime: ""})
		        this.setState({endTime: ""})
		        this.props.handleCloseAll();
			}
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

	selectType(value){
		console.log("dd type: ", value)
		this.setState({type: value});
	}

	render(){
		const { scheduleItemStore } = this.props;
				console.log(scheduleItemStore.addFormSuccess)
		if(scheduleItemStore.addFormSuccess){
			console.log("SUCCESS")
			this.refs.name.value = "";
	        this.refs.description.value ="";
	        this.refs.location.value = "";
	        this.setState({startTime: ""})
	        this.setState({endTime: ""})
	        this.props.handleCloseAll();
		}

		return(
			<div>
				<hr />
				<h4 className="underline">Add Schedule Item on - <strong>{moment(this.props.selectedDate).format('DD/MM/YYYY')}</strong></h4>
				<form id="addForm">

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

					{/*<div className="row mt-2">
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
					</div>*/}

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
