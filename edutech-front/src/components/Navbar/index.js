import React from 'react';
import { Link } from 'react-router-dom';
import { Navbar as BootstrapNavbar, Nav, NavItem, NavDropdown, MenuItem } from 'react-bootstrap';

import './styles.css';

const Navbar = ({ user, modules, groups }) => {
  const moduleMenuItems = modules.map(module => <MenuItem eventKey={module.code}><Link to={`/module/${module.code}`}>{module.name}</Link></MenuItem>)
  const groupMenuItems = groups.map(group => <MenuItem eventKey={group.id}><Link to={`/group/${group.id}`}>{group.name}</Link></MenuItem>)

  return (
    <BootstrapNavbar staticTop="true">
      <BootstrapNavbar.Header>
        <BootstrapNavbar.Brand>
          <Link to="/">EDUTECH</Link>
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
          title={<img src={`/img/${user.img}`} alt="profile" className="img-circle" height="20" />}
          id="groups-nav-dropdown"
        >
          <MenuItem eventKey={4.1}>Action</MenuItem>
          <MenuItem eventKey={4.2}>Another action</MenuItem>
        </NavDropdown>
      </Nav>
    </BootstrapNavbar>
  );
};
export default Navbar;
