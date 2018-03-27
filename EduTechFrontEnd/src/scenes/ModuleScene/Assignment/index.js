import React, {Component} from 'react';
import {observer} from 'mobx-react';
import {Button ,Glyphicon} from 'react-bootstrap';

import CreateAssignmentForm from './CreateAssignmentForm';
import AssignmentListView from './AssignmentListView';
import Submission from '../Submission';

@observer
class Assignment extends Component {

	constructor(){
		super()
		this.state = {
			showAssignmentForm: false
		}
	}

	flipShowAssignmentFormState(){
		this.setState({showAssignmentForm: !this.state.showAssignmentForm})
	}

	renderCreateButton(){

	    return (
	      <div>
	        <Button bsStyle="primary" onClick ={this.flipShowAssignmentFormState.bind(this)}>
	          Create Assignment
	          <Glyphicon glyph="plus" style={{marginLeft: '5px'}}/>
	        </Button>
	        <Submission />
	      </div>
	      )
	 }


	render(){

		return(
			<div className="standardTopGap">
				{
					this.state.showAssignmentForm ? 
					<CreateAssignmentForm flipShowAssignmentFormState={this.flipShowAssignmentFormState.bind(this)}/> 
					: this.renderCreateButton() 
				}			
			</div>
		)
	}
}

export default Assignment;