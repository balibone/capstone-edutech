import React, { Component } from 'react';
import { observer } from 'mobx-react';

import 'react-widgets/dist/css/react-widgets.css';

import SingleIdea from '../SingleIdea';

@observer
export default class BrainstormResult extends Component {
  renderIdeas(brainstorm, brainstormStore) {
    const rankedIdeas = brainstormStore.getRankedIdeas(brainstorm);
    return rankedIdeas.map(idea => (
      <SingleIdea
        key={idea.id}
        idea={idea}
        brainstorm={brainstorm}
        brainstormStore={brainstormStore}
      />
    ));
  }

  render() {
    const { brainstorm, brainstormStore } = this.props;
    return (
      <div>
        <h3> Brainstorm: {brainstorm.title} </h3>
        <p className="lead">The Results</p>
        {this.renderIdeas(brainstorm, brainstormStore)}
      </div>
    );
  }
}
