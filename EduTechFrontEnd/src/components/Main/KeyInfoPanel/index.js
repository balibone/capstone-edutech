  import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {observer} from 'mobx-react';
import { Col, Row, Badge } from 'react-bootstrap';
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
    var color = "";

    if(GroupStore.selectedGroup){
      getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.itemType === "meeting")
      getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.groupId === GroupStore.selectedGroup.id)
    }
    getRecentKeyDates = getRecentKeyDates.filter(item => item.itemDetails.itemType !== "timetable")
    if(this.state.viewAll)
      length = getRecentKeyDates.length;

    if(getRecentKeyDates){
      // console.log("recent key dates", getRecentKeyDates)
      return getRecentKeyDates.slice(0,length).map((item)=>{
          let { id, title, startDate, endDate, itemType } = item.itemDetails;
          switch(itemType){
            case "personal": color = '#006400'
              break;
            case "meeting": color = '#FF8C00'
              break;
            case "timetable": color = '#9932CC'
              break;
            case "task": color = '#00BFFF'
              break;
            case "assessment": color = '#00BFFF'
              break;
          }
          return (
            <Col md={4} key={id}>
                <Paper className="keyInfoCard paperDefault" style={{backgroundColor: color}}>
                  <h4 className="truncate"> {title} &nbsp;</h4>
                  <p className="thinFont truncate"> 
                    {moment(startDate).format('MMM DD YYYY')}
                  </p>
                  <p className="truncate">
                    {moment(startDate).format('HH:mm')} - {moment(endDate).format('HH:mm')}
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
