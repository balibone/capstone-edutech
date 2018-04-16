import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import { teal500, teal700, orangeA200 } from 'material-ui/styles/colors';
import { Snackbar } from 'material-ui';
import { BrowserRouter } from 'react-router-dom';
import { observer } from 'mobx-react';
import { Wave } from 'better-react-spinkit';

import MainPage from './components/MainPage';
import TaskStore from './stores/TaskStore/TaskStore';
import ScheduleItemStore from './stores/ScheduleItemStore/ScheduleItemStore';
import GroupStore from './stores/GroupStore/GroupStore';
import ModuleStore from './stores/ModuleStore/ModuleStore';
import UtilStore from './stores/UtilStore/UtilStore';
import { findUser } from './services/userApi';
import { fetchVenueSuggestions } from './services/nusmodsApi';

import './App.css';

const muiTheme = getMuiTheme({
    palette: {
        primary1Color: teal500,
        primary2Color: teal700,
        accent1Color: orangeA200,
    },
});

@observer
class App extends Component {
  async componentWillMount() {
    const { username } = this.props;
    await this.retrieveCurrentUser(username);
    await GroupStore.populateGroupList(username);
    await ModuleStore.populateModuleList(username);
    await ScheduleItemStore.populateScheduleItems(username);
    await TaskStore.populateTasks(username);
    await this.retrieveNUSVenues();
  }
  async retrieveCurrentUser(username) {
    // retrieve & store current user
    const user = await findUser(username);
    localStorage.setItem('currentUser', JSON.stringify(user.data));
  }
  async retrieveNUSVenues() {
    const venues = await fetchVenueSuggestions();
    localStorage.setItem('NUSVenues', JSON.stringify(venues.data));
  }
  render() {
    if (!GroupStore.donePopulating || !ScheduleItemStore.donePopulating
      || !TaskStore.donePopulating || !ModuleStore.donePopulating) {
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
    if (localStorage.getItem('userType') === 'instructor') {
      return (
        <BrowserRouter>
          <MuiThemeProvider muiTheme={muiTheme}>
            <MainPage />
          </MuiThemeProvider>
        </BrowserRouter>
      );
    }
    return (
      <BrowserRouter>
        <MuiThemeProvider>
          <div>
            <MainPage />
            <Snackbar
              open={UtilStore.snackbarIsOpen}
              message={UtilStore.snackbarMessage}
              autoHideDuration={4000}
              onRequestClose={() => UtilStore.closeSnackbar()}
            />
          </div>
        </MuiThemeProvider>
      </BrowserRouter>
    );
  }
}

export default App;
