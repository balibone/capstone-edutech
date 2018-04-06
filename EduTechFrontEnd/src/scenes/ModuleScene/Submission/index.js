import React, {Component} from 'react';
import {Table, thead, tbody, Button, Panel, PanelGroup} from 'react-bootstrap';
import SingleAssignment from './SingleAssignment';
import SingleAssignmentTable from './SingleAssignmentTable';

class Submission extends Component {

	renderAssignment(assignmentList){
		let isGroup = false;
		return assignmentList.map((assignment) => 
			(
			<Panel eventKey={assignment.id}>
		    	<Panel.Heading>
		      		<Panel.Title toggle>
		      			{assignment.title} &nbsp;
		      			({
		      				(assignment.groups.length>0) ? "Group" : "Individual"
		      			})
		      		</Panel.Title>
		    	</Panel.Heading>
		    	<Panel.Body collapsible>
		    		No submission.
		    	</Panel.Body>
	 		</Panel>
			)
		)
	}

	render(){
		const assignmentList = this.props.assignmentList;

		return(
			<div className="standardTopGap">
			<h4>Students Submissions</h4>
				<PanelGroup accordion id="accordion-example">
					{this.renderAssignment(assignmentList)}
				</PanelGroup>
			</div>
		)
	}
}

export default Submission;