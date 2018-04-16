import React, { Component } from 'react';
import moment from 'moment';
import { RaisedButton, Dialog, FlatButton } from 'material-ui';
import { Multiselect } from 'react-widgets';
import { ControlLabel } from 'react-bootstrap';

import Widget from './Widget';
import './style.css';


export default class KeyDates extends Component {
  state = {
    openWidgetForm: false,
    widgetsSelected: ['personal'],
  }
  componentWillMount() {
    if (JSON.parse(localStorage.getItem('widgetPreference'))) {
      this.setState({ widgetsSelected: JSON.parse(localStorage.getItem('widgetPreference')) });
    }
  }
  handleWidgetChangePreferences(preferences) {
    this.setState({ widgetsSelected: preferences });
    localStorage.setItem('widgetPreference', JSON.stringify(preferences));
  }
  renderWidgetForm() {
    return (
      <div className="widgetForm">
        <ControlLabel>Select widgets to be shown:</ControlLabel>
        <Multiselect
          defaultValue={JSON.parse(localStorage.getItem('widgetPreference'))}
          data={['timetable', 'meeting', 'personal', 'assessment', 'task']}
          onChange={preferences => this.handleWidgetChangePreferences(preferences)}
        />
      </div>
    );
  }
  renderWidgets() {
    if (this.state.widgetsSelected.length > 0) {
      return this.state.widgetsSelected.map(widget => <Widget itemType={widget} key={widget} />);
    }
    return (
      <div className="widgetsEmptyState">
        <p className="lead"> Choose widgets to peek your upcoming key dates </p>
        <RaisedButton primary onClick={() => this.setState({ openWidgetForm: true })} label="Choose widgets" />
      </div>
    );
  }
  render() {
    const actions = [
      <FlatButton
        label="Okay"
        primary
        onClick={() => this.setState({ openWidgetForm: false })}
      />,
    ];
    return (
      <div className="keyDates">
        <h3 className="header"> Upcoming Key Dates</h3>
        <p className="widgetDate">{moment().format('dddd, Do MMMM')} </p>
        {this.renderWidgets()}
        <div className="footerOptions">
          <RaisedButton onClick={() => this.setState({ openWidgetForm: true })} label="Choose widgets" labelPosition="before" icon={<i className="fas fa-cog" />} fullWidth />
        </div>
        <Dialog
          title="Edit Widget Preferences"
          actions={actions}
          modal={false}
          open={this.state.openWidgetForm}
          onRequestClose={() => this.setState({ openWidgetForm: false })}
        >
          {this.renderWidgetForm()}
        </Dialog>
      </div>
    );
  }
}
