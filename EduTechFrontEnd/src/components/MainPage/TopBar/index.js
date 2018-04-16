import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import moment from 'moment';

import RightBar from './RightBar';
import { INSTRUCTOR_PRIMARY2_COLOR, STUDENT_PRIMARY2_COLOR } from '../../../utils/constants';
import './styles.css';

const styles = {
  title: {
    cursor: 'pointer',
  },
};

class TopBar extends Component {
  state = {
    openRightBar: false,
  }
  handleToggleRightBar() {
    this.setState({ openRightBar: !this.state.openRightBar });
  }
  render() {
    const buttonBackgroundColor = localStorage.userType === 'instructor' ? INSTRUCTOR_PRIMARY2_COLOR : STUDENT_PRIMARY2_COLOR;
    return (
      <div>
        <AppBar
          title={<span style={styles.title}>EduTech</span>}
          iconElementLeft={(<div />)}
          iconElementRight={<RaisedButton className="todayButton" backgroundColor={buttonBackgroundColor} labelColor="white" label={moment().format('dddd, Do MMMM')} onClick={() => this.handleToggleRightBar()} />}
          style={{ position: 'fixed', top: 0 }}
        />
        <RightBar
          openRightBar={this.state.openRightBar}
          handleToggleRightBar={() => this.handleToggleRightBar()}
        />
      </div>
    );
  }
}

export default TopBar;
