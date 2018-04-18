import React, { Component } from 'react';
import { toJS } from 'mobx';
import { observer } from 'mobx-react';
import { Button ,Glyphicon, Modal } from 'react-bootstrap';
import RaisedButton from 'material-ui/RaisedButton';
import ContentAddCircle from 'material-ui/svg-icons/content/add-circle';

import CreateAssignmentForm from './CreateAssignmentForm';
// import CreateAssessmentForm from '../RightPanel/CreateAssessmentForm';
import AssignmentListView from './AssignmentListView';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';
import ModuleStore from '../../../stores/ModuleStore/ModuleStore';

@observer
class Assignment extends Component {
	constructor() {
		super()
		this.state = {
			showAssignmentForm: false,
		}
	}

	flipShowAssignmentFormState() {
		this.setState({ showAssignmentForm: !this.state.showAssignmentForm })
	}

	renderCreateButton() {
		const userType = localStorage.getItem('userType');
		if (userType === 'instructor') {
			return (
					<RaisedButton
				      label="Create Assignment"
				      labelPosition="before"
				      primary
							onClick={() => this.flipShowAssignmentFormState()}
				      icon={<ContentAddCircle />}
				  />
	      )
		}
		return '';
	 }


	render() {
		const { selectedModule } = ModuleStore;
		const assignmentList = toJS(AssignmentStore.assignmentList);

		return (
			<div className="standardTopGap">
				<div>
					{
						this.state.showAssignmentForm ?
						<CreateAssignmentForm
						  moduleCode={this.props.moduleCode}
						  selectedModule={selectedModule}
						  flipShowAssignmentFormState={() => this.flipShowAssignmentFormState()}
						/>
						: this.renderCreateButton()
					}
					<AssignmentListView assignmentList={assignmentList} />
				</div>
			</div>
		)
	}
}

export default Assignment;
