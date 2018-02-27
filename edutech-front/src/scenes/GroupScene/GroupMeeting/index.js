import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';
import axios from 'axios';
import moment from 'moment';
import swal from 'sweetalert';
import { Tabs, Tab, Col, Row, Button, FormControl, ControlLabel, FormGroup } from 'react-bootstrap';
import { DateTimePicker, DropdownList } from 'react-widgets';
import momentLocalizer from 'react-widgets-moment';
import './styles.css';
import 'react-widgets/dist/css/react-widgets.css';

import MeetingCard from './MeetingCard';
import MeetingStore from '../../../stores/MeetingStore/MeetingStore';

moment.locale('en');
momentLocalizer();

@observer
class GroupMeeting extends Component{

  constructor(){
      super()
      this.state = {
        viewAll : false,
        startTime: null,
        endTime: null
      }
  }

  addMeetingItem(){
    var name = this.refs.name.value.trim();
    var description = this.refs.description.value.trim();
    var startTime = this.state.startTime;
    var endTime = this.state.endTime;
    var location = this.refs.description.value.trim();
    var recurring = false;
    var user = this.refs.recurring.checked;
    var createdOn = moment(new Date()).format("MMM DD YYYY HH:mm a");
    var groupId = "224488"
    var meetingMinutes = []
    var dataSet = [name, description, startTime, endTime, location, recurring, user, createdOn, groupId, meetingMinutes]
    
    axios.post('/v2/addMeetingItem', dataSet)
    .then((res) => {
      MeetingStore.addMeeting(name, description, startTime, endTime, location, recurring, user, createdOn, groupId, meetingMinutes);
      // scheduleItemStore.addScheduleItem(name, description, startTime, endTime, location, recurring, user, createdOn);
      swal("Success!", "Meeting sucessfully created." , "success");
    })
    .catch((err) => {
      console.log(err)
      swal("Error", "Unable to connect to the server.", "error")
    })
  }

  mergeTeamCalendar(){
    
  }


  renderMeetingInput() {
    return (
      <div className="paperDefault">
        <form>
            <div className="form-group row mt-1">
            <label htmlFor="eventName" className="col-2 col-form-label">Meeting Title:</label>
            <div className="col-8">
              <input className="form-control" type="text" id="eventName" ref="name" />
            </div>
          </div>

          <div className="form-group row mt-1">
            <label htmlFor="location" className="col-2 col-form-label">Location:</label>
            <div className="col-8">
              <input className="form-control" type="text" id="location" ref="location" />
            </div>
          </div>

          <div className="form-group row mt-1">
            <label htmlFor="comment">Description:</label>
            <textarea className="form-control" rows="5" id="comment" ref="description"></textarea>
          </div>

          <div className="form-check">
              <label className="form-check-label">
                <input type="checkbox" className="form-check-input" ref="recurring"/>
                Recurring
              </label>
            </div>

        </form>

        <Row className="smallTopGap">
          <Col md={6}>
            <DateTimePicker
              min={new Date()}
              placeholder="Set Start Time"
              onChange={value => this.setState({ startTime: value })}
              value={this.state.startTime}
            />
          </Col>
          <Col md={6}>
            <DateTimePicker
              min={new Date()}
              placeholder="Set End Time"
              onChange={value => this.setState({ endTime: value })}
              value={this.state.endTime}
            />
          </Col>
        </Row>
        
        

        <Row className="smallTopGap">
          <Col md={12}>
            <Button onClick={this.mergeTeamCalendar.bind(this)}>
              Merge Team Calendar
            </Button>
            <Button bsStyle="primary" className="pull-right" onClick={this.addMeetingItem.bind(this)}>
              Create Meeting
            </Button>
          </Col>
        </Row>
      </div>
    );
  }


  render(){
    let meetingsObservable = MeetingStore.meetings;
    var meetings= toJS(meetingsObservable);
    console.log("MeetingStore in meeting : ", meetings);
    if(meetings === null)
      meetings = []

    return(
      <div className="standardTopGap">
        { this.renderMeetingInput() }

        {
            meetings.map((meeting, index) =>{
              return (<MeetingCard key={meeting._id} meeting={meeting} />);
            })  
        }

  
      </div>

    )
  }
}

export default GroupMeeting;
