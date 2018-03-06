import React, { Component } from 'react';
import { Paper } from 'material-ui';
import { Button, FormControl } from 'react-bootstrap';
import swal from 'sweetalert';

import GroupStore from '../../../stores/GroupStore/GroupStore';
import './styles.css';

class GroupProfilePanel extends Component {
  state = {
    editView: false,
    groupTitle: this.props.primaryInfo,
    groupDescription: this.props.secondaryInfo,
  }

  componentWillMount() {
    const { img, primaryInfo, secondaryInfo, group } = this.props;
    this.setState({
      groupTitle: primaryInfo,
      groupDescription: secondaryInfo
    })

  }
  componentWillReceiveProps(newProps) {
    const { img, primaryInfo, secondaryInfo, group } = newProps;
    this.setState({
      groupTitle: primaryInfo,
      groupDescription: secondaryInfo
    })

  }

  handleSaveEdit() {
    if (this.state.groupTitle && this.state.groupDescription) {
      this.props.group.title = this.state.groupTitle;
      this.props.group.description = this.state.groupDescription;
      GroupStore.editGroupDetails(this.props.group);
      console.log('handleSaveEdit this.props.group.title', this.props.group.title)
      this.setState({editView: false});
    } else {
      swal("Ooops!", "Group name and description cannot be empty.", "error")
    }
  }




  render() {
    const { img, primaryInfo, secondaryInfo, group } = this.props;
    console.log('?????', primaryInfo)

    let content = (
      <div>
        <h4><span className="capitalize">{this.state.groupTitle}</span></h4>
        <p>{this.state.groupDescription}</p>
        <Button onClick={() => this.setState({editView: true})}> Edit </Button>
      </div>
    )

    if (this.state.editView) {
      content = (
        <div>
          <FormControl
            onChange={e => this.setState({groupTitle: e.target.value})}
            type="text"
            value={this.state.groupTitle}
            placeholder="Group Title"
          />
          <FormControl
            onChange={e => this.setState({groupDescription: e.target.value})}
            type="text"
            value={this.state.groupDescription}
            placeholder="Group Description"
          />
          <Button onClick={() => this.handleSaveEdit()} bsStyle="primary"> Save </Button>
        </div>
      )
    }
    return (
      <Paper className="profilePanel">
        <img src={`http://localhost:8080/EduTechWebApp-war/uploads/edutech/group/images/${img}`} alt="profile" height="110" className="img-circle img-profile" />
        { content }
      </Paper>
    )
  }
}

export default GroupProfilePanel;
