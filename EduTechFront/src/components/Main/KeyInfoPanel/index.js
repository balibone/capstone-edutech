import React, {Component} from 'react';
import { Paper } from 'material-ui';
import {observer} from 'mobx-react';
import { Col, Row } from 'react-bootstrap';

// import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

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
    // console.log("before map", getRecentKeyDates)
    console.log("before map", getRecentKeyDates.length)
    var length = 3;
    if(this.state.viewAll)
      length = getRecentKeyDates.lengt;
    if(getRecentKeyDates){

      return getRecentKeyDates.slice(0,length).map((item)=>{
          console.log("ITEM: ",item);
          return (
            <Col md={4} key={item.itemDetails._id}>
                <Paper className="keyInfoCard paperDefault">
                  <h4> {item.itemDetails.name} </h4>
                  <p className="thinFont"> <strong>{item.itemDetails.startTime} - {item.itemDetails.endTime}</strong> </p>
                </Paper>
            </Col>)
        })

    } 
    return <span></span>    
  }



  render(){
    const ScheduleItemStore = this.props.ScheduleItemStore
    // console.log(ScheduleItemStore.scheduleItems)

    return(
      <Paper className="keyInfoPanel">
        
        <Row>
          <h3>
            <i className="far fa-calendar" /> Key Dates
            <small> · <a href="#" onClick={this.viewAll.bind(this)}>View {this.state.viewAll ? "Less" : "All"}</a></small>
          </h3>
        </Row>
        <Row>
        {this.renderKeyInfo(ScheduleItemStore.getRecentKeyDates)}
        </Row>
          
      </Paper>

    )
  }
}

export default KeyInfoPanel;
