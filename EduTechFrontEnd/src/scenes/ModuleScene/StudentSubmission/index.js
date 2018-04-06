import React, {Component} from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import {Button, Panel, PanelGroup} from 'react-bootstrap';

import GroupSubmission from './GroupSubmission';
import IndividualSubmission from './IndividualSubmission';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

@observer
class StudentSubmission extends Component {

	renderAssignment(assignmentList){
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
		    		{
		    			(assignment.groups.length>0) ?
		    			<GroupSubmission assignment={assignment}/> : <IndividualSubmission assignment={assignment}/>
		    		}
		    	</Panel.Body>
	 		</Panel>
			)
		)
	}

	render(){
		// const assignmentList = this.props.assignmentList;
		const assignmentList = toJS(AssignmentStore.assignmentList);

		return(
			<div className="standardTopGap">
			<h4>My Submissions</h4>
				<PanelGroup accordion id="accordion-example">
					{this.renderAssignment(assignmentList)}
				</PanelGroup>
			</div>
		)
	}


}

export default StudentSubmission;