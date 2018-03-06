import React from 'react';
import { Paper } from 'material-ui';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';

import MainCalendar from './MainCalendar';

import 'react-big-calendar/lib/css/react-big-calendar.css';
import './styles.css';


BigCalendar.momentLocalizer(moment);


// import PropTypes from 'prop-types';

const PersonalScene = () => (
  <div>
    <MainCalendar />
  </div>
);


export default PersonalScene;
