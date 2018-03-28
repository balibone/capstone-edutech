import React, {Component} from 'react';
import {Panel, Button, Grid, Row, Col} from 'react-bootstrap';
import moment from 'moment';

import UploadFileBtn from './UploadFileBtn';

class SingleLesson extends Component {


	renderAttachmentBtn(){
		const wellStyles = { maxWidth: 400, margin: '0 auto 10px' };
		return (

		        <Row className="show-grid">
		          <Col xs={12} md={6}>
		            
		          </Col>
		          <Col xs={6} md={6}>
		            <div className="well" style={wellStyles}>
		              <UploadFileBtn />
		            </div>        
					<Button className="standardTopGap" bsStyle="primary">Manage Session</Button>
		          </Col>
		        </Row>

		)
	}

	render(){
		return(
			<Panel eventKey={this.props.lesson.id}>
		    	<Panel.Heading>
		      		<Panel.Title toggle>{this.props.lesson.title} - {moment(this.props.lesson.dateTime).format('dddd, Do MMMM')}
			      		<Button className="pull-right" style={{marginTop: '-5px'}}>Download All Attachments</Button>
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