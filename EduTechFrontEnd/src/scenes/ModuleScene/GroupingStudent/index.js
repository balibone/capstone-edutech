import React, {Component} from 'react';
import GroupingListStudent from './GroupingListStudent';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';

import GroupStore from '../../../stores/GroupStore/GroupStore';
import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

import SingleAssignmentGroup from './SingleAssignmentGroup';

@observer
class GroupingStudent extends Component{

	renderAssignmentList(){
		const assignmentList = toJS(AssignmentStore.assignmentList);
    	if(assignmentList.length > 0){
    		return assignmentList.map((assignment) => 
    			(<SingleAssignmentGroup key={assignment.id} assignment={assignment} />)
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