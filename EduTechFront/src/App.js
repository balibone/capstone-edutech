import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import Navbar from './components/Navbar';
import Main from './components/Main';

import { getUserDetails } from './services/api/user';
import { getUserModules, getModuleDetails } from './services/api/module';
import { getUserGroups, getGroupDetails } from './services/api/group';


const App = () => (
  <MuiThemeProvider>
    <Navbar user={getUserDetails(1)} modules={getUserModules(1)} groups={getUserGroups(1)} />
    <Main user={getUserDetails(1)} moduleDetails={getModuleDetails('IS4100')} groupDetails={getGroupDetails(1)} />
  </MuiThemeProvider>
);

export default App;
