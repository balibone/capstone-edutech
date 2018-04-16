import React, { Component } from 'react';
import { Drawer, Divider, List, ListItem } from 'material-ui';
import { observer } from 'mobx-react';
import { Link } from 'react-router-dom';
import swal from 'sweetalert';

import GroupStore from '../../../stores/GroupStore/GroupStore';
import ModuleStore from '../../../stores/ModuleStore/ModuleStore';
import { GROUP_IMAGE_PATH, USER_IMAGE_PATH, INSTRUCTOR_PRIMARY1_COLOR, STUDENT_PRIMARY1_COLOR } from '../../../utils/constants';
import './styles.css';


@observer
export default class LeftBar extends Component {
  handleLogout() {
    swal({
      title: 'Logout',
      text: 'Are you sure you want to logout now?',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        // clear cookie
        document.cookie.split(';').forEach((c) => { document.cookie = c.replace(/^ +/, '').replace(/=.*/, '=;expires=' + new Date().toUTCString() + ';path=/'); }); // eslint-disable-line
        // clear localStorage
        localStorage.clear();
        // redirect to login page
        window.location.replace('http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=goToLogout');
      }
    });
  }
  renderProfile(scene) {
    const user = JSON.parse(localStorage.getItem('currentUser'));
    let image = USER_IMAGE_PATH + user.imgFileName;
    let name = user.username;
    // if in module or group scene
    if (scene === 'module') {
      if (ModuleStore.selectedModule) {
        const module = ModuleStore.selectedModule;
        name = module.title;
        image = '/img/module.png';
      }
    } else if (scene === 'group') {
      if (GroupStore.selectedGroup) {
        const group = GroupStore.selectedGroup;
        name = group.title;
        image = GROUP_IMAGE_PATH + group.imagefilename;
      }
    }
    const backgroundColor = localStorage.getItem('userType') === 'instructor' ? INSTRUCTOR_PRIMARY1_COLOR : STUDENT_PRIMARY1_COLOR;
    return (
      <div className="profile" style={{ backgroundColor }}>
        <img src={image} height="120" alt="profile" />
        <h4> {name} </h4>
      </div>
    );
  }
  renderModulesMenu() {
    const modules = ModuleStore.moduleList;
    if (modules && modules.length > 0) {
      const moduleListItems = [];
      for (let i = 0; i < modules.length; i += 1) {
        moduleListItems.push(<ListItem primaryText={modules[i].moduleCode} key={modules[i].moduleCode} containerElement={<Link to={`/module/${modules[i].moduleCode}`} />} />); // eslint-disable-line
      }

      return (
        <div>
          <Divider />
          <Divider />
            <ListItem
              primaryText="My Modules"
              leftIcon={<i className="fas fa-book" />}
              initiallyOpen={false}
              primaryTogglesNestedList
              nestedItems={moduleListItems}
            />
        </div>
      );
    }
    return <span />;
  }
  renderGroupsMenu() {
    const groups = GroupStore.groupList;
    if (groups && groups.length > 0) {
      const groupListItems = [];
      for (let i = 0; i < groups.length; i += 1) {
        groupListItems.push(<ListItem primaryText={groups[i].title} key={groups[i].id} containerElement={<Link to={`/group/${groups[i].id}`} />} />); // eslint-disable-line
      }
      return (
        <div>
          <Divider />
            <ListItem
              primaryText="My Groups"
              leftIcon={<i className="fas fa-group" />}
              initiallyOpen={false}
              primaryTogglesNestedList
              nestedItems={groupListItems}
            />
        </div>
      );
    }
    return <span />;
  }
  renderOthersMenu() {
    const otherListItems = [
      <ListItem primaryText="Edubox" key={1} onClick={() => window.location.replace('http://localhost:8080/EduTechWebApp-war/')} />,
      <ListItem primaryText="Logout" key={1} onClick={() => this.handleLogout()} />,
    ];
    return (
      <div>
        <Divider />
          <ListItem
            primaryText="Others"
            leftIcon={<i className="fas fa-bookmark" />}
            initiallyOpen={false}
            primaryTogglesNestedList
            nestedItems={otherListItems}
          />
      </div>
    );
  }
  render() {
    const { scene } = this.props;
    return (
      <Drawer open className="leftDrawer">
        {this.renderProfile(scene)}
        <List style={{ padding: 0 }}>
          <ListItem
            primaryText="Home"
            leftIcon={<i className="fas fa-home" />}
            containerElement={<Link to={''} />} // eslint-disable-line
          />
          <Divider />
          {this.renderModulesMenu()}
          {this.renderGroupsMenu()}
          {this.renderOthersMenu()}
        </List>
      </Drawer>
    );
  }
}
