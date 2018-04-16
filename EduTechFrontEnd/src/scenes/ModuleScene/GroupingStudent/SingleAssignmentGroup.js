import React, { Component } from 'react';
import { Label, Row } from 'react-bootstrap';

import GroupingListStudent from './GroupingListStudent';


class SingleAssignmentGroup extends Component {
	checkUserInAssignment(moduleGrouping) {
		const username = localStorage.getItem('username');
		let studentExist = false;
		for (let i = 0; i < moduleGrouping.length; i++) {
			studentExist = moduleGrouping[i].members.find((member) => {
				if (member.username === username) {
					return true;
				}
			})
			if (studentExist) {
				return true;
			}
		}

		return false;
	}

	render() {
		// console.log("props assignment: ", this.props.assignment)
		const { assignment } = this.props;
		const moduleGrouping = assignment.groups;
		const studentExist = this.checkUserInAssignment(moduleGrouping);
		// console.log("STUDENT EXIST: ", studentExist);
		return (
			<div>
				<h3>
			    	{assignment.title}
			    	<h5><Label bsStyle="default">Max Group Size: {moduleGrouping[0].groupSize}</Label></h5>
				</h3>
				<Row>
					{
						(moduleGrouping.length > 0) ? (
							moduleGrouping.map(group =>
								(<GroupingListStudent key={group.id} group={group} assignmentId={assignment.id} studentExist={studentExist} moduleCode={assignment.module.moduleCode}/>)
							)
							)
						: ''
					}
				</Row>
				<hr />
			</div>
		)
	}
}

export default SingleAssignmentGroup;
