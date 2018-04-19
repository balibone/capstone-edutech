import React from 'react';
import { Switch, Route } from 'react-router-dom';

import HomeScene from '../../scenes/HomeScene';
import ModuleScene from '../../scenes/ModuleScene';
import GroupScene from '../../scenes/GroupScene';
import CollabScene from '../../scenes/CollabScene';
import MyScheduleScene from '../../scenes/MyScheduleScene';
import MyTasksScene from '../../scenes/MyTasksScene';
import TopBar from './TopBar';
import CollabTopBar from './CollabTopBar';
import LeftBar from './LeftBar';
import GroupStore from '../../stores/GroupStore/GroupStore';

const MainPage = () => (
  <div className="App">
    <Switch>
      <Route exact path="/room/:groupId" component={CollabTopBar} />
      <TopBar />
    </Switch>
    <Switch>
      <Route exact path="/room/:groupId" render={() => ''} />
      <LeftBar scene="home" />
    </Switch>
    <div>
      <Switch>
          <Route exact path="/" component={HomeScene} />
          <Route path="/module/:moduleCode" component={ModuleScene} />
          <Route path="/group/:groupId" component={GroupScene} />
          <Route exact path="/myschedule" component={MyScheduleScene} />
          <Route exact path="/mytasks" component={MyTasksScene} />
      </Switch>
    </div>
    <div>
      <Switch>
        <Route exact path="/room/:groupId" component={CollabScene} />
      </Switch>
    </div>
  </div>
);


export default MainPage;
