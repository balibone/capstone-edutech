import React, {Component} from 'react';
import {Grid, Row} from 'react-bootstrap';
import GroupingListInstructor from './GroupingListInstructor';

class GroupingInstructor extends Component {

	render(){

		return(
			<div className="standardTopGap">
				<h2>No grouping created.</h2>
				<h4 style={{fontWeight: '150'}}>Please create group assignment to create groups.</h4>
				<br />
				<h2>Assignment 1A</h2>
				<h4 style={{fontWeight: '150'}}>Number of Groups: 8</h4>
				
					<Row>
						<GroupingListInstructor />
						<GroupingListInstructor />
						<GroupingListInstructor />
						<GroupingListInstructor />						
					</Row>
				
			</div>
		)
	}
}

export default GroupingInstructor;