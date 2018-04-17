import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import { observer } from 'mobx-react'
import swal from 'sweetalert';

import { STUDENT_PRIMARY2_COLOR } from '../../../utils/constants';

import GroupStore from '../../../stores/GroupStore/GroupStore';


const styles = {
  title: {
    cursor: 'pointer',
  },
};

@observer
class CollabTopBar extends Component {
  async componentWillMount() {
    const { groupId } = this.props.match.params;
    await GroupStore.fetchCollabGroup(groupId);
  }
  handleLeaveSession() {
    swal({
      title: 'Are you sure?',
      text: 'Confirm leaving this session?',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((confirm) => {
      if (confirm) {
        window.close();
      }
    });
  }
  render() {
    if (!GroupStore.doneFetchingCollabGroup) {
      return 'loading...'
    }
    return (
      <div>
        <AppBar
          title={<span style={styles.title}>Edutech Live Collab: {GroupStore.collabGroup.title}</span>}
          iconElementLeft={(<div />)}
          iconElementRight={<RaisedButton className="todayButton" backgroundColor={STUDENT_PRIMARY2_COLOR} labelColor="white" label="Leave Session" onClick={() => this.handleLeaveSession()} />}
          style={{ position: 'fixed', top: 0 }}
        />
      </div>
    );
  }
}

export default CollabTopBar;
