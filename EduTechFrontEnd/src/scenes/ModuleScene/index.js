import React, {Component} from 'react';
import PropTypes from 'prop-types';
import { Paper } from 'material-ui';
import { Tabs, Tab } from 'react-bootstrap';
import {observer} from 'mobx-react';

import Feed from '../../components/Feed';
import Lesson from './Lesson';
import Submission from './Submission';
import Assignment from './Assignment';

import LessonStore from '../../stores/LessonStore/LessonStore';

@observer
class ModuleScene extends Component {

  componentDidMount(){
    let { moduleCode } = this.props.match.params;
    LessonStore.getLessonsForModule(moduleCode);

  }
  componentWillReceiveProps(newProps) {
    let { moduleCode } = newProps.match.params;
    LessonStore.getLessonsForModule(moduleCode);
  }

  render(){
    const { match } = this.props;
    return(
      <Paper className="standardTopGap standardBottomGap paperDefault">
        <Tabs defaultActiveKey={1} id="uncontrolled-tab-example">
          <Tab eventKey={1} title={match.params.moduleCode}>
            <Feed pageId={match.params.moduleCode} />
          </Tab>
          <Tab eventKey={2} title="Lessons">
            <Lesson />
          </Tab>
          <Tab eventKey={3} title="Submissions">
            <Submission />
          </Tab>
          <Tab eventKey={4} title="Assignment">
            <Assignment />
          </Tab>
        </Tabs>
      </Paper>
    )
  }
}

export default ModuleScene;
