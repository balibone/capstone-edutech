import React, { Component } from 'react';
import { Paper, FlatButton, Toggle, IconButton, Dialog } from 'material-ui';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';
import BigCalendar from 'react-big-calendar';
import moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import { Link } from 'react-router-dom';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import 'react-widgets/dist/css/react-widgets.css';

import GroupScheduleItemsChart from '../GroupScheduleItemsChart';
import AddScheduleItemDialog from './AddScheduleItemDialog';
import SingleScheduleItemDialog from './SingleScheduleItemDialog';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import GroupScheduleItemStore from '../../../stores/ScheduleItemStore/GroupScheduleItemStore';
import { formatMergedCalendarEvents, setScheduleItemsColor } from '../../../utils/helpers';
import './style.css';

moment.locale('en');
BigCalendar.momentLocalizer(moment);
momentLocalizer();

@observer
class GroupCalendar extends Component {
  state = {
    openViewScheduleItemDialog: false,
    selectedEvent: null,
    openAddScheduleItemDialog: false,
    scheduleStart: null,
    scheduleEnd: null,
    scheduleTitle: '',
    scheduleDescription: '',
    scheduleLocation: '',
    groupItemsOnly: false,
    openChartDialog: false,
  }

  handleAddScheduleItem() {
    const {
      scheduleTitle, scheduleDescription, scheduleStart, scheduleEnd, scheduleLocation,
    } = this.state;
    const assignedTo = [];
    const itemType = 'meeting';
    const moduleCode = null;
    const groupId = GroupStore.selectedGroup.id;
    GroupScheduleItemStore.addScheduleItem(
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
    console.log('event', event, 'event grou id', event.groupId)
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

  renderChartDialog() {
    const actions = [
     <FlatButton
       label="Close"
       primary
       onClick={() => this.setState({ openChartDialog: false })}
     />,
   ];
    return (
      <Dialog
        actions={actions}
        modal
        open={this.state.openChartDialog}
      >
        <GroupScheduleItemsChart />
      </Dialog>
    );
  }

  renderViewChartButton() {
    if (this.props.viewChart) {
      return (
        <FlatButton
          secondary
          label="View Chart"
          icon={<i className="fas fa-chart-bar" />}
          onClick={() => this.setState({ openChartDialog: true })}
        />
      );
    }
    return '';
  }

  renderToggle() {
    if (!GroupScheduleItemStore.meetingItems || GroupScheduleItemStore.meetingItems.length < 1) {
      return '';
    }
    return (
      <Toggle
        label="Group events only"
        labelStyle={{ fontWeight: 'normal' }}
        style={{ marginRight: '10px' }}
        className="pull-right text-right"
        onToggle={() => this.setState({ groupItemsOnly: !this.state.groupItemsOnly })}
      />
    );
  }

	render() {
    const scheduleItems = this.state.groupItemsOnly ?
      GroupScheduleItemStore.groupItems : GroupScheduleItemStore.scheduleItems;
    if (!scheduleItems || scheduleItems.length < 1) {
      return '';
    }
    const events = formatMergedCalendarEvents(scheduleItems, GroupStore.selectedGroup.id);
    return (
      <div className="myCalendar">
        <h3>
          {GroupStore.selectedGroup.title} Schedule
        </h3>
        {this.renderViewChartButton()}
        {this.renderToggle()}
        <div className="calendarContainter">
          <BigCalendar
            events={events}
            views={['month', 'week', 'day']}
            defaultView={localStorage.getItem('defaultView') || 'week'}
            step={60}
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
        { this.renderChartDialog() }
      </div>
		);
	}
}

export default GroupCalendar;
