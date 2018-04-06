import React, {Component} from 'react';
import {Card, CardActions, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {ListGroup, ListGroupItem, Col} from 'react-bootstrap';

class GroupingListInstructor extends Component {

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

	render(){
		const group = this.props.group;
		const members = group.members;
		const username = localStorage.getItem('username');
		const memberSize = group.members.length;
		const subtitle = "Group Size: " + memberSize.toString()

		return(
			<Col xs={12} md={6}>
				<Card className="standardTopGap">
				    <CardTitle title={group.title} subtitle={subtitle} />
				    <CardText>
				      	<ListGroup>
						  {this.renderStudentList(group)}
						</ListGroup>
				    </CardText>
				    <CardActions>
				      <FlatButton label="Delete Group" />
				    </CardActions>
				  </Card>
			</Col>
		)
	}
}

export default GroupingListInstructor;