import React, {Component} from 'react';
import {Card, CardActions, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {ListGroup, ListGroupItem, Col} from 'react-bootstrap';

class GroupingListInstructor extends Component {

	render(){

		return(
			<Col xs={12} md={6}>
				<Card className="standardTopGap">
				    <CardTitle title="Group 1" subtitle="Group Size: 4" />
				    <CardText>
				      	<ListGroup>
						  <ListGroupItem>Nan Da</ListGroupItem>
						  <ListGroupItem>Hafidz</ListGroupItem>
						  <ListGroupItem>Derian</ListGroupItem>
						  <ListGroupItem>Winston</ListGroupItem>
						</ListGroup>
				    </CardText>
				    <CardActions>
				      <FlatButton label="Broadcast Announcement" />
				      <FlatButton label="Delete Group" />
				    </CardActions>
				  </Card>
			</Col>
		)
	}
}

export default GroupingListInstructor;