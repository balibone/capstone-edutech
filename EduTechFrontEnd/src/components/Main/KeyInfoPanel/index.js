import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {observer} from 'mobx-react';
import { Col, Row } from 'react-bootstrap';
import moment from 'moment';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';

import './styles.css';


@observer
class KeyInfoPanel extends Component{

  constructor(){
      super()
      this.state = {
        viewAll : false
      }
  }

  viewAll(){
    this.setState({viewAll: !this.state.viewAll})
  }

  renderKeyInfo(getRecentKeyDates){
    var length = 3;
    console.log("keydates before", getRecentKeyDates)
    if(GroupStore.selectedGroup){
      getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.type === "meeting")
      console.log("keydates between", getRecentKeyDates)
      getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.groupId === GroupStore.selectedGroup.id)
    }
        console.log("keydates after", getRecentKeyDates)

    getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.type !== "timetable")
    if(this.state.viewAll)
      length = getRecentKeyDates.length;
    if(getRecentKeyDates){
      return getRecentKeyDates.slice(0,length).map((item)=>{
          
          return (
            <Col md={4} key={item.itemDetails.id}>
                <Paper className="keyInfoCard paperDefault">
                  <h4 className="truncate"> {item.itemDetails.title} </h4>
                  <p className="thinFont truncate"> 
                    {moment(item.itemDetails.startDate).format('MMM DD YYYY')}
                  </p>
                  <p className="truncate">
                    {moment(item.itemDetails.startDate).format('HH:mm')} - {moment(item.itemDetails.endDate).format('HH:mm')}
                  </p>
                </Paper>
            </Col>)
        })

    } 
    return <span></span>    
  }



  render(){  
    let keyInfoArea = this.renderKeyInfo(ScheduleItemStore.getRecentKeyDates)
    if(GroupStore.selectedGroup){
      console.log("GroupStore selectedGroup GroupId: ", GroupStore.selectedGroup.id);
      keyInfoArea = this.renderKeyInfo(ScheduleItemStore.getRecentKeyDates)
    }

    return(
      <Paper className="keyInfoPanel">
        
        <Row>
          <h3>
            <i className="far fa-calendar" /> Key Dates
            <small> · <a href="#" onClick={this.viewAll.bind(this)}>View {this.state.viewAll ? "Less" : "All"}</a></small>
          </h3>
        </Row>
        <Row>
        {keyInfoArea}
        </Row>
          
      </Paper>

    )
  }
}

export default KeyInfoPanel;
