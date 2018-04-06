import React, { Component } from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import { Link } from 'react-router-dom';
import Badge from 'material-ui/Badge';
import { Navbar as BootstrapNavbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';
import { Wave } from 'better-react-spinkit'

import GroupStore from '../../stores/GroupStore/GroupStore';

import './styles.css';

@observer
class Navbar extends Component {

  handleLogout() {
    // clear cookie
    document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
    // clear localStorage
    localStorage.clear();
    // redirect to login page
    window.location.replace('http://localhost:8080/EduTechWebApp-war/CommonInfra?pageTransit=goToLogout');
  }


  render(){
    const { user, modules, groups, userStore} = this.props;
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const groupList = JSON.parse(localStorage.getItem('groupList'));
    const moduleList = JSON.parse(localStorage.getItem('moduleList'));
    // console.log("MODLE IN NAV", moduleList)
    if(!groupList || !currentUser) {
      return (
        <div className="fakeBody">
          <div className="initialSpinner">
          <center>
            <Wave size={100} />
            <span className="spinnerText">Loading...</span>
            </center>
          </div>
        </div>
      )
    }

    // if(!groupList || !currentUser) {
    //   return <span>loading - please refresh if it takes too long</span>
    // }

    const moduleMenuItems = moduleList.map(module => <MenuItem eventKey={module.moduleCode}><Link to={`/module/${module.moduleCode}`}>{module.title}</Link></MenuItem>)
    const groupMenuItems = groupList.map(group => <MenuItem eventKey={group.id}><Link to={`/group/${group.id}`}>{group.title}</Link></MenuItem>)

    return(
      <BootstrapNavbar staticTop="true">
        <BootstrapNavbar.Header>
          <BootstrapNavbar.Brand>
            <Link to="/" onClick={()=> GroupStore.selectedGroup=null} >EDUTECH</Link>
          </BootstrapNavbar.Brand>
        </BootstrapNavbar.Header>
        <Nav>
          <NavDropdown eventKey={1} title="Modules" id="modules-nav-dropdown">
            {moduleMenuItems}
          </NavDropdown>
          <NavDropdown eventKey={2} title="Groups" id="groups-nav-dropdown">
            {groupMenuItems}
          </NavDropdown>
        </Nav>
        <Nav pullRight>
        <Badge
              badgeContent={4}
              primary={true}
            >
          <NavItem eventKey={3} href="#">
            
              <i className="far fa-bell" />
            
          </NavItem>
          </Badge>
          <NavDropdown
            eventKey={4}
            title={<img src={`http://localhost:8080/EduTechWebApp-war/uploads/commoninfrastructure/admin/images/${currentUser.imgFileName}`} alt="profile" className="img-circle" height="20" />}
            id="groups-nav-dropdown"
          >
            <MenuItem eventKey={4.2} onClick={() =>  window.location.replace('http://localhost:8080/EduTechWebApp-war/')}>EduBox</MenuItem>
            <MenuItem eventKey={4.1} onClick={() => this.handleLogout()}>Logout</MenuItem>
          </NavDropdown>
        </Nav>
      </BootstrapNavbar>
    )
  }
}

export default Navbar;
