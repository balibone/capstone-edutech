import React,{Component} from 'react';
import { Paper } from 'material-ui';
import { Button, FormControl } from 'react-bootstrap';
import swal from 'sweetalert';

import ModuleStore from '../../../stores/ModuleStore/ModuleStore';
import './styles.css';


class ModuleProfilePanel extends Component{

state = {
    editView: false,
    moduleCode: this.props.primaryInfo,
    moduleTitle: this.props.secondaryInfo,
  }

  componentWillMount() {
    const { img, primaryInfo, secondaryInfo, group } = this.props;
    this.setState({
      moduleCode: primaryInfo,
      moduleTitle: secondaryInfo
    })

  }
  componentWillReceiveProps(newProps) {
    const { img, primaryInfo, secondaryInfo, group } = newProps;
    this.setState({
      moduleCode: primaryInfo,
      moduleTitle: secondaryInfo
    })

  }

  handleSaveEdit() {
    if (this.state.moduleCode && this.state.moduleTitle) {
      this.props.group.title = this.state.moduleCode;
      this.props.group.description = this.state.moduleTitle;
      // GroupStore.editGroupDetails(this.props.group);
      this.setState({editView: false});
    } else {
      swal("Ooops!", "Group name and description cannot be empty.", "error")
    }
  }




  render() {
    const { img, primaryInfo, secondaryInfo, group } = this.props;


    let content = (
      <div>
        <h4><span className="capitalize">{this.state.moduleCode}</span></h4>
        <p>{this.state.moduleTitle}</p>
        <Button onClick={() => this.setState({editView: true})}> Edit </Button>
      </div>
    )

    if (this.state.editView) {
      content = (
        <div>
          <FormControl
            onChange={e => this.setState({moduleCode: e.target.value})}
            type="text"
            value={this.state.moduleCode}
            placeholder="Group Title"
          />
          <FormControl
            onChange={e => this.setState({moduleTitle: e.target.value})}
            type="text"
            value={this.state.moduleTitle}
            placeholder="Group Description"
          />
          <Button onClick={() => this.handleSaveEdit()} bsStyle="primary"> Save </Button>
        </div>
      )
    }
    return (
      <Paper className="profilePanel">
        
        { content }
      </Paper>
    )
  }
}

export default ModuleProfilePanel;