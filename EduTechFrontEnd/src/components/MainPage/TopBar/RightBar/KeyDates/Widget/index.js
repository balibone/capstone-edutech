import React from 'react';
import { Paper } from 'material-ui';
import { Row, Col } from 'react-bootstrap';
import moment from 'moment';

import ScheduleItemStore from '../../../../../../stores/ScheduleItemStore/ScheduleItemStore';
import './style.css';

const Widget = ({ itemType }) => {
  const scheduleItems = ScheduleItemStore.getKeyDates(itemType);
  const scheduleItemType = itemType === 'task' ? 'deadline' : itemType;
  const widgetEmptyState = <p className="emptyStateText">No upcoming {scheduleItemType}s.</p>;
  const widgetItems = scheduleItems.length < 1 ? widgetEmptyState :
    scheduleItems.map(scheduleItem => (
      <Row className="keyItem" key={scheduleItem.id}>
        <Col md={5} className="datetimeSection">
          <h4>{moment(scheduleItem.startDate).format('D MMM')} </h4>
          <small>{moment(scheduleItem.startDate).format('HH:mm')} - {moment(scheduleItem.endDate).format('HH:mm')}</small>
        </Col>
        <Col md={7} className="contentSection">
          <p className="widgetTitle">{scheduleItem.title} @ {scheduleItem.location}</p>
        </Col>
      </Row>
    ));
  const widgetItemsStyle = scheduleItems.length < 1 ? 'emptyBody' : 'body';
  return (
    <Paper className="widget">
      <div className="widgetHeader">
        {itemType}
      </div>
      <div className={widgetItemsStyle}>
        {widgetItems}
      </div>
    </Paper>
  );
};

export default Widget;
