import React, { Component } from 'react';
import { Col, Row, FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';

import 'react-widgets/dist/css/react-widgets.css';

import SingleIdea from '../SingleIdea';

@observer
export default class BrainstormIdeation extends Component {
  handleEnterPress(e) {
    if (e.which === 13) {
      this.addIdea(e);
    }
  }

  addIdea(e) {
    this.props.brainstormStore.addIdea(
      this.props.brainstorm,
      e.target.value,
    );
    e.target.value = '';
  }

  renderIdeas(brainstorm, brainstormStore) {
    return brainstorm.ideas.map(idea => (
      <SingleIdea
        key={idea.id}
        idea={idea}
        brainstorm={brainstorm}
        brainstormStore={brainstormStore}
      />
    ));
  }

  renderBrainstormForm() {
    return (
      <div className="standardBottomGap">
        <Row>
          <Col md={12}>
            <FormControl
              type="text"
              placeholder="Add idea"
              onKeyPress={e => this.handleEnterPress(e)}
            />
          </Col>
        </Row>
      </div>
    );
  }

  render() {
    const { brainstorm, brainstormStore } = this.props;
    return (
      <div>
        <h3> Brainstorm: {brainstorm.title} </h3>
        <p className="lead">Ideation Phase</p>
        {this.renderBrainstormForm()}
        {this.renderIdeas(brainstorm, brainstormStore)}
      </div>
    );
  }
}
