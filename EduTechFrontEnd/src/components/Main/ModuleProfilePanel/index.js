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
    const { primaryInfo, secondaryInfo } = this.props;
    this.setState({
      moduleCode: primaryInfo,
      moduleTitle: secondaryInfo
    })

  }
  componentWillReceiveProps(newProps) {
    const { primaryInfo, secondaryInfo } = newProps;
    this.setState({
      moduleCode: primaryInfo,
      moduleTitle: secondaryInfo
    })

  }

  render() {


    let content = (
      <div>
        <h4><span className="capitalize">{this.state.moduleCode}</span></h4>
        <p>{this.state.moduleTitle}</p>
      </div>
    )

    return (
      <Paper className="profilePanel">
        
        { content }
      </Paper>
    )
  }
}

export default ModuleProfilePanel;