import React, {Component} from 'react';
import {Panel, Button, Grid, Row, Col,ListGroup, ListGroupItem, Badge} from 'react-bootstrap';
import moment from 'moment';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';
import axios from 'axios';
import _ from 'lodash';

import UploadFileBtn from './UploadFileBtn';
import DownloadAllFileBtn from './DownloadAllFileBtn';
import DownloadSingleFile from './DownloadSingleFile';

import './styles.css';

class SingleLesson extends Component {

constructor(props){
  super(props)
  this.state = {
    files: [],
    uploadedFile: this.props.uploadedFile
  }
}

componentDidMount(){
  const lessonId = this.props.lesson.id;

  axios.get(`/lesson/allAttachments/${lessonId}`)
    .then((res) => {
      this.state.files = res.data;
      // console.log("lessonId and file", lessonId, this.state.files);
    })
    .catch((err) => {
      console.log(err);
    })
}

componentDidUpdate(prevProps, prevState){
  if (prevProps.uploadedFile !== this.props.uploadedFile) {
    this.setState({uploadedFile: this.props.uploadedFile})
  }
}

renderAttachmentBtn() {
    const wellStyles = { maxWidth: 400, margin: '0 auto 10px' };
    const lessonId = this.props.lesson.id;
    const lesson = toJS(this.props.lesson);
    var files = this.state.files;
    var uploadedFile = this.state.uploadedFile;
    var fileListArea = (<span>LOADING...</span>)
    var downloadAllFileBtnArea = (<span></span>)
    var uploadedFileArea = (<span></span>)
    if (this.state.uploadedFile.length > 0) {
      console.log("this uploaded file:", this.state.uploadedFile);
    }

    if (files && files.length>0) {
      fileListArea = files.map((file) => {
        return <DownloadSingleFile key={file.id} file={file} lessonId={lessonId}/>
      })
      downloadAllFileBtnArea = (<DownloadAllFileBtn lessonId={lessonId} lesson={lesson}/>)
    } else {
      fileListArea = (<p>No file for this lesson.</p>)
    }

    if (uploadedFile && uploadedFile.length > 0) {
      uploadedFileArea = (<DownloadSingleFile file={uploadedFile} lessonId={lessonId}/>)
    }
    return (
            <Row className="show-grid">
              <Col xs={12} md={8}>
                <ListGroup>
                    { fileListArea }
                </ListGroup>
                {downloadAllFileBtnArea}
              </Col>
              <Col xs={6} md={4}>
                <div className="well" style={wellStyles}>
                  <UploadFileBtn lessonId={lessonId}/>
                </div>
              </Col>
            </Row>
    )
  }

  render(){
    const {id, title, startDate, endDate} = this.props.lesson;
    const start = moment(startDate).format("h:mm a");
    const end = moment(endDate).format("h:mm a");
    const date = moment(startDate).format("dddd, Do MMMM");
    const currentDateTime = moment(new Date());
    const lessonEndDateTime = moment(endDate);

    return(
      <Panel eventKey={id}>
          <Panel.Heading>
              <Panel.Title toggle>{title}
              {
                currentDateTime>lessonEndDateTime ?
                (<Badge className="pull-right" style={{background: '#008B8B'}}>Completed</Badge>) : (<span></span>)
              }

              <p className="smallText">{date}, {start} - {end} </p>

              </Panel.Title>
          </Panel.Heading>
          <Panel.Body collapsible>
              {this.renderAttachmentBtn()}
          </Panel.Body>
       </Panel>
    )
  }
}

export default SingleLesson;
