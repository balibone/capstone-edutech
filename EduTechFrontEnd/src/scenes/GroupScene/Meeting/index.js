import React, { Component } from 'react';
import { Button, Glyphicon } from 'react-bootstrap';
import { toJS } from 'mobx';
import { observer } from 'mobx-react';
import MeetingForm from './MeetingForm';
import MeetingCard from './MeetingCard';

import MeetingStore from '../../../stores/MeetingStore/MeetingStore';

@observer
class Meeting extends Component {
  state = {
    showMeetingForm: false,
  }
  flipShowMeetingFormState() {
    this.setState({ showMeetingForm: !this.state.showMeetingForm })
  }
  renderMeetingForm() {
    return (<MeetingForm groupId={this.props.groupId} />);
  }
  renderMeetingCard(meetings) {
    if (meetings && meetings.length > 0) {
      return (
        meetings.map((meeting) => {
          return (
            <MeetingCard
              key={meeting.id}
              meeting={meeting}
              groupId={this.props.groupId}
            />
            );
        })
      )
    }
    return (<h2>No Meeting Created.</h2>)
  }
  renderCreateButton() {
    const meetings = toJS(MeetingStore.meetingsItems); // check if meetings belong to corrrect group

    return (
      <div>
        <Button bsStyle="primary" onClick={this.flipShowMeetingFormState.bind(this)}>
          Create Meeting
          <Glyphicon glyph="plus" style={{ marginLeft: '5px' }} />
        </Button>
        {this.renderMeetingCard(meetings)}
      </div>
      )
  }

  render() {
    console.log(this.state.showMeetingForm)
    return (
      <div className="standardTopGap">
        {this.state.showMeetingForm ? this.renderMeetingForm() : this.renderCreateButton() }
      </div>
    );
  }
}

export default Meeting;
