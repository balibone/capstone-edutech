import React, {Component} from 'react';
import axios, { post } from 'axios';

class UploadFileBtn extends Component {

  constructor(props) {
    super(props);
    this.state ={
      file:null
    }
    this.onFormSubmit = this.onFormSubmit.bind(this)
    this.onChange = this.onChange.bind(this)
    this.fileUpload = this.fileUpload.bind(this)
  }

  onFormSubmit(event){
    event.preventDefault()
    let selectedFile = this.state.file;
    if(selectedFile){
	    const formData = new FormData();
	    formData.append('file', selectedFile)
      console.log('SELECTED FILE: ', selectedFile)
      console.log('UPLOADING FORM: ', formData)
	    // axios.post('/scheduleitem/meetingminute/upload', )
	    // .then((res) => {
	    // 	console.log("upload success", res)
	    // })
	    // .catch((err) => {
	    // 	console.log(err);
	    // })
    }

    // this.fileUpload(this.state.file).then((response)=>{
    //   console.log(response.data);
    // })
  }

  onChange(e) {
    this.setState({file:e.target.files[0]})
  }

  fileUpload(file){
    const url = 'http://example.com/file-upload';
    const formData = new FormData();
    formData.append('file',file)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    return  post(url, formData,config)
  }

  render() {
  	let file = this.state.file;
  	console.log("seleced file: ", file);
  	if(file){
  		if(this.state.file.size > 1000){
	  		console.log("File size more than 1000 bytes")
	  	}else{
	  		console.log("File size less than 1000 bytes")
	  	}
  	}
  	
    return (
      <form onSubmit={this.onFormSubmit}>
        <h1>File Upload</h1>
        <input type="file" onChange={this.onChange} size="1000" />
        <button type="submit">Upload</button>
      </form>
   )
  }
}

export default UploadFileBtn;