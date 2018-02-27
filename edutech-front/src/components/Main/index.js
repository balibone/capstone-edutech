import React, {Component} from 'react';
import { Switch, Route } from 'react-router-dom';
import { Grid, Col } from 'react-bootstrap';

import ProfilePanel from './ProfilePanel';
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

@observer
class Main extends Component {

  componentDidMount(){
    ScheduleItemStore.populateScheduleItems();
    MeetingStore.populateMeetings();
  }

  render(){
    const { user, moduleDetails, groupDetails } = this.props;
    console.log("MeetingStore in main: ", MeetingStore);
    return(
      <Grid>
        <Col md={3}>
          <Switch>
            <Route exact path="/" render={() => <ProfilePanel img={user.img} primaryInfo={user.name} secondaryInfo={user.email} />} />
            <Route exact path="/module/:moduleCode" render={() => <ProfilePanel img={moduleDetails.img} primaryInfo={moduleDetails.code} secondaryInfo={moduleDetails.name} />} />
            <Route exact path="/group/:groupId" render={() => <ProfilePanel img={groupDetails.img} primaryInfo={groupDetails.name} secondaryInfo={groupDetails.description} />} />
          </Switch>
          <MembersPanel />
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
