import React, {Component} from 'react';
import {Panel, Button} from 'react-bootstrap';
import moment from 'moment';

import UploadFileBtn from './UploadFileBtn';

class SingleLesson extends Component {


	renderAttachmentBtn(){
		return (
			<div className="pull-right">
				<UploadFileBtn />
				<Button className="standardTopGap" bsStyle="primary">Manage Session</Button>
			</div>
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