import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { Col, Row, FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';

import 'react-widgets/dist/css/react-widgets.css';

import SingleBrainstorm from './SingleBrainstorm';
import BrainstormSession from './BrainstormSession';
import BrainstormStore from '../../../stores/BrainstormStore/BrainstormStore';
// create a viewModel singleton
const brainstormStore = new BrainstormStore();

@observer
export default class GroupBrainstorm extends Component {
  state = {
    currentSession: null,
  }

  handleStartSession(brainstorm) {
    this.setState({ currentSession: brainstorm });
  }

  handleEndSession() {
    this.setState({ currentSession: null });
  }

  handleEnterPress(e) {
    if (e.which === 13) {
      this.createBrainstorm(e);
    }
  }

  createBrainstorm(e) {
    brainstormStore.createBrainstorm(
      this.props.groupId,
      e.target.value,
    );
    e.target.value = '';
  }

  renderBrainstorms() {
    return brainstormStore.brainstorms.map(brainstorm => (
      <SingleBrainstorm
        key={brainstorm.id}
        brainstorm={brainstorm}
        brainstormStore={brainstormStore}
        handleStartSession={() => this.handleStartSession(brainstorm)}
      />
    ));
  }

  renderBrainstormForm() {
    return (
      <div>
        <Row>
          <Col md={12}>
            <FormControl
              type="text"
              placeholder="Create brainstorm session"
              onKeyPress={e => this.handleEnterPress(e)}
            />
          </Col>
        </Row>
      </div>
    );
  }

  render() {
    // start session - have current session
    if (this.state.currentSession) {
      return (
        <BrainstormSession
          brainstorm={this.state.currentSession}
          brainstormStore={brainstormStore}
          handleEndSession={() => this.handleEndSession()}
        />
      );
    }
    // no current session
    return (
      <Paper className="standardTopGap paperDefault">
        { this.renderBrainstormForm() }
        { this.renderBrainstorms()}
      </Paper>
    );
  }
}
