import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import { Button } from 'react-bootstrap';
import 'react-widgets/dist/css/react-widgets.css';

import BrainstormIdeation from './BrainstormIdeation';
import BrainstormVoting from './BrainstormVoting';
import BrainstormResult from './BrainstormResult';


@observer
export default class BrainstormSession extends Component {
  render() {
    const { brainstorm, brainstormStore, handleEndSession } = this.props;
    let brainstormView;
    let doneButton;
    switch (brainstorm.status) {
      case 0:
        doneButton =
          <Button bsStyle="primary" onClick={() => brainstorm.status += 1}>Done Ideating</Button>;
        brainstormView =
          <BrainstormIdeation brainstormStore={brainstormStore} brainstorm={brainstorm} />;
        break;
      case 1:
        doneButton =
          <Button bsStyle="primary" onClick={() => brainstorm.status += 1}>Done Voting</Button>;
        brainstormView =
          <BrainstormVoting brainstormStore={brainstormStore} brainstorm={brainstorm} />;
        break;
      default:
        doneButton = <span />;
        brainstormView =
          <BrainstormResult brainstormStore={brainstormStore} brainstorm={brainstorm} />;
    }
    return (
      <div className="taskItem" >
        {brainstormView}
        <div className="standardTopGap">
          {doneButton}
          <Button onClick={() => handleEndSession()}>Exit Brainstorm</Button>
        </div>
      </div>
    );
  }
}
