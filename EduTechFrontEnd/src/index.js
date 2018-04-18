import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { retrieveCookie } from './utils/helpers';
import { HOST_NAME } from './utils/constants';

const username = retrieveCookie('username');
localStorage.setItem('username', username);
const userType = retrieveCookie('userType');
localStorage.setItem('userType', userType);

if (username) {
  ReactDOM.render(<App username={username} />, document.getElementById('root'));
} else {
  window.location.replace(`http://${HOST_NAME}:8080/EduTechWebApp-war/`);
}
