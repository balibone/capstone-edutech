import React, {Component} from 'react';
import {observer} from 'mobx-react';
import axios, { post } from 'axios';
import {Button, Grid, Row, Col, FormControl, ControlLabel} from 'react-bootstrap';
import swal from 'sweetalert';

import LessonStore from '../../../../stores/LessonStore/LessonStore';

@observer
class UploadFileBtn extends Component {

  constructor(props) {
    super(props);
    this.state ={
      title: "",
      file:null
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
  }

  onFormSubmit(event){
    event.preventDefault()
    const selectedFile = this.state.file;
    const lessonId = this.props.lessonId;
    const title = this.state.title;

    if(selectedFile){
      if(selectedFile.size > 10000000){   // 10MB is max file size
        swal("File Size Error!", "Your file size is more than 10MB.", "error");
      }else{
        LessonStore.uploadAttachment(title, selectedFile, lessonId);
      }
    }
  }

  onChange(e) {
    this.setState({file:e.target.files[0]})
  }

  handleChangeTitle(e){
    this.setState({ title: e.target.value });
  }

  render() {
  	let file = this.state.file;
    	
    return (
      <form className="standardTopGap" onSubmit={this.onFormSubmit}>
        <input type="file" onChange={this.onChange} size="1000" />
        {
          this.state.file !==null ?
          (
            <div>
              <ControlLabel>Description (optional)</ControlLabel>
              <FormControl
                type="text"
                value={this.state.title}
                placeholder="Enter text"
                onChange={this.handleChangeTitle.bind(this)}
              />
            </div>
          ): (<span></span>) 
        }
        <Button className="standardTopGap" type="submit" bsStyle="primary" block>Upload Attachment</Button>
      </form>

   )
  }
}

export default UploadFileBtn;