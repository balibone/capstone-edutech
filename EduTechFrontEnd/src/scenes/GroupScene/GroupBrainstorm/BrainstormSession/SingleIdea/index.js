import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';

import './styles.css';

const voter = {
  id: 1,
  name: 'Hafidz',
  img: 'avatar.png',
};


@observer
export default class SingleIdea extends Component {
  state = {
    hasVoted: false,
  }

  removeIdea(brainstormStore, brainstorm, idea) {
    brainstormStore.removeIdea(brainstorm, idea);
  }

  toggleVoteIdea(brainstormStore, brainstorm, idea) {
    const hasVoted = brainstormStore.toggleVoteIdea(brainstorm, idea, voter);
    this.setState({ hasVoted });
  }

  renderActionIcons(brainstormStore, brainstorm, idea) {
    const voteIcon = this.state.hasVoted ? 'fa fa-thumbs-up' : 'far fa-thumbs-up';
    switch (brainstorm.status) {
      case 0:
        return <i className="fa fa-trash" onClick={() => this.removeIdea(brainstormStore, brainstorm, idea)} />;
      case 1:
        return (
          <i
            className={voteIcon}
            onClick={() => this.toggleVoteIdea(brainstormStore, brainstorm, idea)}
          />);
      default:
        return <span> votes: {idea.voters.length}</span>;
    }
  }

  render() {
    const { brainstormStore, brainstorm, idea } = this.props;

    return (
      <Paper className="taskItem paperDefault">
        <span>{idea.title}</span>
        <div className="pull-right taskActions">
          {this.renderActionIcons(brainstormStore, brainstorm, idea)}
        </div>
      </Paper>
    );
  }
}
