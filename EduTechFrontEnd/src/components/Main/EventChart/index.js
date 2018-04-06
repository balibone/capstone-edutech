import React, {Component} from 'react';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';
import BarChart from './BarChart';
import { Paper } from 'material-ui';
import {Col} from 'react-bootstrap';
import moment from 'moment';
import axios from 'axios';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';

@observer
class EventChart extends Component {

	constructor(){
	    super();
	    this.state = {
	    	numberOfWeeks: [],
	    	semesterStartDate: null,
	    	chartData:{}
	    }
	  }

	  componentWillMount(){
	    this.getChartData();
	  }

	  componentDidMount(){
	  	axios.get('/semester')
        .then((res) => {
          const startD = moment(res.data[0].startDate);
          const endD = moment(res.data[0].endDate);

          var result = endD.diff(startD, 'week') + 1;

          this.setState({numberOfWeeks: result, semesterStartDate: startD})
        })
        .catch((err) => {
          console.log(err)
        })
	  }

	  getChartData(){
	    // Ajax calls here
	    this.setState({
	      chartData:{
	        labels: [],
	        datasets:[
	          {
	              	label: "Personal",
	              	backgroundColor: "blue",
	              	data: []
	          },
	          {
	              	label: "Meeting",
	              	backgroundColor: "red",
	              	data: []
	          },
	          {
	              	label: "Task Deadline",
	              	backgroundColor: "green",
	              	data: []
	          },
	          {
	          		label: "Assessment",
	              	backgroundColor: "orange",
	              	data: []	
	          }
	        ]
	      }
	    });
	  }

	render(){
		const scheduleItems = toJS(ScheduleItemStore.scheduleItems);
		var semesterStartDate = moment(this.state.semesterStartDate);
		
		// console.log(semesterStartDate.add(7, 'days').format());
		console.log("ScheduleItems in chart: ", scheduleItems)
		console.log("semester start Date: ", semesterStartDate)
		// console.log("num of weeks", this.state.numberOfWeeks)

		return(
			
				<Paper className="paperDefault">
					<BarChart 
						chartData={this.state.chartData} 
						numberOfWeeks={this.state.numberOfWeeks} 
						scheduleItems={scheduleItems}
						semesterStartDate={semesterStartDate}
					/>
				</Paper>
			
		)
	}
}

export default EventChart;