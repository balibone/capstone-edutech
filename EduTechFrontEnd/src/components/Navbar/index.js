import React, { Component } from 'react';
import {toJS} from 'mobx';
import {observer} from 'mobx-react';
import { Link } from 'react-router-dom';
import { Navbar as BootstrapNavbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';

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
    // const groupList = toJS(GroupStore.groupList);
    // console.log(groupList)
    const { user, modules, groups, userStore} = this.props;
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const groupList = JSON.parse(localStorage.getItem('groupList'));
    console.log('currentUser', currentUser)
    const moduleMenuItems = modules.map(module => <MenuItem eventKey={module.code}><Link to={`/module/${module.code}`}>{module.name}</Link></MenuItem>)
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
          <NavItem eventKey={3} href="#">
            <i className="far fa-bell" />
          </NavItem>
          <NavDropdown
            eventKey={4}
            title={<img src={`/img/${currentUser.imgfilename}`} alt="profile" className="img-circle" height="20" />}
            id="groups-nav-dropdown"
          >
            <MenuItem eventKey={4.1} onClick={() => this.handleLogout()}>Logout</MenuItem>
          </NavDropdown>
        </Nav>
      </BootstrapNavbar>
    )
  }
}

export default Navbar;


// const Navbar = ({ user, modules, groups }) => {
//   const moduleMenuItems = modules.map(module => <MenuItem eventKey={module.code}><Link to={`/module/${module.code}`}>{module.name}</Link></MenuItem>)
//   const groupMenuItems = groups.map(group => <MenuItem eventKey={group.id}><Link to={`/group/${group.id}`}>{group.name}</Link></MenuItem>)

//   return (
//     <BootstrapNavbar staticTop="true">
//       <BootstrapNavbar.Header>
//         <BootstrapNavbar.Brand>
//           <Link to="/">EDUTECH</Link>
//         </BootstrapNavbar.Brand>
//       </BootstrapNavbar.Header>
//       {console.log("Group in navbar: ", GroupStore.groupList)}
//       <Nav>
//         <NavDropdown eventKey={1} title="Modules" id="modules-nav-dropdown">
//           {moduleMenuItems}
//         </NavDropdown>
//         <NavDropdown eventKey={2} title="Groups" id="groups-nav-dropdown">
//           {groupMenuItems}
//         </NavDropdown>
//       </Nav>
//       <Nav pullRight>
//         <NavItem eventKey={3} href="#">
//           <i className="far fa-bell" />
//         </NavItem>
//         <NavDropdown
//           eventKey={4}
//           title={<img src={`/img/${user.img}`} alt="profile" className="img-circle" height="20" />}
//           id="groups-nav-dropdown"
//         >
//           <MenuItem eventKey={4.1}>Action</MenuItem>
//           <MenuItem eventKey={4.2}>Another action</MenuItem>
//         </NavDropdown>
//       </Nav>
//     </BootstrapNavbar>
//   );
// };
// export default Navbar;
