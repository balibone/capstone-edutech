import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import { Grid, Col } from 'react-bootstrap';

import ProfilePanel from './ProfilePanel';
import GroupProfilePanel from './GroupProfilePanel';
import MembersPanel from './MembersPanel';
import KeyInfoPanel from './KeyInfoPanel';
import CalendarPanel from './CalendarPanel';
import TaskPanel from '../TaskPanel';
import { observer } from 'mobx-react';

import PersonalScene from '../../scenes/PersonalScene';
import ModuleScene from '../../scenes/ModuleScene';
import GroupScene from '../../scenes/GroupScene';

import ScheduleItemStore from '../../stores/ScheduleItemStore/ScheduleItemStore';
import MeetingStore from '../../stores/MeetingStore/MeetingStore';
import GroupStore from '../../stores/GroupStore/GroupStore';


@observer
class Main extends Component {

  componentDidMount() {
    const username = localStorage.getItem('username');
    ScheduleItemStore.populateScheduleItems(username);
  }

  getSelectedGroup(groupId) {
    const groupList = JSON.parse(localStorage.getItem('groupList'))
    console.log('groupListgroupList', groupList[0].id, "rwgwrg", groupId)
    return groupList.find((group) => group.id === parseInt(groupId))
  }

  render(){
    const { user, moduleDetails, groupDetails } = this.props;
    console.log("MeetingStore in main: ", MeetingStore);
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const { imgfilename, userfirstname, userlastname, username} = currentUser;
    return(
      <Grid>
        <Col md={3}>
          <Switch>
            <Route exact path="/" render={() => <ProfilePanel img={imgfilename} primaryInfo={userfirstname + " " + userlastname} secondaryInfo={`@${username}`} />} />
            <Route exact path="/module/:moduleCode" render={() => (<ProfilePanel img={moduleDetails.img} primaryInfo={moduleDetails.code} secondaryInfo={moduleDetails.name} />)} />
            <Route exact path="/group/:groupId" render={(props) => {
              console.log('YAaaaaz')
              const { groupId } = props.match.params
              const group = this.getSelectedGroup(groupId)

              console.log("WHAT IS WRONG?", group);
              return(<div><GroupProfilePanel group={group} img={group.imagefilename} primaryInfo={group.title} secondaryInfo={group.description} /> <MembersPanel groupStore={GroupStore} getSelectedGroup={this.getSelectedGroup} {...props}/></div>)}
            }/>
          </Switch>
        </Col>
        <Col md={6} >
          <KeyInfoPanel ScheduleItemStore={ScheduleItemStore}/>
          <Switch>
            <Route exact path="/" component={PersonalScene} />
            <Route path="/module/:moduleCode" component={ModuleScene} />
            <Route path="/group/:groupId" render={(props) => <GroupScene meetingStore={MeetingStore}{...props} />} />
          </Switch >
        </Col>
        <Col md={3}>
          <CalendarPanel />
          <TaskPanel />
        </Col>
      </Grid>
    )
  }
}

export default Main;
