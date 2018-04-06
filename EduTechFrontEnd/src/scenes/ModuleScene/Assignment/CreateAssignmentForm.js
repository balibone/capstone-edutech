import React, {Component} from 'react';
import {observer} from 'mobx-react';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import { Button, ControlLabel, FormControl } from 'react-bootstrap';
import {RadioButton, RadioButtonGroup} from 'material-ui/RadioButton';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import { DateTimePicker } from 'react-widgets';

import 'react-widgets/dist/css/react-widgets.css';
import './styles.css';

import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';

moment.locale('en');
momentLocalizer();

const styles = {
  block: {
    maxWidth: 250,
  },
  radioButton: {
    marginBottom: 16,
  },
};

const wellStyles = { maxWidth: 400, marginLeft: '10px' };

@observer
class CreateAssignmentForm extends Component {

	constructor(){
		super()
		this.state = {
			title: "",
			type: "individual",
			groupSize: 2,
			deadline: new Date()
		}
	}

	checkGroup(event){
		this.setState({type: event.target.value})
	}

	handleGroupNumChange(event, index, value){
		console.log(value)
		this.setState({groupSize: value})
	}

	handleClose(){
		this.props.flipShowAssignmentFormState();
	}

	createAssignment(event){
		event.preventDefault();
		var {title, type, deadline, groupSize} = this.state;
		const currentDate = new Date();
		const username = localStorage.getItem('username');
		const moduleCode = this.props.moduleCode;
		var memberSize = 0;
		console.log("selectedModule in assignment", this.props.selectedModule)
		if(this.props.selectedModule){
			
			memberSize = this.props.selectedModule.members.length;
		}

		//temporary step
		var tempMemberSize = memberSize + 11;
		// console.log("temp size: ", tempMemberSize);
		AssignmentStore.createAssignment(currentDate, deadline, username, moduleCode, title, tempMemberSize, groupSize, type);
	}

	render(){

		return(
			<div>
				<div className="text-right" >
				    	<i className="fas fa-times fa-1x standardRightGap btnHover" onClick={this.handleClose.bind(this)}></i>
				</div>
				<ControlLabel>Assignment Title</ControlLabel>
		          <FormControl
		            type="text"
		            value={this.state.title}
		            placeholder="Enter text"
		            onChange={(e) => this.setState({title: e.target.value})}
		          />

			    <label htmlFor="deadline" >Set Deadline</label>
			    <div id="deadling" className="smallTopGap">
			    	<DateTimePicker
		              min={new Date()}
		              placeholder="Set Deadline"
		              step={30}
		              onChange={value => this.setState({ deadline: new Date(value) })}
		              value={new Date(this.state.deadline)}
		            />
			    </div>
			    
			    <div className="well standardTopGap" style={wellStyles}>
			    	<RadioButtonGroup className="standardTopGap" name="isGroup" defaultSelected="individual" onChange={this.checkGroup.bind(this)}>
				      <RadioButton
				        value="individual"
				        label="Individual Assignment"
				        style={styles.radioButton}
				      />
				      <RadioButton
				        value="group"
				        label="Group Assignment"
				        style={styles.radioButton}
				      />
				    </RadioButtonGroup>
				    {
				    	this.state.type === "group" ?
				    	(	
				    		<div>
				    		<label htmlFor="groupDropDown" style={{marginBottom: '10px'}}>Select Maximum Group Size</label>
				    		<DropDownMenu id="groupDropDown" value={this.state.groupSize} onChange={this.handleGroupNumChange.bind(this)}>
					          <MenuItem value={2} primaryText="2 People" />
					          <MenuItem value={3} primaryText="3 People" />
					          <MenuItem value={4} primaryText="4 People" />
					          <MenuItem value={5} primaryText="5 People" />
					          <MenuItem value={6} primaryText="6 People" />
					        </DropDownMenu>
					        </div>
				    	):(<span></span>)
				    }	
			    </div>
			    <Button className="standardTopGap" bsStyle="primary" onClick={this.createAssignment.bind(this)}>Create Assignment</Button>
			</div>
		)
	}
}

export default CreateAssignmentForm;