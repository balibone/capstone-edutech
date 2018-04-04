import React, {Component} from 'react';
import FlatButton from 'material-ui/FlatButton';
import { Col, Row } from 'react-bootstrap';
import { Modal,Button, FormGroup, FormControl,ControlLabel } from 'react-bootstrap';
import Multiselect from 'react-widgets/lib/Multiselect';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import momentLocalizer from 'react-widgets-moment';
import moment from 'moment';

import MinuteStore from '../../../../stores/MeetingStore/MinuteStore';

import UploadFileBtn from './UploadFileBtn';
import AgendaArea from './AgendaArea';

// const wellStyles = { maxWidth: 400, margin: '0 auto 10px' };

moment.locale('en');
momentLocalizer();

class Minute extends Component {

	constructor(props){
		super(props)
		this.state = {
			membersMultiSelect: [],
			memberNamesArr: [],
			memberNamesData: [],
			agenda: "",
			startTime: this.props.meeting.startDate,
			endTime: this.props.meeting.endDate
		}
	}

	componentDidMount(){
		const selectedGroup = this.getSelectedGroup();
		const members = selectedGroup.members;
		let memberNamesArr = []
		members.forEach((member) => {
			memberNamesArr.push(member.username)
		})
		this.setState({memberNamesArr: memberNamesArr, memberNamesData: memberNamesArr})
	}

	getSelectedGroup() {
		const {groupId} = this.props.meeting;
		const groupList = JSON.parse(localStorage.getItem('groupList'))
		return groupList.find((group) => group.id === parseInt(groupId))
	}

	// componentDidUpdate(prevProps, prevState){
	// 	if (prevProps.formSuccess !== this.props.formSuccess) {
	// 		this.props.handleClose();	    
	// 	}
	// }

	addMinute(){
		const {startTime, endTime} = this.state;
		const meetingId = this.props.meeting.id;
		const membersTemp = ["NanDa", "Hafidz", "Derian"]
		console.log("members multi select check", this.state.membersMultiSelect)
		MinuteStore.addMeetingMinute(meetingId, startTime, endTime, membersTemp);
	}

	render(){
	
		
		return(
			<div>
				<Modal.Body>
					 
					<Row className="smallTopGap">
			          <Col md={6}>
			            <DateTimePicker
			              min={new Date()}
			              placeholder="Set Start Time"
			              step={30}
			              onChange={value => this.setState({ startTime: new Date(value) })}
			              value={new Date(this.state.startTime)}
			            />
			          </Col>
			          <Col md={6}>
			            <DateTimePicker
			              min={new Date()}
			              placeholder="Set End Time"
			              step={30}
			              onChange={value => this.setState({ endTime:  new Date(value) })}
			              value={new Date(this.state.endTime)}
			            />
			          </Col>
			        </Row>
					
					<ControlLabel>Attendees:</ControlLabel>
					<Multiselect 
						className="standardTopGap" 
						data={this.state.memberNamesData} 
						value={this.state.memberNamesArr}
						defaultValue={this.state.memberNamesArr}
						onChange={(dataItems) => this.setState({memberNamesArr: dataItems})}
					/>

				    <AgendaArea />

	                <div className="well">
	                  <UploadFileBtn />
	                </div>        
		           
				</Modal.Body>
				<Modal.Footer>
					<Button onClick={this.props.handleClose.bind(this)}>Close</Button>
					<Button bsStyle="primary" onClick={this.addMinute.bind(this)}>Apply Changes</Button>
				</Modal.Footer>
	        </div>
		)
	}
}

export default Minute;