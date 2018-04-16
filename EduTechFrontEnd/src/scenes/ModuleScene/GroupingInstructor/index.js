import React, { Component } from 'react';
import { toJS } from 'mobx';
import { observer } from 'mobx-react';

import SingleAssignmentGroup from './SingleAssignmentGroup';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

@observer
class GroupingInstructor extends Component {
	renderAssignmentList() {
		const assignmentList = toJS(AssignmentStore.assignmentList);
    	if (assignmentList.length > 0) {
    		return assignmentList.map(assignment =>
    			(
    				(assignment.groups.length > 0) ?
    				<SingleAssignmentGroup key={assignment.id} assignment={assignment} />
    				: ''
    			)
    		)
    	}
  		return (
  			<div>
  				<h2>No grouping created.</h2>
				<h4 style={{ fontWeight: '150' }}>Please create group assignment to create groups.</h4>
				<br />
  			</div>
  			)
	}

	render() {
		return (
			<div className="standardTopGap">
				{this.renderAssignmentList()}
			</div>
		)
	}
}

export default GroupingInstructor;
