import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Card, CardActions, CardHeader } from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

@observer
export default class SingleGroupTask extends Component {
  renderSessionButton(brainstorm) {
    switch (brainstorm.status) {
      case 0:
        return 'Add Ideas';
      case 1:
        return 'Input Votes';
      default:
        return 'View Results';
    }
  }

  render() {
    const { brainstorm, handleStartSession } = this.props;
    const sessionButtonText = this.renderSessionButton(brainstorm);
    return (
      <Card className="standardTopGap">
        <CardHeader
          title={brainstorm.title}
          subtitle={`Created on: ${brainstorm.createdAt}`}
        />
        <CardActions>
          <FlatButton
            label={sessionButtonText}
            onClick={handleStartSession}
          />
        </CardActions>
      </Card>
    );
  }
}
