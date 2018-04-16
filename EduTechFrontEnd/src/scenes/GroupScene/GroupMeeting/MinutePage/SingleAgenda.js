import React, { Component } from 'react';
import { FormGroup, FormControl, Button, Panel, Col, Row, ControlLabel } from 'react-bootstrap';

import MinuteStore from '../../../../stores/MeetingStore/MinuteStore';

class SingleAgenda extends Component {
  constructor(props) {
		super(props)
    const {
      id, title, discussion, conclusion
    } = this.props.agenda;
		this.state = {
			id,
      title,
			discussion,
			conclusion,
		}
	}

  componentDidUpdate(prevProps) {
    if (prevProps.agenda !== this.props.agenda) {
      this.handleUpdateId(this.props.agenda);
    }
  }

  handleUpdateId(agenda) {
    console.log('UPDATING PROPS')
    this.setState({ id: agenda.id })
  }

  submitAgenda() {
    const { minuteId } = this.props;
    const { id } = this.props.agenda;
    const {
      title, discussion, conclusion
    } = this.state;
    const agenda = {
      id, title, discussion, conclusion
    }
    console.log('agenda in submit agenda:', agenda);
    MinuteStore.updateAgenda(minuteId, agenda, this.props.groupId);
  }

  deleteAgenda() {
    const { id } = this.props.agenda;
    MinuteStore.deleteAgenda(id, this.props.groupId);
  }

  handleDiscussionChange(event) {
    this.setState({ discussion: event.target.value })
  }
  handleConclusionChange(event) {
    this.setState({ conclusion: event.target.value })
  }

  render() {
    return (
      <Panel eventKey={this.state.id} className="standardTopGap">
          <Panel.Heading>
              <Panel.Title toggle>
                {this.state.title}
                <i className="fas fa-trash-alt pull-right" onClick={() => this.deleteAgenda()} />
              </Panel.Title>
          </Panel.Heading>

          <Panel.Body collapsible>
                <Row className="show-grid">
                <Col md={12}>
                  <FormGroup controlId="formControlsTextarea">
                    <ControlLabel>Discussion</ControlLabel>
                    <FormControl
                      componentClass="textarea"
                      value={this.state.discussion}
                      placeholder="Add discussion"
                      onChange={this.handleDiscussionChange.bind(this)}
                    />
                  </FormGroup>

                  <FormGroup controlId="formControlsTextarea">
                    <ControlLabel>Conclusion</ControlLabel>
                    <FormControl
                      componentClass="textarea"
                      value={this.state.conclusion}
                      placeholder="Add conclusion"
                      onChange={this.handleConclusionChange.bind(this)}
                    />
                  </FormGroup>
                  <Button bsStyle="primary" onClick={() => this.submitAgenda()}>Submit</Button>
                </Col>
              </Row>
          </Panel.Body>
       </Panel>
    );
  }
}

export default SingleAgenda;
