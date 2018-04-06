import React, {Component} from 'react';
import {observer} from 'mobx-react';
import {ListGroup, ListGroupItem, Col, Button, ButtonToolbar} from 'react-bootstrap';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

@observer
class GroupingListStudent extends Component {

	// constructor(props){
	// 	super(props)
	// 	this.state = {
	// 		group: this.props.group
	// 	}
	// }

	joinGroup(){
		const username = localStorage.getItem('username');
		const groupId = this.props.group.id;
		const assignmentId = this.props.assignmentId;
		console.log("add this student", username);
		console.log("to this group", this.props.group)
		AssignmentStore.joinAssignmentGroup(username, groupId, assignmentId);
	}

	leaveGroup(){
		const username = localStorage.getItem('username');
		const groupId = this.props.group.id;
		const assignmentId = this.props.assignmentId;
		console.log("remove this student", username);
		console.log("from this group", this.props.group)
		AssignmentStore.leaveAssignmentGroup(username, groupId, assignmentId);
	}

	renderStudentList(group){
		const { groupSize, members } = group;
		var studentListItem = [];

		for(var i=0 ; i<groupSize ; i++){
			if(members[i]){
				studentListItem.push(members[i].username)
			} else {
				studentListItem.push('vacant')
			}
		}

		return studentListItem.map((student) => {
			const style = student === 'vacant' ? 'warning': 'info'
			return <ListGroupItem bsStyle={style}>{student}</ListGroupItem>
		})	
	}

	renderJoinButton(username, members){
		let studentExist = this.props.studentExist;
		let group = this.props.group;
		let inGroupExist = false;
		inGroupExist = members.find((member) => {
			if(member.username === username)
				return true;
		})

		if(studentExist && inGroupExist){
			return (
				<ButtonToolbar className="pull-right" style={{marginTop: '-20px'}}>
					<Button bsStyle="warning" bsSize="small" onClick={this.leaveGroup.bind(this)}>
				      Leave Group
				    </Button>
			    </ButtonToolbar>
				)
		}else if(studentExist) {
			return (<span></span>)
		}else {
			return (
				<ButtonToolbar className="pull-right" style={{marginTop: '-20px'}}>
					<Button bsStyle="primary" bsSize="small" onClick={this.joinGroup.bind(this)}>
				      Join Group
				    </Button>
			    </ButtonToolbar>
			)
		}
	}

	render(){
		const group = this.props.group;
		const members = group.members;
		const username = localStorage.getItem('username');
		var memberSize = group.members.length;
		// console.log("Group in GroupingListStudent: ", group)
		// if(group){
		// 	memberSize = group.members.length;
		// }
		return(
			<Col xs={12} md={6}>
				<ListGroup>
					<ListGroupItem header={group.title}>Number of Students: {memberSize}		
						{this.renderJoinButton(username, members)}					
					</ListGroupItem>
					{this.renderStudentList(group)}
				</ListGroup>
			</Col>
		)
	}
}

export default GroupingListStudent;