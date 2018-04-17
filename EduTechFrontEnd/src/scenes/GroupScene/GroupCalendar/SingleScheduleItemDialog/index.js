import React, { Component } from 'react';
import { Dialog, FlatButton, List, ListItem, Divider } from 'material-ui';
import { Badge, Row, Col, ControlLabel, FormControl } from 'react-bootstrap';
import { DateTimePicker } from 'react-widgets';
import Autosuggest from 'react-bootstrap-autosuggest';
import moment from 'moment';
import swal from 'sweetalert';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';

import { getScheduleTypeColor } from '../../../../utils/helpers';
import ScheduleItemStore from '../../../../stores/ScheduleItemStore/ScheduleItemStore';
import GroupScheduleItemStore from '../../../../stores/ScheduleItemStore/GroupScheduleItemStore';
import GroupStore from '../../../../stores/GroupStore/GroupStore';

@observer
export default class ViewScheduleItemDialog extends Component {
  state = {
    openEditForm: false,
    edittedTitle: '',
    edittedDescription: '',
    edittedLocation: '',
    edittedStart: null,
    edittedEnd: null,
  }
  componentWillMount() {
    const { title, start, end, description, location } = this.props.selectedEvent; // eslint-disable-line
    this.setState({
      edittedTitle: title,
      edittedDescription: description,
      edittedLocation: location,
      edittedStart: start,
      edittedEnd: end,
    });
  }
  getActions(type, handleCloseDialog, eventId, groupId) {
    if (this.state.openEditForm) {
      const actions = [<FlatButton label="Cancel" primary onClick={() => this.handleCancelEdit()} />];
      actions.push(<FlatButton label="Save" primary onClick={() => this.handleSaveEdit(eventId)} />);
      return actions;
    }
    const actions = [<FlatButton label="Close" primary onClick={handleCloseDialog} />];
    console.log('type', type, 'groupId', groupId, 'GroupStore.selectedGroup.id', GroupStore.selectedGroup.id)
    if (type === 'meeting' && groupId === GroupStore.selectedGroup.id) {
      actions.push(<FlatButton label="Remove" primary onClick={() => this.handleRemoveScheduleItem(eventId, handleCloseDialog)} />);
      actions.push(<FlatButton label="Edit" primary onClick={() => this.setState({ openEditForm: true })} />);
    }
    return actions;
  }

  handleCancelEdit() {
    const { title, start, end, description, location } = this.props.selectedEvent; // eslint-disable-line
    this.setState({
      edittedTitle: title,
      edittedDescription: description,
      edittedLocation: location,
      edittedStart: start,
      edittedEnd: end,
      openEditForm: false,
    });
  }

  handleSaveEdit(eventId) {
    const {
      edittedTitle, edittedDescription, edittedStart, edittedEnd, edittedLocation,
    } = this.state;
    ScheduleItemStore.updateScheduleItem(
        eventId, edittedTitle, edittedDescription,
        edittedStart, edittedEnd, edittedLocation,
      );
    this.setState({ openEditForm: false });
  }

  handleRemoveScheduleItem(eventId, handleCloseDialog) {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this item!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        ScheduleItemStore.removeScheduleItem(eventId);
        handleCloseDialog();
      }
    });
  }

  renderType(type) {
    const color = getScheduleTypeColor(type);
    return (
      <Badge style={{ background: color }}>{type}</Badge>
    );
  }

  renderAgendaList() {
    const groupScheduleItems = toJS(GroupScheduleItemStore.scheduleItems);
    const { id } = this.props.selectedEvent;
    const selectedScheduleItem = groupScheduleItems.filter(item => item.id === id);
    console.log('selectedScheduleItem: ', selectedScheduleItem[0])
    if (selectedScheduleItem[0].meetingMinute) {
      console.log('render agendas lah')
      const agendaList = selectedScheduleItem[0].meetingMinute.agendas.map((agenda, index) => (
        <ListItem
          primaryText={`${index+1}) ${agenda.title}`}
        />
      ));
      return (
          <div>
          <Divider />
          <h4 className="lead">Agenda List</h4>
          <List>
            {agendaList}
          </List>
          </div>
      );
    }
    return (<p className="lead">No agenda created for this meeting.</p>);
  }

  renderTime(start, end) {
    if (moment(start).format('Do MMMM YYYY') === moment(end).format('Do MMMM YYYY')) {
      return <p>{moment(start).format('Do MMMM, h:mm a')} - {moment(end).format('h:mm a')}</p>;
    }
    return <p> {moment(start).format('Do MMMM, h:mm a')} - {moment(end).format('Do MMMM, h:mm a')}</p>;
  }

  renderContent(type) {
    const { openEditForm, edittedTitle, edittedDescription, edittedStart, edittedEnd, edittedLocation } = this.state; // eslint-disable-line
    if (openEditForm) {
      return (
        <div>
        <div>
            <Row className="calendarDateFormField">
              <Col md={6}>
                <ControlLabel>From</ControlLabel>
                <DateTimePicker
                  defaultValue={edittedStart}
                  onChange={newStart => this.setState({ edittedStart: newStart })}
                />
              </Col>
              <Col md={6}>
                <ControlLabel>To</ControlLabel>
                <DateTimePicker
                  defaultValue={edittedEnd}
                  onChange={newEnd => this.setState({ edittedEnd: newEnd })}
                />
              </Col>
            </Row>
            <Row className="calendarFormField">
              <Col md={12}>
                  <ControlLabel>Location</ControlLabel>
                  <Autosuggest
                    value={edittedLocation}
                    datalist={JSON.parse(localStorage.getItem('NUSVenues'))}
                    placeholder="Enter schedule location"
                    onChange={venue => this.setState({ edittedLocation: venue })}
                  />
              </Col>
            </Row>
            <Row className="calendarFormField">
              <Col md={12}>
                  <ControlLabel>Title</ControlLabel>
                  <FormControl
                    type="text"
                    value={edittedTitle}
                    placeholder="Enter schedule title"
                    onChange={e => this.setState({ edittedTitle: e.target.value })}
                  />
              </Col>
            </Row>
            <Row className="calendarFormField">
              <Col md={12}>
                  <ControlLabel>Description</ControlLabel>
                  <FormControl
                    type="text"
                    value={edittedDescription}
                    placeholder="Enter schedule description"
                    onChange={e => this.setState({ edittedDescription: e.target.value })}
                  />
              </Col>
            </Row>
        </div>
        </div>
      );
    }
    return (
      <div>
        <h4>{edittedTitle} @ {edittedLocation} {this.renderType(type)}</h4>
        {this.renderTime(edittedStart, edittedEnd)}
        <p>{this.state.edittedDescription}</p>
        {this.renderAgendaList()}
      </div>
    );
  }
  render() {
    const { selectedEvent, dialogState, handleCloseDialog } = this.props;
    const { id, type, groupId } = selectedEvent; // eslint-disable-line
    return (
      <Dialog
        actions={this.getActions(type, handleCloseDialog, id, groupId)}
        modal={false}
        open={dialogState}
        onRequestClose={handleCloseDialog}
      >
        {this.renderContent(type)}
      </Dialog>
    );
  }
}
