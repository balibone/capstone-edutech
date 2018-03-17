import React, {Component} from 'react';
import {Table, thead, tbody, Button, Panel, PanelGroup} from 'react-bootstrap';
import SingleAssignment from './SingleAssignment';

class Submission extends Component {

	render(){

		return(
			<div className="standardTopGap">
			<h4>Students Submissions</h4>
				<PanelGroup accordion id="accordion-example">

			 		<Panel eventKey={1}>
				    	<Panel.Heading>
				      		<Panel.Title toggle>Assignment 1</Panel.Title>
				    	</Panel.Heading>
				    	<Panel.Body collapsible>
				    		<SingleAssignment />
				    	</Panel.Body>
			 		</Panel>

			 		<Panel eventKey={2}>
				    	<Panel.Heading>
				      		<Panel.Title toggle>Assignment 2</Panel.Title>
				    	</Panel.Heading>
				    	<Panel.Body collapsible>
				    		<SingleAssignment />
				    	</Panel.Body>
			 		</Panel>

				</PanelGroup>
			</div>
		)
	}
}

export default Submission;