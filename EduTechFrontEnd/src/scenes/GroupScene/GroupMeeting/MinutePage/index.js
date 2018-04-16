import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';
import { Row, Col, Button, ControlLabel } from 'react-bootstrap';
import Multiselect from 'react-widgets/lib/Multiselect';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import momentLocalizer from 'react-widgets-moment';
import moment from 'moment';

import MeetingStore from '../../../../stores/MeetingStore/MeetingStore';
import MinuteStore from '../../../../stores/MeetingStore/MinuteStore';
import AgendaArea from './AgendaArea';
import UploadFileBtn from './UploadFileBtn';

moment.locale('en');
momentLocalizer();

@observer
class MinutePage extends Component {
  constructor(props) {
    super(props)
    this.state = {
      memberNamesArr: [],
      memberNamesData: [],
      startTime: '',
      endTime: '',
    }
  }

  componentDidMount() {
		const meeting = toJS(MeetingStore.selectedMeeting);
		const members = meeting.assignedTo;
		const memberNamesArr = [];

		members.forEach((member) => {
			memberNamesArr.push(member.username)
		})
		this.setState({
      memberNamesArr,
      memberNamesData: memberNamesArr,
      startTime: meeting.meetingMinute.startTime,
      endTime: meeting.meetingMinute.endTime,
    });
	}

  closeMinutePage() {
    this.props.closeMinutePage();
  }

  addMinute() {
    const { startTime, endTime, memberNamesArr } = this.state;
    const meetingId = MeetingStore.selectedMeeting.id;
    MinuteStore.addMeetingMinute(meetingId, startTime, endTime, memberNamesArr);
  }

  render() {
    const minuteId = MeetingStore.selectedMeeting.meetingMinute.id;
    const { agendas } = MeetingStore.selectedMeeting.meetingMinute;
    console.log('selected meeting Agenda in minutePage: ', toJS(MeetingStore.selectedMeeting))
    return (
      <div>
        <h2>Add Meeting Minute - {MeetingStore.selectedMeeting.title}</h2>
        <div className="text-right">
          <i className="fas fa-times fa-1x btnHover" onClick={() => this.closeMinutePage()} />
        </div>
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
              onChange={value => this.setState({ endTime: new Date(value) })}
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
        onChange={dataItems => this.setState({ memberNamesArr: dataItems })}
      />
        <AgendaArea minuteId={minuteId} agendas={agendas} groupId={this.props.groupId} />
{/*        <div className="well">
          <UploadFileBtn minuteId={minuteId} />
        </div>
      */}
        <Button onClick={() => this.closeMinutePage()}>Close</Button> &nbsp;
					<Button bsStyle="primary" onClick={() => this.addMinute()}>Apply Changes</Button>
      </div>
    );
  }
}

export default MinutePage;
