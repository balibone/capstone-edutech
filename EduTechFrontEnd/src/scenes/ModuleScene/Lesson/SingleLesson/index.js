import React, {Component} from 'react';
import {Panel, Button, Grid, Row, Col} from 'react-bootstrap';
import moment from 'moment';

import UploadFileBtn from './UploadFileBtn';

import './styles.css';

class SingleLesson extends Component {


	renderAttachmentBtn(){
		const wellStyles = { maxWidth: 400, margin: '0 auto 10px' };
		return (

		        <Row className="show-grid">
		          <Col xs={12} md={6}>
		            show uploaded files here
		          </Col>
		          <Col xs={6} md={6}>
		            <div className="well" style={wellStyles}>
		              <UploadFileBtn />
		            </div>        
		            <Button className="pull-right" >Download All Attachments</Button>
					<Button className="standardTopGap" bsStyle="primary">Manage Session</Button>
		          </Col>
		        </Row>

		)
	}

	render(){
		const {id, title, startDate, endDate} = this.props.lesson;
		const start = moment(startDate).format("h:mm a");
        const end = moment(endDate).format("h:mm a");
        const date = moment(startDate).format("dddd, Do MMMM");

		return(
			<Panel eventKey={id}>
		    	<Panel.Heading>
		      		<Panel.Title toggle>{title} <p className="smallText">{date}, {start} - {end} </p>
			      		
		      		</Panel.Title>
		    	</Panel.Heading>
		    	<Panel.Body collapsible>
		      		{this.renderAttachmentBtn()}
		    	</Panel.Body>
		 	</Panel>
		)
	}
}

export default SingleLesson;