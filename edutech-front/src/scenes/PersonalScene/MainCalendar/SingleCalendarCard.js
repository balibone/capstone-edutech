import React, { Component } from 'react';
import {Tabs, Tab} from 'material-ui/Tabs';
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
		ScheduleItemStore.removeScheduleItem(id);
		this.props.handleCloseAll();
	}

	render(){
		var { title, start, end, description} = this.props.selectedEvent;
		var duration = moment(start).format("HH:mm") + " - " + moment(end).format("HH:mm")
		console.log("DURATION: ", duration);
		var titleText = title + " on " + moment(start).format("DD/MM/YYYY")
		return(
    		<Card>
			    <div className="row">
				  <div className="col-md-10">
				  	<CardHeader
				      title={titleText}
				      subtitle={duration}
				    />
				  </div>
				  <div className="col-md-2">
				    <div className="text-right" >
				    	<i className="fas fa-times fa-1x standardTopGap standardRightGap btnHover" onClick={this.handleCloseAll.bind(this)}></i>
				    </div>
				  </div>
				</div>
	    
			    <CardText>
			      {description}
			    </CardText>
			    <CardActions>
			      <FlatButton label="Edit Item" onClick={this.editFormOpen.bind(this)} />
			      <FlatButton label="Remove Item" onClick={this.removeItem.bind(this)}/>
			    </CardActions>
		    </Card>
		)
	}
}

export default SingleCalendarCard;