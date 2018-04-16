import React, { Component } from 'react';
import { Label, Row, Button } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { RaisedButton, Divider, Paper, FlatButton, List, ListItem, Avatar, Dialog, Subheader } from 'material-ui';

import GroupingListInstructor from './GroupingListInstructor';
import AssignmentStore from '../../../stores/ModuleStore/AssignmentStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import ModuleStore from '../../../stores/ModuleStore/ModuleStore';

import { USER_IMAGE_PATH } from '../../../utils/constants';

@observer
class SingleAssignmentGroup extends Component {
	constructor() {
		super();
		this.state = {
			openMembersDialog: false,
		}
	}
	getMembersWithoutGroup() {
		const { id } = this.props.assignment;
		this.setState({ openMembersDialog: true })
		AssignmentStore.getMembersWithoutGroup(id);
	}
	doAutoAssignMembers(assignmentId) {
		const { moduleCode } = ModuleStore.selectedModule;
		GroupStore.doAutoAssignMembers(assignmentId, moduleCode);
	}
	renderViewMembersWithtouGroupBtn() {
		return (<Button bsStyle="primary" onClick={() => this.getMembersWithoutGroup()}>View Members Without Group</Button>)
	}
	renderMembersWithoutGroupDialog() {
		const { assignment } = this.props;
		const { membersWithoutGroup } = AssignmentStore;
    let studentsList = <ListItem primaryText="All students assigned." />;
    if (membersWithoutGroup && membersWithoutGroup.length > 0) {
      studentsList = membersWithoutGroup.map(member => (
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
		 <FlatButton
      label="Auto Assign Members"
			primary
			onClick={() => this.doAutoAssignMembers(assignment.id)}
		 />
    ];
    return (
      <Dialog
        title={`${assignment.title} Members`}
        actions={actions}
        modal={false}
        open={this.state.openMembersDialog}
        onRequestClose={() => this.setState({ openMembersDialog: false })}
        autoScrollBodyContent
      >
        <List>
          <Subheader>Students Without Groups</Subheader>
          {studentsList}
        </List>
      </Dialog>
    );
  }
	render() {
		const { assignment } = this.props;
		const moduleGrouping = assignment.groups;
		return (
			<div>
				<h3>
			    	{assignment.title} {this.renderViewMembersWithtouGroupBtn()}
			    	<h5><Label bsStyle="default">Max Group Size: {moduleGrouping[0].groupSize}</Label></h5>
				</h3>
				<Row>
					{
						(moduleGrouping.length > 0) ? (
							moduleGrouping.map(group =>
								(<GroupingListInstructor
									key={group.id}
									group={group}
									assignmentId={assignment.id}
								/>)
							)
							)
						: ''
					}
				</Row>
				<hr />
				{this.renderMembersWithoutGroupDialog()}
			</div>
		)
	}
}

export default SingleAssignmentGroup;
