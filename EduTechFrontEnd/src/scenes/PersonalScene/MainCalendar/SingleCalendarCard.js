import React, { Component } from 'react';
import {Tabs, Tab} from 'material-ui/Tabs';
import { Badge } from 'react-bootstrap';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {observer} from 'mobx-react';
import moment from 'moment';
import axios from 'axios';
import swal from 'sweetalert';
import './styles.css';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class SingleCalendarCard extends Component {

	handleCloseAll(){ 
		console.log("Closing")
		this.props.handleCloseAll() 
	}
	editFormOpen(){ 
		console.log("OPENING edit form")
		this.props.editFormOpen() 
	}	

	removeItem(){
		var { id } = this.props.selectedEvent;

		swal({
		  title: "Are you sure?",
		  text: "You will not be able to recover this item!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		  	ScheduleItemStore.removeScheduleItem(id);
		    swal("Poof! Your imaginary file has been deleted!", {icon: "success"});
			this.props.handleCloseAll();
		  } 
		});

		
	}

	render(){
		var { title, start, end, description, type} = this.props.selectedEvent;
		var duration = moment(start).format("HH:mm") + " - " + moment(end).format("HH:mm")
		console.log("TYPE: ", this.props.selectedEvent);
		var titleText = title + " on " + moment(start).format("DD/MM/YYYY")
		var color = "";
		switch(type){
			case "personal": color = '#006400'
				break;
			case "meeting": color = '#FF8C00'
				break;
			case "timetable": color = '#9932CC'
				break;
			case "assessment": color = '#00BFFF'
				break;
		}

		return(
    		<Card>
			    <div className="row">
				  <div className="col-md-10 standardLeftGap standardTopGap">
				  	<h4>{titleText} <Badge style={{background: color}}>{type}</Badge></h4> 
				  	<p>{duration}</p>
				  </div>
				  <div className="col-md-1">
				    <div className="text-right" >
				    	<i className="fas fa-times fa-1x standardTopGap standardRightGap btnHover" onClick={this.handleCloseAll.bind(this)}></i>
				    </div>
				  </div>
				</div>
	    
			    <CardText>
			      {description}
			    </CardText>
			    {
			    	type === "personal" ?
			    	(
			    		<CardActions>
					      <FlatButton label="Edit Item" onClick={this.editFormOpen.bind(this)} />
					      <FlatButton label="Remove Item" onClick={this.removeItem.bind(this)}/>
					    </CardActions>
			    	) : <span></span>
			    }
			    
		    </Card>
		)
	}
}

export default SingleCalendarCard;