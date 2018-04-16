import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Row, Button, FormControl } from 'react-bootstrap';
import { RaisedButton, Divider, Paper, FlatButton, List, ListItem, Avatar, Dialog, Subheader } from 'material-ui';
import moment from 'moment';
import './styles.css';

import AnnouncementStore from '../../../stores/AnnouncementStore/AnnouncementStore';
import AddAssessmentDialog from './AddAssessmentDialog';
import AssessmentListView from './AssessmentListView';
import ModuleStore from '../../../stores/ModuleStore/ModuleStore';
import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

import { USER_IMAGE_PATH } from '../../../utils/constants';

@observer
class RightPanel extends Component {
  constructor() {
    const tempDescription = ModuleStore.selectedModule.description;
    super();
    this.state = {
      assessmentDialogState: false,
      startDate: new Date(),
      endDate: new Date(),
      title: '',
      description: '',
      location: '',
      openMembersDialog: false,
      editView: false,
      tempDescription,
    };
  }

  handleEnterPress(e) {
    if (e.which === 13) {
      ModuleStore.editModuleDescription(e.target.value);
      this.setState({ editView: false });
    }
  }

  handleCloseAssessmentForm() {
    this.setState({
      assessmentDialogState: false,
      title: '',
      description: '',
      location: '',
    });
  }
  handleAddAssessment(moduleCode) {
    const {
      title, description, startDate, endDate, location,
    } = this.state;
    const assignedTo = [];
    const itemType = 'assessment';
    const groupId = null;
    ScheduleItemStore.addScheduleItem(
        title, description, startDate,
        endDate, location, assignedTo, itemType, moduleCode, groupId,
      );
    AnnouncementStore.postAnnouncement(
      moduleCode,
      `${title} created as assessment`,
      ModuleStore.selectedModule.members,
      `/module/${moduleCode}`,
    );
    this.handleCloseAssessmentForm();
  }
  openAssessmentDialog() {
    this.setState({
      assessmentDialogState: true,
      startDate: moment(new Date()).format('YYYY-MM-DD HH:mm:ss'),
      endDate: moment(new Date()).format('YYYY-MM-DD HH:mm:ss'),
    })
  }
  renderAddAssessmentDialog(moduleCode) {
    return (
      <AddAssessmentDialog
        handleTitleChange={e => this.setState({ title: e.target.value })}
        handleDescriptionChange={e => this.setState({ description: e.target.value })}
        handleLocationChange={venue => this.setState({ location: venue })}
        handleStartChange={start => this.setState({ startDate: start })}
        handleEndChange={end => this.setState({ endDate: end })}
        handleCloseAssessmentForm={() => this.handleCloseAssessmentForm()}
        handleAddAssessment={() => this.handleAddAssessment(moduleCode)}
        dialogState={this.state.assessmentDialogState}
        scheduleStart={this.state.startDate}
        scheduleEnd={this.state.endDate}
        scheduleLocation={this.state.location}
        scheduleTitle={this.state.title}
        scheduleDescription={this.state.description}
      />
    )
  }
  renderCreateAssessmentBtn(userType) {
    if (userType === 'instructor') {
      return (<Button className="standardTopGap" bsStyle="primary" block onClick={() => this.openAssessmentDialog()}>Add Assessment</Button>)
    }
    return '';
  }
  renderMembersDialog() {
    console.log('ModuleStore.selectedModule', ModuleStore.selectedModule)
    let instructorsList = <ListItem primaryText="Instructors will be assigned soon." />;
    if (ModuleStore.instructors && ModuleStore.instructors > 0) {
      instructorsList = ModuleStore.instructors.map(member => (
          <ListItem
            primaryText={member.userFirstName + ' ' + member.userLastName}
            leftAvatar={<Avatar src={USER_IMAGE_PATH + member.imgFileName} />}
          />
        ));
    }
    let studentsList = <ListItem primaryText="Students will be assigned soon." />;
    if (ModuleStore.students && ModuleStore.students.length > 0) {
      studentsList = ModuleStore.students.map(member => (
          <ListItem
            primaryText={member.userFirstName + ' ' + member.userLastName}
            leftAvatar={<Avatar src={USER_IMAGE_PATH + member.imgFileName} />}
          />
        ));
    }

    const actions = [
     <FlatButton
       label="Close"
       primary
       onClick={() => this.setState({ openMembersDialog: false })}
     />,
    ];
    return (
      <Dialog
        title={`${ModuleStore.selectedModule.title} Members`}
        actions={actions}
        modal={false}
        open={this.state.openMembersDialog}
        onRequestClose={() => this.setState({ openMembersDialog: false })}
        autoScrollBodyContent
      >
        <List>
          <Subheader>Instructors</Subheader>
          {instructorsList}
          <Subheader>Students</Subheader>
          {studentsList}
        </List>
      </Dialog>
    );
  }
  renderGroupDescription() {
    if (this.state.editView) {
      return (
        <FormControl
          type="text"
          value={this.state.tempDescription}
          onChange={e => this.setState({ tempDescription: e.target.value })}
          onKeyPress={e => this.handleEnterPress(e)}
        />
      )
    }
    return (
        <p>
          {ModuleStore.selectedModule.description}
          <i className="fas fa-edit" onClick={() => this.setState({ editView: !this.state.editView })} />
        </p>
    );
  }
  render() {
    const module = ModuleStore.getModule(this.props.moduleCode);
    const userType = localStorage.getItem('userType');
    const { moduleCode, title } = module;
    return (
            <Paper className="moduleInfo">
              <Row className="sideTopSection">
                <h2> {moduleCode} </h2>
                {this.renderGroupDescription()}
                <br />
                <RaisedButton label="View Members" onClick={() => this.setState({ openMembersDialog: true })} />
                <br />
                <br />
              </Row>
              <Divider />
              <Row className="taskChartItem">
              <AssessmentListView moduleCode={moduleCode} />
              {this.renderCreateAssessmentBtn(userType)}
              {this.renderAddAssessmentDialog(moduleCode)}
              </Row>
              {this.renderMembersDialog()}
            </Paper>
    );
  }
}
export default RightPanel;
