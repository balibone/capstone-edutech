import React, {Component} from 'react';
import {Label, Row} from 'react-bootstrap';

import GroupingListInstructor from './GroupingListInstructor';

class SingleAssignmentGroup extends Component {

	render(){
		// console.log("props assignment: ", this.props.assignment)
		const assignment = this.props.assignment;
		const moduleGrouping = assignment.groups;

		// console.log("STUDENT EXIST: ", studentExist);
		return(
			<div>
				<h3>
			    	{assignment.title} 
			    	<h5><Label bsStyle="default">Max Group Size: {moduleGrouping[0].groupSize}</Label></h5>
				</h3>
				<Row>
					{
						(moduleGrouping.length > 0) ? (
							moduleGrouping.map((group) => {
								return <GroupingListInstructor key={group.id} group={group} assignmentId={assignment.id}/>
							})
							)
						: (<span></span>)		
					}
				</Row>
				<hr />
			</div>
		)
	}
}

export default SingleAssignmentGroup;