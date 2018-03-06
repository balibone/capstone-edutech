import React, { Component } from 'react';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';
import PropTypes from 'prop-types';
import { Paper } from 'material-ui';
import { Tabs, Tab } from 'react-bootstrap';

import GroupTask from './GroupTask';
import GroupMeeting from './GroupMeeting';
import GroupBrainstorm from './GroupBrainstorm';
import Feed from '../../components/Feed';
import MergeCalendar from './GroupMeeting/MergeCalendar';

import GroupStore from '../../stores/GroupStore/GroupStore';
import MeetingStore from '../../stores/MeetingStore/MeetingStore';
import MergeGroupStore from '../../stores/GroupStore/MergeGroupStore';
import ScheduleItemStore from '../../stores/ScheduleItemStore/ScheduleItemStore';
GroupStore.populateGroup();

@observer
class GroupScene extends Component {


  componentDidMount(){
    // MeetingStore.populateMeetings(this.props.match.params.groupId); 
    const username = "nanda"
    ScheduleItemStore.populateScheduleItems(username);
  }


  componentDidMount(){
    let { groupId } = this.props.match.params;

    MeetingStore.populateMeetings(groupId);
    GroupStore.getSelectedGroup(groupId);
    MergeGroupStore.getMembersInGroup(groupId);
    ScheduleItemStore.populateMergedScheduleItemsForGroup(groupId);
    ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));

    // const membersArray = toJS(GroupStore.selectedGroup.members);
    // console.log('membersArray 1', membersArray);
    // MergeGroupStore.fetchMergedCalendar(membersArray);
  }
  componentWillReceiveProps(newProps) {
    let { groupId } = this.props.match.params;

    ScheduleItemStore.populateScheduleItems(localStorage.getItem('username'));
    MeetingStore.populateMeetings(groupId);
    GroupStore.getSelectedGroup(groupId);
    MergeGroupStore.getMembersInGroup(groupId);
    ScheduleItemStore.populateMergedScheduleItemsForGroup(groupId);
    // const membersArray = toJS(GroupStore.selectedGroup.members);
    // console.log('membersArray 1', membersArray);
    // MergeGroupStore.fetchMergedCalendar(membersArray);
  }


  render() {
    const { match } = this.props;
    let selectedGroup;
    console.log('render', GroupStore.fetched)
    if(GroupStore.fetched) {

      console.log('component selected group in', GroupStore.fetched)

      selectedGroup = GroupStore.selectedGroup;
    }
    console.log('component selected group', GroupStore.groupList)
    // const meetingStore = this.props.meetingStore;

    return (
      <Paper className="standardTopGap standardBottomGap paperDefault">
        <Tabs defaultActiveKey={1} id="uncontrolled-tab-example">
          <Tab eventKey={1} title="Group">
            <Feed pageId={match.params.groupId} selectedGroup={GroupStore.selectedGroup} />
          </Tab>
          <Tab eventKey={2} title="Tasks">
            <GroupTask groupId={match.params.groupId} selectedGroup={GroupStore.selectedGroup} />
          </Tab>
          <Tab eventKey={3} title="Meetings">
            <GroupMeeting groupId={match.params.groupId} />
          </Tab>
          <Tab eventKey={4} title="Brainstorms">
            <GroupBrainstorm groupId={match.params.groupId} />
          </Tab>
          <Tab eventKey={5} title="Group Calendar">
            <MergeCalendar groupId={match.params.groupId} />
          </Tab>
        </Tabs>
      </Paper>
    )
  }
}

export default GroupScene;
