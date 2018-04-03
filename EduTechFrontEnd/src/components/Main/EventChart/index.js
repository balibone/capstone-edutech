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
	        labels: ['Week 1', 'week 2', 'Week 3','Week 4', 'week 5', 'Week 6','Week 7', 'week 8', 'Week 9'],
	        datasets:[
	          {
	              label: "Personal",
	              backgroundColor: "blue",
	              data: [3,7,4,6,7,4,3,5,6,3,2]
	          },
	          {
	              label: "Meeting",
	              backgroundColor: "red",
	              data: [4,3,5,6,4,3,2,5,6,4,5]
	          },
	          {
	              label: "Task Deadline",
	              backgroundColor: "green",
	              data: [7,2,6,1,3,5,8,7,5,4,3]
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