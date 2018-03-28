import React, {Component} from 'react';
import {Label, Row} from 'react-bootstrap';
import GroupingListStudent from './GroupingListStudent';

class GroupingStudent extends Component{

	render(){

		return(
			<div className="standardTopGap">
				<h3>
			    	Assignment 1A 
			    	<h5><Label bsStyle="default">Max Group Size: 4</Label></h5>
				</h3>
				<Row>
					<GroupingListStudent />
					<GroupingListStudent />
					<GroupingListStudent />
					<GroupingListStudent />
				</Row>
			</div>
		)
	}
}

export default GroupingStudent;