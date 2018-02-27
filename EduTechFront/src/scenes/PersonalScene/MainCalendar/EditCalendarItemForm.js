import React, { Component } from 'react';
import moment from 'moment';
import Datetime from 'react-datetime';
import { observer } from 'mobx-react';
import swal from 'sweetalert';
import axios from 'axios';
import './react-datetime.css';


@observer
class EditCalendarItemForm extends Component {

  constructor(props) {
		super(props);
		this.state = {
      title: null,
      description: null,
			startTime: null,
			endTime: null,
			isRecurring: null,
      location: null
    }
  }

	componentDidMount(){
		var { title, description, location, isRecurring, start, end } = this.props.event;
		this.setState({
			title: title,
			description: description,
			startTime: start,
			endTime: end,
			isRecurring: isRecurring,
			location: location
		})
	}

	editCalendarItem(){
		const { scheduleItemStore } = this.props;
		var itemId = this.props.event.id;
		var createdOn = moment(new Date()).format('MMM DD YYYY HH:mm a')
		var createdBy = this.props.event.createdBy;
		var { title, description, location, isRecurring, startTime, endTime } = this.state;

		var date = moment(startTime).format("MMM DD YYYY");

		console.log(startTime)
		console.log(endTime)
		scheduleItemStore.updateScheduleItem(itemId, title, description, startTime, endTime, location, isRecurring, createdBy, createdOn);

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
		var { title, description, location, isRecurring, start, end } = this.props.event;

		return(
			<div>
				<hr />
				<h4 className="underline">Edit Schedule Item<strong></strong></h4>
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
							    	onChange={(value)=>this.changeStartTime(value)}
							    	defaultValue = {moment(this.props.event.start).format("hh:mm A")}
							    />
							  </div>
							</div>
				  		</div>
				  		<div className="col-md-2">
				  			<div className="card" style={{'background': '#87CEFA', 'width': '120px'}}>
							  <div className="card-block">
							    <strong style={{'paddingLeft': '10px'}}>Pick End Time</strong>
							    <Datetime
							    	dateFormat = {false}
							    	onChange = {(value)=>this.changeEndTime(value)}
							    	defaultValue = {moment(this.props.event.end).format("hh:mm A")}
							    	/>
							  </div>
							</div>
				  		</div>
				  	</div>
				  </div>
				  	<div className="form-group row mt-2">
					  <label htmlFor="eventName" className="col-2 col-form-label">Event Name:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="eventName" ref="name"
					    	value={this.state.title}
					    	onChange={(e)=>this.setState({title: e.target.value})}/>
					  </div>
					</div>

					<div className="form-group row mt-2">
					  <label htmlFor="description" className="col-2 col-form-label">Description:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="description" ref="description" value={this.state.description} onChange={(e)=>this.setState({description: e.target.value})}/>
					  </div>
					</div>

					<div className="form-group row mt-2">
					  <label htmlFor="location" className="col-2 col-form-label">Location:</label>
					  <div className="col-8">
					    <input className="form-control" type="text" id="location" ref="location" value={this.state.location} onChange={(e)=>this.setState({location: e.target.value})}/>
					  </div>
					</div>

					<div className="form-check">
					    <label className="form-check-label">
					      <input type="checkbox" className="form-check-input" ref="recurring" defaultChecked={this.state.isRecurring} onChange={(e)=>this.setState({isRecurring: e.target.value})}/>
					      Recurring
					    </label>
					  </div>

				</form>
				<div className="d-flex justify-content-end">
					<div className="mr-auto p-2 ml-2 mb-1">
						
				  		<button type="submit" className="btn btn-primary btn-lg" onClick={this.editCalendarItem.bind(this)}>Submit</button>
				  	</div>
				</div>

			</div>
		)
	}
}

export default EditCalendarItemForm;
