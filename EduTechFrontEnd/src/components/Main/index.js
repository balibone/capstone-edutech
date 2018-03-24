import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import { Grid, Col } from 'react-bootstrap';

import ProfilePanel from './ProfilePanel';
import GroupProfilePanel from './GroupProfilePanel';
import ModuleProfilePanel from './ModuleProfilePanel';
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
import ModuleStore from '../../stores/ModuleStore/ModuleStore';
import UserStore from '../../stores/UserStore/UserStore';


@observer
class Main extends Component {

  componentDidMount() {
    const username = localStorage.getItem('username');
    ScheduleItemStore.populateScheduleItems(username);
  }

  getSelectedGroup(groupId) {
    const groupList = JSON.parse(localStorage.getItem('groupList'))
    return groupList.find((group) => group.id === parseInt(groupId))
  }

  getSelectedModule(moduleCode){
    const moduleList = JSON.parse(localStorage.getItem('moduleList'));
    console.log('selected Module', moduleList);
    return moduleList.find((module) => module.moduleCode === moduleCode)
  }

  render(){
    const { user, moduleDetails, groupDetails } = this.props;

    // const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const {currentUser} = UserStore;
    if(!currentUser) {
      return <span></span>
    }
    
    const { imgFileName, userFirstName, userLastName, username} = currentUser;

    return(
      <Grid>
        <Col md={3}>
          <Switch>
            <Route exact path="/" render={() => <ProfilePanel img={imgFileName} primaryInfo={userFirstName + " " + userLastName} secondaryInfo={`@${username}`} />} />
            <Route exact path="/module/:moduleCode" render={(props) => {
              const {moduleCode} = props.match.params;
              const module = this.getSelectedModule(moduleCode);
              console.log("Selected module", module)
              return (
                <div>
                  <ModuleProfilePanel module={module} primaryInfo={module.moduleCode} secondaryInfo={module.title}/>
                  <MembersPanel getSelectedModule={this.getSelectedModule} {...props}/>
                </div>)
            }}/>
            <Route exact path="/group/:groupId" render={(props) => {
              const { groupId } = props.match.params
              const group = this.getSelectedGroup(groupId)
              return(
                <div>
                    <GroupProfilePanel group={group} img={group.imagefilename} primaryInfo={group.title} secondaryInfo={group.description} /> 
                    <MembersPanel groupStore={GroupStore} getSelectedGroup={this.getSelectedGroup} {...props}/>
                </div>)
            }}/>
          </Switch>
        </Col>
        <Col md={9} >
          <KeyInfoPanel ScheduleItemStore={ScheduleItemStore}/>
          <Switch>
            <Route exact path="/" component={PersonalScene} />
            <Route path="/module/:moduleCode" component={ModuleScene} />
            <Route path="/group/:groupId" render={(props) => <GroupScene meetingStore={MeetingStore}{...props} />} />
          </Switch>
        </Col>

    
      {/*
      <Col md={3}>
          <CalendarPanel />
          <TaskPanel />
        </Col>
      */}
        
      </Grid>
    )
  }
}

export default Main;
