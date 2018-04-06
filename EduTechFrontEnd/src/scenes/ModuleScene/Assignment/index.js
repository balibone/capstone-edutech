import React, {Component} from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import {Button ,Glyphicon, Modal} from 'react-bootstrap';

import CreateAssignmentForm from './CreateAssignmentForm';
import CreateAssessmentForm from './CreateAssessmentForm';
import AssignmentListView from './AssignmentListView';
import Submission from '../Submission';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

@observer
class Assignment extends Component {

	constructor(){
		super()
		this.state = {
			openAssessmentForm: false,
			showAssignmentForm: false
		}
	}

	flipShowAssignmentFormState(){
		this.setState({showAssignmentForm: !this.state.showAssignmentForm})
	}

	flipShowAssessmentFormState(){
		this.setState({openAssessmentForm: !this.state.openAssessmentForm})	
	}

	getSelectedModule(moduleCode){
		const moduleList = JSON.parse(localStorage.getItem('moduleList'));
		return moduleList.find((module) => module.moduleCode === moduleCode)
	}

	renderCreateButton(assignmentList){

	    return (
	      <div>
	        <Button bsStyle="primary" onClick ={this.flipShowAssignmentFormState.bind(this)}>
	          Create Assignment
	          <Glyphicon glyph="plus" style={{marginLeft: '5px'}}/>
	        </Button>
	        &nbsp;
	        <Button bsStyle="primary" onClick ={this.flipShowAssessmentFormState.bind(this)}>
	          Create Assessment
	          <Glyphicon glyph="plus" style={{marginLeft: '5px'}}/>
	        </Button>
	        <Submission assignmentList={assignmentList}/>
	      </div>
	      )
	 }


	render(){
		const selectedModule = this.getSelectedModule(this.props.moduleCode)
		const assignmentList = toJS(AssignmentStore.assignmentList);

		return(
			<div className="standardTopGap">
				{
					this.state.showAssignmentForm ? 
					<CreateAssignmentForm moduleCode={this.props.moduleCode} selectedModule={selectedModule} flipShowAssignmentFormState={this.flipShowAssignmentFormState.bind(this)}/> 
					: this.renderCreateButton(assignmentList) 
				}	

				<Modal show={this.state.openAssessmentForm} onHide={this.flipShowAssessmentFormState.bind(this)}>
		          <Modal.Header closeButton>
		            <Modal.Title>Add Assessment Item </Modal.Title>
		          </Modal.Header>
		          <CreateAssessmentForm moduleCode={this.props.moduleCode} handleClose={this.flipShowAssessmentFormState.bind(this)} />
		        </Modal>

			</div>
		)
	}
}

export default Assignment;