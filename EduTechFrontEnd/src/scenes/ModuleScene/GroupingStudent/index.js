import React, {Component} from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';

import SingleAssignmentGroup from './SingleAssignmentGroup';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';


@observer
class GroupingStudent extends Component{

	renderAssignmentList(){
		const assignmentList = toJS(AssignmentStore.assignmentList);
    	if(assignmentList.length > 0){
    		return assignmentList.map((assignment) =>

    			(
    				(assignment.groups.length>0) ?
    				<SingleAssignmentGroup key={assignment.id} assignment={assignment} />
    				:(<span></span>)
    			)
    		)
    	}else{
    		return (<span>No Assignments</span>)
    	}
	}


	render(){

		return(
			<div className="standardTopGap">
				{this.renderAssignmentList()}
			</div>
		)
	}
}

export default GroupingStudent;
