import React, { Component } from 'react';
import { Button, Row, Col } from 'react-bootstrap';

class MeetingForm extends Component {
state = {
  title: '',
  description: '',
  location: '',
  startTime: new Date(),
  endTime: new Date(),
  checkeBox: false,
}

  closeMeetingForm() {
    console.log('close meeting form');
  }

  addMeetingItem() {
    const { title, location, description } = this.state;
    console.log('add meeting item: ', title, location, description);
  }

  render() {
    return (
      <div className="paperDefault">
          <div className="text-right" >
              <i className="fas fa-times fa-1x btnHover" onClick={this.closeMeetingForm.bind(this)}></i>
            </div>
          <div className="form-group row mt-1">
            <label htmlFor="eventName" className="col-2 col-form-label">Meeting Title:</label>
            <div className="col-8">
              <input className="form-control" type="text" id="eventName" ref="name"
                onChange={(e)=>this.setState({title: e.target.value})}
              />
            </div>
          </div>

          <div className="form-group row mt-1">
            <label htmlFor="location" className="col-2 col-form-label">Location:</label>
            <div className="col-8">
              <input className="form-control" type="text" id="location" ref="location"
              onChange={(e)=>this.setState({location: e.target.value})}
              />
            </div>
          </div>

          <div className="form-group row mt-1">
            <label htmlFor="comment">Description:</label>
            <textarea className="form-control" rows="5" id="comment" ref="description"
            onChange={(e)=>this.setState({description: e.target.value})}
            >
            </textarea>
          </div>

        <Row className="smallTopGap">
          <Col md={12}>
            <Button bsStyle="primary" className="pull-right" onClick={this.addMeetingItem.bind(this)}>
              Create Meeting
            </Button>
          </Col>
        </Row>
      </div>
    )
  }
}

export default MeetingForm;
