import React, { Component } from 'react';
import { Paper, RaisedButton, Toggle } from 'material-ui';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import { Link } from 'react-router-dom';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'react-widgets/dist/css/react-widgets.css';

import AddScheduleItemDialog from './AddScheduleItemDialog';
import SingleScheduleItemDialog from './SingleScheduleItemDialog';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import { setScheduleItemsColor } from '../../../utils/helpers';
import './style.css';

moment.locale('en');
BigCalendar.momentLocalizer(moment);
momentLocalizer();

@observer
class MyCalendar extends Component {
  state = {
    openViewScheduleItemDialog: false,
    selectedEvent: null,
    openAddScheduleItemDialog: false,
    scheduleStart: null,
    scheduleEnd: null,
    scheduleTitle: '',
    scheduleDescription: '',
    scheduleLocation: '',
    timetableOnly: false,
  }

  handleAddScheduleItem() {
    const {
      scheduleTitle, scheduleDescription, scheduleStart, scheduleEnd, scheduleLocation,
    } = this.state;
    const assignedTo = [];
    const itemType = 'personal';
    const moduleCode = null;
    const groupId = null;
    ScheduleItemStore.addScheduleItem(
        scheduleTitle, scheduleDescription, scheduleStart,
        scheduleEnd, scheduleLocation, assignedTo, itemType, moduleCode, groupId,
      );
    this.handleCloseCalendarForm();
  }

  handleCloseCalendarForm() {
    this.setState({
      openAddScheduleItemDialog: false,
      scheduleTitle: '',
      scheduleDescription: '',
      scheduleLocation: '',
    });
  }

  eventStyleGetter(event) {
  	const backgroundColor = event.hexColor;
  	const style = {
        backgroundColor,
        border: `1px solid ${backgroundColor}`,
  	};
  	return { style };
	}

  handleSelectSlot(slotInfo) {
    this.setState({
      scheduleStart: moment(slotInfo.start).format('YYYY-MM-DD HH:mm:ss'),
      scheduleEnd: moment(slotInfo.end).format('YYYY-MM-DD HH:mm:ss'),
      openAddScheduleItemDialog: true,
    });
  }

  handleSelectEvent(event) {
    this.setState({
      selectedEvent: event,
      openViewScheduleItemDialog: true,
    });
  }

  renderAddScheduleItemDialog() {
    return (
      <AddScheduleItemDialog
        handleTitleChange={e => this.setState({ scheduleTitle: e.target.value })}
        handleDescriptionChange={e => this.setState({ scheduleDescription: e.target.value })}
        handleLocationChange={venue => this.setState({ scheduleLocation: venue })}
        handleStartChange={start => this.setState({ scheduleStart: start })}
        handleEndChange={end => this.setState({ scheduleEnd: end })}
        handleCloseCalendarForm={() => this.handleCloseCalendarForm()}
        handleAddScheduleItem={() => this.handleAddScheduleItem()}
        dialogState={this.state.openAddScheduleItemDialog}
        scheduleStart={this.state.scheduleStart}
        scheduleEnd={this.state.scheduleEnd}
        scheduleLocation={this.state.scheduleLocation}
        scheduleTitle={this.scheduleTitle}
        scheduleDescription={this.scheduleDescription}
      />
    );
  }

  renderViewScheduleItemDialog() {
    if (this.state.openViewScheduleItemDialog) {
      return (
        <SingleScheduleItemDialog
          dialogState={this.state.openViewScheduleItemDialog}
          selectedEvent={this.state.selectedEvent}
          handleCloseDialog={() => this.setState({ openViewScheduleItemDialog: false })}
        />
      );
    }
    return '';
  }

  renderExpandViewButton() {
    if (this.props.expandViewButton) {
      return (
        <RaisedButton
          label="Expand Schedule View"
          labelPosition="before"
          icon={<i className="fas fa-expand" />}
          fullWidth
          containerElement={<Link to="myschedule" />} // eslint-disable-line
        />
      );
    }
    return '';
  }

  renderToggle() {
    if (!ScheduleItemStore.timetableItems || ScheduleItemStore.timetableItems.length < 1) {
      return '';
    }
    return (
      <Toggle
        label="Timetable only"
        labelStyle={{ fontWeight: 'normal' }}
        style={{ marginTop: '-25px', marginRight: '10px' }}
        className="pull-right text-right"
        onToggle={() => this.setState({ timetableOnly: !this.state.timetableOnly })}
      />
    );
  }

	render() {
    const scheduleItems = this.state.timetableOnly ?
      ScheduleItemStore.timetableItems : ScheduleItemStore.scheduleItems;
    if (!scheduleItems || scheduleItems.length < 1) {
      return '';
    }
    const events = setScheduleItemsColor(scheduleItems);
		return (
      <Paper className="myCalendar">
        <h3> My Schedule </h3>
        <div className="calendarContainter">
          <BigCalendar
            events={events}
            views={['month', 'week', 'day']}
            defaultView={localStorage.getItem('defaultView') || 'week'}
            step={this.props.step}
            scrollToTime={new Date()}
            defaultDate={new Date(moment().startOf('day').toString())}
            length={7}
            eventPropGetter={this.eventStyleGetter}
            onView={view => localStorage.setItem('defaultView', view)}
            onSelectSlot={slotInfo => this.handleSelectSlot(slotInfo)}
            onSelectEvent={event => this.handleSelectEvent(event)}
            selectable
            popup
          />
        </div>
        { this.renderAddScheduleItemDialog() }
        { this.renderViewScheduleItemDialog() }
        { this.renderExpandViewButton() }
      </Paper>
		);
	}
}

export default MyCalendar;
