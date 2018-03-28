import React, {Component} from 'react';
import {ListGroup, ListGroupItem, Col, Button, ButtonToolbar} from 'react-bootstrap';

class GroupingListStudent extends Component {


	render(){

		return(
			<Col xs={12} md={6}>
				<ListGroup>
					<ListGroupItem header="Group 1">2 members
						<ButtonToolbar className="pull-right" style={{marginTop: '-20px'}}>
						    <Button bsStyle="primary" bsSize="small">
						      Join Group
						    </Button>
						</ButtonToolbar>
					</ListGroupItem>
					<ListGroupItem bsStyle="info">Nan Da</ListGroupItem>
					<ListGroupItem bsStyle="info">Hafidz</ListGroupItem>
    				<ListGroupItem bsStyle="warning">vacant</ListGroupItem>
    				<ListGroupItem bsStyle="warning">vacant</ListGroupItem>
				</ListGroup>
			</Col>
		)
	}
}

export default GroupingListStudent;