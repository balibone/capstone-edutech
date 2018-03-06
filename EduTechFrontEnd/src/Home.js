import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import { observer } from 'mobx-react';
import Spinner from 'react-spinkit';
import { Wave } from 'better-react-spinkit'
import Navbar from './components/Navbar';
import Main from './components/Main';

import { getUserDetails } from './services/api/user';
import { getUserModules, getModuleDetails } from './services/api/module';
import { getUserGroups, getGroupDetails } from './services/api/group';

import GroupStore from './stores/GroupStore/GroupStore';
import UserStore from './stores/UserStore/UserStore';

@observer
class Home extends Component {

  render(){

    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const groupList = JSON.parse(localStorage.getItem('groupList'));


    return (
      <MuiThemeProvider>
        <Navbar userStore={UserStore} user={getUserDetails(1)} modules={getUserModules(1)} groups={getUserGroups(1)} />
        <Main user={getUserDetails(1)} moduleDetails={getModuleDetails('IS4100')} groupDetails={getGroupDetails(1)} />
      </MuiThemeProvider>
    )
  }
}


export default Home;
