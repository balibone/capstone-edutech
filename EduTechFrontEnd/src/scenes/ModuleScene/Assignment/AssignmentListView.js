import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Panel, PanelGroup, Button } from 'react-bootstrap';
import swal from 'sweetalert';

import GroupAssignmentView from './GroupAssignmentView';
import IndividualAssignmentView from './IndividualAssignmentView';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

@observer
class AssignmentListView extends Component {
	deleteAssignment(assignmentId) {
		swal({
		  title: 'Are you sure?',
		  text: 'You will not be able to recover this item!',
		  icon: 'warning',
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		  	AssignmentStore.deleteAssignment(assignmentId);
		  }
		});
	}
	renderDeleteBtn(assignment) {
		const userType = localStorage.getItem('userType')
		if (userType === 'instructor') {
			return (<Button bsStyle="warning" bsSize="small" className="pull-right" onClick={() => this.deleteAssignment(assignment.id)}>Delete</Button>)
		}
		return '';
	}
	renderAssignment(assignmentList) {
		return assignmentList.map(assignment =>
			(
			<Panel eventKey={assignment.id}>
		    	<Panel.Heading>
		      		<Panel.Title toggle>
		      			{assignment.title} &nbsp;
		      			({
		      				(assignment.groups.length > 0) ? 'Group' : 'Individual'
		      			})
								{this.renderDeleteBtn(assignment)}
		      		</Panel.Title>
		    	</Panel.Heading>
		    	<Panel.Body collapsible>
		    		{
		    			(assignment.groups.lengtsh > 0) ?
		    			<GroupAssignmentView assignment={assignment} />
							: <IndividualAssignmentView assignment={assignment} />
		    		}
		    	</Panel.Body>
	 		</Panel>
			)
		)
	}

	render() {
		const { assignmentList } = this.props;
		return (
			<div className="standardTopGap">
			{
				(assignmentList.length > 0) ? (<h4>Students Submissions</h4>) : ''
			}
				<PanelGroup accordion id="accordion-example">
					{this.renderAssignment(assignmentList)}
				</PanelGroup>
			</div>
		)
	}
}

export default AssignmentListView;
