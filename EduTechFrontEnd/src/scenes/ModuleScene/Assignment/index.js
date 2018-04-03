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

	getSelectedModule(moduleCode){
		const moduleList = JSON.parse(localStorage.getItem('moduleList'));
		return moduleList.find((module) => module.moduleCode === moduleCode)
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
		var selectedModule = this.getSelectedModule(this.props.moduleCode)
		return(
			<div className="standardTopGap">
				{
					this.state.showAssignmentForm ? 
					<CreateAssignmentForm moduleCode={this.props.moduleCode} selectedModule={selectedModule} flipShowAssignmentFormState={this.flipShowAssignmentFormState.bind(this)}/> 
					: this.renderCreateButton() 
				}			
			</div>
		)
	}
}

export default Assignment;