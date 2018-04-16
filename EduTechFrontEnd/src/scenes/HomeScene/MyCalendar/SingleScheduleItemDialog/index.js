import React, { Component } from 'react';
import { Dialog, FlatButton } from 'material-ui';
import { Badge, Row, Col, ControlLabel, FormControl } from 'react-bootstrap';
import { DateTimePicker } from 'react-widgets';
import Autosuggest from 'react-bootstrap-autosuggest';
import moment from 'moment';
import swal from 'sweetalert';
import FileSaver from 'file-saver';
import { observer } from 'mobx-react';
import axios from 'axios';


import UtilStore from '../../../../stores/UtilStore/UtilStore';
import { getScheduleTypeColor } from '../../../../utils/helpers';
import ScheduleItemStore from '../../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
export default class ViewScheduleItemDialog extends Component {
  state = {
    openEditForm: false,
    edittedTitle: '',
    edittedDescription: '',
    edittedLocation: '',
    edittedStart: null,
    edittedEnd: null,
    files: null,
    doneFetching: false,
  }
  componentWillMount() {
    const { id, title, start, end, description, location, type } = this.props.selectedEvent; // eslint-disable-line
    this.setState({
      edittedTitle: title,
      edittedDescription: description,
      edittedLocation: location,
      edittedStart: start,
      edittedEnd: end,
    });

    if (type === 'timetable') {
      axios.get(`/lesson/allAttachments/${id}`)
        .then((res) => {
          this.setState({ files: res.data, doneFetching: true })
          // console.log("lessonId and file", lessonId, this.state.files);
        })
        .catch((err) => {
          console.log(err);
        })
    }
  }
  getActions(type, handleCloseDialog, eventId) {
    if (this.state.openEditForm) {
      const actions = [<FlatButton label="Cancel" primary onClick={() => this.handleCancelEdit()} />];
      actions.push(<FlatButton label="Save" primary onClick={() => this.handleSaveEdit(eventId)} />);
      return actions;
    }
    const actions = [<FlatButton label="Close" primary onClick={handleCloseDialog} />];
    if (type === 'personal') {
      actions.push(<FlatButton label="Remove" primary onClick={() => this.handleRemoveScheduleItem(eventId, handleCloseDialog)} />);
      actions.push(<FlatButton label="Edit" primary onClick={() => this.setState({ openEditForm: true })} />);
    }
    if (type === 'timetable') {
      if (!this.state.doneFetching) {
        actions.push(<span>Loading. . . </span>);
      } else if (this.state.doneFetching && this.state.files && this.state.files.length > 0) {
        actions.push(<FlatButton label="Download Files" info onClick={() => this.downloadFiles(eventId)} />);
      }
    }

    return actions;
  }
  async downloadFiles(lessonId) {
    try {
      const files = await axios.get(`/lesson/downloadAllAttachments/${lessonId}`, { responseType: 'blob' });
      const downloadedZip = files.data;
      const { title, start } = this.props.selectedEvent;
      const dateTimeFormat = moment(start).format('Do MMMM');
      const zipFileName = title.concat("_" + dateTimeFormat + ".zip"); //eslint-disable-line
      FileSaver.saveAs(downloadedZip, zipFileName);
      UtilStore.openSnackbar(`Downloading ${zipFileName} . . .`)
    } catch (e) {
      swal('error!', 'error downloading file', 'error');
    }

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
      );
    }
    return (
      <div>
        <h4>{edittedTitle} @ {edittedLocation} {this.renderType(type)}</h4>
        {this.renderTime(edittedStart, edittedEnd)}
        <p>{this.state.edittedDescription}</p>
      </div>
    );
  }
  render() {
    const { selectedEvent, dialogState, handleCloseDialog } = this.props;
    const { id, type } = selectedEvent; // eslint-disable-line

    return (
      <Dialog
        actions={this.getActions(type, handleCloseDialog, id)}
        modal={false}
        open={dialogState}
        onRequestClose={handleCloseDialog}
      >
        {this.renderContent(type)}
      </Dialog>
    );
  }
}
