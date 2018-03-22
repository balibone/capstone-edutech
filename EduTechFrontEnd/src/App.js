import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import Navbar from './components/Navbar';
import Main from './components/Main';
import Home from './Home';


import { getUserDetails } from './services/api/user';
import { getUserModules, getModuleDetails } from './services/api/module';
import { getUserGroups, getGroupDetails } from './services/api/group';

import GroupStore from './stores/GroupStore/GroupStore';
import ModuleStore from './stores/ModuleStore/ModuleStore';
import UserStore from './stores/UserStore/UserStore';


function getCookie(name) {
  const value = "; " + document.cookie;
  const parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
  return false;
}

let App = <span></span>;

if (getCookie('username')) {
  localStorage.setItem('username', getCookie('username'));
  const { currentUser } = UserStore;
  GroupStore.populateGroup();
  ModuleStore.populateModule();

  App = () => (
    <MuiThemeProvider>
      <Navbar userStore={UserStore} user={getUserDetails(1)} modules={getUserModules(1)} groups={getUserGroups(1)} />
      <Main user={getUserDetails(1)} moduleDetails={getModuleDetails('IS4100')} groupDetails={getGroupDetails(1)} />
    </MuiThemeProvider>
  );

} else {
  window.location.replace('http://localhost:8080/EduTechWebApp-war/');
}



export default App;
