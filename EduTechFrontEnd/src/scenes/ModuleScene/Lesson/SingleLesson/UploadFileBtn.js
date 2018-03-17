import React, {Component} from 'react';
import axios, { post } from 'axios';
import {Button} from 'react-bootstrap';
import swal from 'sweetalert';

class UploadFileBtn extends Component {

  constructor(props) {
    super(props);
    this.state ={
      file:null
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
  }

  onFormSubmit(event){
    event.preventDefault()
    let selectedFile = this.state.file;

    if(selectedFile){
      if(selectedFile.size > 10000000){   // 10MB is max file size
        swal("File Size Error!", "Your file size is more than 10MB.", "error");
      }else{
        const formData = new FormData();
        formData.append('file', selectedFile)
        console.log('SELECTED FILE: ', selectedFile)
        console.log('UPLOADING FORM: ', formData)
        //call api method here
        swal("Success !", `${selectedFile.name} is successfully uploaded.`, "success");
      }
    }
  }

  onChange(e) {
    this.setState({file:e.target.files[0]})
  }

  // fileUpload(file){
  //   const url = 'http://example.com/file-upload';
  //   const formData = new FormData();
  //   formData.append('file',file)
  //   const config = {
  //       headers: {
  //           'content-type': 'multipart/form-data'
  //       }
  //   }
  //   return  post(url, formData,config)
  // }

  render() {
  	let file = this.state.file;
    	
    return (
      <form className="standardTopGap" onSubmit={this.onFormSubmit}>
        <input type="file" onChange={this.onChange} size="1000" />
        <Button className="standardTopGap" type="submit" bsStyle="primary">Upload Attachment</Button>
      </form>

   )
  }
}

export default UploadFileBtn;