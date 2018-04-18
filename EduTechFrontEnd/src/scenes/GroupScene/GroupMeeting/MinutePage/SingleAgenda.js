import React, { Component } from 'react';
import { FormGroup, FormControl, Button, Panel, Col, Row, ControlLabel } from 'react-bootstrap';
import RaisedButton from 'material-ui/RaisedButton';
import moment from 'moment';
import swal from 'sweetalert';

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
    this.setState({ id: agenda.id, discussion: agenda.discussion, conclusion: agenda.conclusion })
  }

  submitAgenda() {
    const { minuteId } = this.props;
    const { id } = this.props.agenda;
    const {
      title, discussion, conclusion
    } = this.state;
    const modifiedAt = moment(new Date()).format('YYYY-MM-DDTHH:mm:ss');
    const agenda = {
      id, title, discussion, conclusion, modifiedAt, modifiedBy: JSON.parse(localStorage.getItem('currentUser'))
    }
    // console.log('submit agenda thing: ', agenda)
    MinuteStore.updateAgenda(minuteId, agenda, this.props.groupId);
  }

  deleteAgenda() {
    const { id } = this.props.agenda;
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this agenda if you delete it!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        MinuteStore.deleteAgenda(id, this.props.groupId);
      }
    });
  }

  handleDiscussionChange(event) {
    this.setState({ discussion: event.target.value })
  }
  handleConclusionChange(event) {
    this.setState({ conclusion: event.target.value })
  }

  render() {
    console.log('agenda in SingleAgenda:', this.props.agenda)

    const { modifiedAt, modifiedBy } = this.props.agenda;
    return (
      <Panel eventKey={this.state.id} className="standardTopGap">
          <Panel.Heading>
              <Panel.Title toggle>
                {this.state.title}
                <i className="fas fa-trash-alt pull-right" onClick={() => this.deleteAgenda()} />
                <p className="smallText">Modified At: {moment(modifiedAt).format('D MMM, h:mm a')}</p>
                <p className="smallText">Modified By: {modifiedBy.username}</p>
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
                  <RaisedButton label="Submit" primary onClick={() => this.submitAgenda()} />
                </Col>
              </Row>
          </Panel.Body>
       </Panel>
    );
  }
}

export default SingleAgenda;
