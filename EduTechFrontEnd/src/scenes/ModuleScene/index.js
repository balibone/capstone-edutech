import React, { Component } from 'react';
import { Tabs, Tab, Paper, FlatButton, List, ListItem, Avatar, Dialog, Subheader } from 'material-ui';
import { Row, Col } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { Animated } from 'react-animated-css';
import * as qs from 'query-string';

import Lesson from './Lesson';
import Assignment from './Assignment';
import GroupingInstructor from './GroupingInstructor';
import GroupingStudent from './GroupingStudent';
import RightPanel from './RightPanel';

import LessonStore from '../../stores/LessonStore/LessonStore';
import ModuleStore from '../../stores/ModuleStore/ModuleStore';
import AssignmentStore from '../../stores/ModuleStore/AssignmentStore';
import { USER_IMAGE_PATH } from '../../utils/constants';


import Feed from '../../components/Feed';

@observer
export default class ModuleScene extends Component {
  state = {
    activeTabKey: 'Conversations',
  }

  componentDidMount() {
    const { moduleCode } = this.props.match.params;
    ModuleStore.setSelectedModule(moduleCode);
    LessonStore.getLessonsForModule(moduleCode);
    // GroupStore.populateModuleGroup(moduleCode);
    AssignmentStore.populateModuleAssignments(moduleCode);
    if (qs.parse(this.props.location.search).tabKey) {
      this.setState({ activeTabKey: qs.parse(this.props.location.search).tabKey });
    }
  }

  componentWillReceiveProps(newProps) {
    const { moduleCode } = newProps.match.params;
    ModuleStore.setSelectedModule(moduleCode);
    LessonStore.getLessonsForModule(moduleCode);
    AssignmentStore.populateModuleAssignments(moduleCode);
    // GroupStore.populateModuleGroup(moduleCode);
    if (qs.parse(this.props.location.search).tabKey) {
      this.setState({ activeTabKey: qs.parse(this.props.location.search).tabKey });
    }
  }
  renderGroupingPage() {
    return localStorage.getItem('userType') === 'student' ? <GroupingStudent /> : <GroupingInstructor />;
  }

  render() {
    const { moduleCode } = this.props.match.params;
    let module = null;
    if (ModuleStore.moduleList.length > 0) {
      module = ModuleStore.getModule(moduleCode);
    }
    return (
      <Animated animationIn="fadeIn" animationOut="fadeOut" isVisible>
        <Row>
          <Col md={8}>
          <Paper>
            <Tabs value={this.state.activeTabKey} onChange={tabKey => this.setState({ activeTabKey: tabKey })}>
              <Tab label="Conversations" value="Conversations">
                <div className="tabContent">
                  <Feed pageId={moduleCode} scene="module" />
                </div>
              </Tab>
              <Tab label="Lessons" value="Lessons">
                <div className="tabContent">
                  <Lesson />
                </div>
              </Tab>
              <Tab label="Assignments" value="Assignments">
                <div className="tabContent">
                  <Assignment moduleCode={moduleCode} />
                </div>
              </Tab>
              <Tab label="Groupings" value="Groupings">
                <div className="tabContent">
                  {this.renderGroupingPage()}
                </div>
              </Tab>
            </Tabs>
          </Paper>
          </Col>
          <Col md={4}>
            <RightPanel moduleCode={moduleCode} />
          </Col>
        </Row>
      </Animated>
    );
  }
}
