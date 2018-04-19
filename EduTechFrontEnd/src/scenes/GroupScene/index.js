import React, { Component } from 'react';
import { Tabs, Tab, Paper, Divider, RaisedButton, Dialog, FlatButton, List, ListItem, Avatar } from 'material-ui';
import { observer } from 'mobx-react';
import { Animated } from 'react-animated-css';
import { Wave } from 'better-react-spinkit';
import { Row, Col, FormControl } from 'react-bootstrap';
import moment from 'moment';
import swal from 'sweetalert';
import { Link } from 'react-router-dom';

import GroupStore from '../../stores/GroupStore/GroupStore';
import GroupScheduleItemStore from '../../stores/ScheduleItemStore/GroupScheduleItemStore';

import Feed from '../../components/Feed';
import GroupMeeting from './GroupMeeting';
import MergeCalendar from './GroupMeeting/MergeCalendar';
import GroupBrainstorm from './GroupBrainstorm';
import GroupCalendar from './GroupCalendar';
import GroupScheduleItemsChart from './GroupScheduleItemsChart';
import GroupTask from './GroupTask';
import GroupTaskStore from '../../stores/TaskStore/GroupTaskStore';
import * as qs from 'query-string';

import {USER_IMAGE_PATH} from '../../utils/constants';
import './styles.css';


@observer
export default class GroupScene extends Component {
  state = {
    openMembersDialog: false,
    editView: false,
    tempDescription: null,
    openAgendaDialog: false,
    activeTabKey: 'Conversations',
  }
  async componentWillMount() {
    const { groupId } = this.props.match.params;
    await GroupStore.setSelectedGroup(groupId);
    await GroupTaskStore.fetchGroupTasks(groupId);
    await GroupScheduleItemStore.populateGroupScheduleItems(groupId);
    this.setState({ tempDescription: GroupStore.selectedGroup.description });
    if (qs.parse(this.props.location.search).tabKey) {
      this.setState({ activeTabKey: qs.parse(this.props.location.search).tabKey });
    }
  }
  async componentWillReceiveProps(newProps) {
    const { groupId } = newProps.match.params;
    await GroupStore.setSelectedGroup(groupId);
    await GroupTaskStore.fetchGroupTasks(groupId);
    await GroupScheduleItemStore.populateGroupScheduleItems(groupId);
    this.setState({ tempDescription: GroupStore.selectedGroup.description });
    if (qs.parse(this.props.location.search).tabKey) {
      this.setState({ activeTabKey: qs.parse(this.props.location.search).tabKey });
    }
  }
  handleEnterPress(e) {
    if (e.which === 13) {
      GroupStore.editGroupDescription(e.target.value);
      this.setState({ editView: false });
    }
  }

  renderMembersDialog() {
    const membersList = GroupStore.selectedGroup.members.map(member => (
        <ListItem
          primaryText={member.userFirstName + " " + member.userLastName}
          leftAvatar={<Avatar src={USER_IMAGE_PATH + member.imgFileName} />}
        />
      ));
    const actions = [
     <FlatButton
       label="Close"
       primary
       onClick={() => this.setState({ openMembersDialog: false })}
     />,
    ];
    return (
      <Dialog
        title={`${GroupStore.selectedGroup.title} Members`}
        actions={actions}
        modal={false}
        open={this.state.openMembersDialog}
        onRequestClose={() => this.setState({ openMembersDialog: false })}
        autoScrollBodyContent
      >
        <List>
          {membersList}
        </List>
      </Dialog>
    );
  }

  renderAgendaDialog() {
    if (GroupScheduleItemStore.sortedUpcomingMeetings[0] && GroupScheduleItemStore.sortedUpcomingMeetings[0].meetingMinute) {
      const { agendas } = GroupScheduleItemStore.sortedUpcomingMeetings[0].meetingMinute;
      const { title } = GroupScheduleItemStore.sortedUpcomingMeetings[0];
      let agendaList = <ListItem primaryText="No agenda created for this meeting" />
      if( agendas.length > 0 ) {
        agendaList = agendas.map((agenda, index) => (
          <ListItem
            primaryText={`${index+1}) ${agenda.title}`}
          />
        ));
      }

      const actions = [
       <FlatButton
         label="Close"
         primary
         onClick={() => this.setState({ openAgendaDialog: false })}
       />,
      ];
      return (
        <Dialog
          title={`${title} Agendas`}
          actions={actions}
          modal={false}
          open={this.state.openAgendaDialog}
          onRequestClose={() => this.setState({ openAgendaDialog: false })}
          autoScrollBodyContent
        >
          <Divider />
          <List>
            {agendaList}
          </List>
        </Dialog>
      );
    }
    return '';
  }

  renderUpcomingMeetings() {
    if (GroupScheduleItemStore.sortedUpcomingMeetings[0]) {
      const { title, startDate, endDate, location, id, groupId} = GroupScheduleItemStore.sortedUpcomingMeetings[0];
      return (
        <div className="sideSection">
        <div className="sideSectionItem">
          <div className="paperDefault standardTopGap">
            <div className="">
              <p>Upcoming Meeting:</p>
              <h4><b>{title}</b></h4>
              <p>
                {moment(startDate).format('Do MMMM h:mm a') + ' - '
                  + moment(endDate).format('h:mm a')}
              </p>
              <p>{location}</p>
              <RaisedButton label="View Agenda" primary style={{ margin: '15px' }} onClick={() => this.setState({ openAgendaDialog: true })} />
            </div>
          </div>
        </div>
        </div>
      )
    }
    return <p className="lead standardTopGap">No Upcoming Meetings</p>
  }

  renderGroupDescription() {
    if (this.state.editView) {
      return (
        <FormControl
          type="text"
          value={this.state.tempDescription}
          onChange={e => this.setState({ tempDescription: e.target.value })}
          onKeyPress={e => this.handleEnterPress(e)}
        />
      )
    }
    return (
        <p>
          {GroupStore.selectedGroup.description}
          <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
        </p>
    );
  }
  render() {
    const { groupId } = this.props.match.params;
    if (!GroupStore.donePopulating || !GroupTaskStore.donePopulating
        || !GroupScheduleItemStore.donePopulating) {
      return (
        <div className="fakeBody">
          <div className="initialSpinner">
            <center>
              <Wave size={100} />
              <span className="spinnerText">Loading...</span>
            </center>
          </div>
        </div>
      );
    }
    return (
      <Animated animationIn="fadeIn" animationOut="fadeOut" isVisible={groupId}>
        <div className="mainContent">
        <Row>
          <Col md={8}>
            <Paper className="animated fadeIn">
              <Tabs value={this.state.activeTabKey} onChange={tabKey => this.setState({ activeTabKey: tabKey })}>
                <Tab label="Conversations" value="Conversations" containerElement={<Link to={`/group/${groupId}?tabKey=Conversations`}/>}>
                  <div className="tabContent">
                    <Feed pageId={groupId} scene="group" />
                  </div>
                </Tab>
                <Tab label="Meetings" value="Meetings" containerElement={<Link to={`/group/${groupId}?tabKey=Meetings`}/>}>
                  <div className="tabContent">
                    <GroupMeeting groupId={groupId} />
                  </div>
                </Tab>
                <Tab label="Tasks" value="Tasks" containerElement={<Link to={`/group/${groupId}?tabKey=Tasks`}/>}>
                  <div className="tabContent">
                    <GroupTask groupId={groupId} selectedGroup={GroupStore.selectedGroup} />
                  </div>
                </Tab>
                <Tab label="Schedule" value="Schedule" containerElement={<Link to={`/group/${groupId}?tabKey=Schedule`}/>}>
                  <div className="tabContent">
                    <GroupCalendar groupId={groupId} viewChart />
                  </div>
                </Tab>
              </Tabs>
            </Paper>
          </Col>
          <Col md={4}>
            <Paper className="sideSection">
              <Row className="sideTopSection">
                <h3> {GroupStore.selectedGroup.title} </h3>
                <div style={{ padding: '10px 25px' }}>
                  {this.renderGroupDescription()}
                </div>
                <br />
                <RaisedButton className="viewMembersButton" label="View Members" onClick={() => this.setState({ openMembersDialog: true })} />
                <br />
                <br />
              </Row>
              <Divider />
              <Row>
                <div>
                  {this.renderUpcomingMeetings()}
                </div>
              </Row>
              <Divider />
              <Row className="">
                <h1> {GroupTaskStore.taskDeadlineInAWeek.length} </h1>
                <p className="lead">deadline in 7 days</p>
              </Row>
              <Divider />
              <Row className="">
                <h3> Live Collab Room </h3>
                <p> No member in room currently. </p>
                <RaisedButton label="Enter Collab Room" primary style={{ margin: '15px' }} containerElement={<Link to={`/room/${groupId}`} target="_blank" />}/>
              </Row>
            </Paper>
          </Col>
        </Row>
        {this.renderMembersDialog()}
        {this.renderAgendaDialog()}
        </div>
      </Animated>
    );
  }
}
