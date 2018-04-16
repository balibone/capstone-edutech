import React from 'react';
import { Dialog, FlatButton } from 'material-ui';
import { Row, Col, ControlLabel, FormControl } from 'react-bootstrap';
import { DateTimePicker } from 'react-widgets';
import Autosuggest from 'react-bootstrap-autosuggest';

const AddScheduleItemDialog = ({
  dialogState, handleCloseCalendarForm, handleAddScheduleItem,
  scheduleStart, scheduleEnd, scheduleLocation, handleLocationChange,
  scheduleTitle, scheduleDescription, handleTitleChange, handleDescriptionChange,
  handleStartChange, handleEndChange,
}) => {
    const actions = [
      <FlatButton
        label="Cancel"
        primary
        onClick={() => handleCloseCalendarForm()}
      />,
      <FlatButton
        label="Submit"
        primary
        onClick={() => handleAddScheduleItem()}
      />,
    ];
    return (
      <Dialog
        title="Add To Schedule"
        actions={actions}
        modal={false}
        open={dialogState}
        onRequestClose={() => handleCloseCalendarForm()}
      >
      <div>
          <Row className="calendarDateFormField">
            <Col md={6}>
              <ControlLabel>From</ControlLabel>
              <DateTimePicker
                defaultValue={new Date(scheduleStart)}
                onChange={handleStartChange}
              />
            </Col>
            <Col md={6}>
              <ControlLabel>To</ControlLabel>
              <DateTimePicker
                defaultValue={new Date(scheduleEnd)}
                onChange={handleEndChange}
              />
            </Col>
          </Row>
          <Row className="calendarFormField">
            <Col md={12}>
                <ControlLabel>Location</ControlLabel>
                <Autosuggest
                  value={scheduleLocation}
                  datalist={JSON.parse(localStorage.getItem('NUSVenues'))}
                  placeholder="Enter schedule location"
                  onChange={handleLocationChange}
                />
            </Col>
          </Row>
          <Row className="calendarFormField">
            <Col md={12}>
                <ControlLabel>Title</ControlLabel>
                <FormControl
                  type="text"
                  value={scheduleTitle}
                  placeholder="Enter schedule title"
                  onChange={handleTitleChange}
                />
            </Col>
          </Row>
          <Row className="calendarFormField">
            <Col md={12}>
                <ControlLabel>Description</ControlLabel>
                <FormControl
                  type="text"
                  value={scheduleDescription}
                  placeholder="Enter schedule description"
                  onChange={handleDescriptionChange}
                />
            </Col>
          </Row>
      </div>
      </Dialog>
    );
};

export default AddScheduleItemDialog;
