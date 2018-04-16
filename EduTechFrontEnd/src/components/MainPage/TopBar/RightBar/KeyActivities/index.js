import React, { Component } from 'react';
import { Wave } from 'better-react-spinkit';
import { Paper, List, ListItem, Subheader, Avatar, Divider } from 'material-ui';
import moment from 'moment';
import {Link} from 'react-router-dom';

import {USER_IMAGE_PATH} from '../../../../../utils/constants';
import AnnouncementStore from '../../../../../stores/AnnouncementStore/AnnouncementStore';
import './styles.css';

export default class KeyActivities extends Component {
  componentWillMount() {
    AnnouncementStore.populateAnnouncements(localStorage.getItem('username'));
  }
  renderNotifications() {
    return AnnouncementStore.announcements.map(announcement => (
      <div>
        <ListItem
          containerElement={<Link to={announcement.path} />}
          leftAvatar={<Avatar src={USER_IMAGE_PATH + announcement.createdBy.imgFileName} />}
          primaryText={
              <p>{announcement.title}<br />
                <span style={{color: '#AAA'}}>
                  <small> {moment(announcement.createdAt).fromNow()}</small>
                </span>
              </p>
            }
          secondaryText={
            <p>
              <span style={{color: '#333'}}>{announcement.createdBy.username}</span> --
              {announcement.message}
            </p>
          }
          secondaryTextLines={2}
        />
        <Divider inset />
      </div>
    ));
  }
  render() {
    if (!AnnouncementStore.donePopulating) {
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
    return (
      <div>
        <Subheader>Latest Key Activities</Subheader>
        <List>
          {this.renderNotifications()}
        </List>
      </div>
    );
  }
}
