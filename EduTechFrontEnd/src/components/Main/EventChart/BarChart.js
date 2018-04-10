import React, {Component} from 'react';
import {Bar} from 'react-chartjs-2';
import moment from 'moment';
import {Paper} from 'material-ui';

import {FadingCircle} from 'better-react-spinkit';

import './styles.css';

class BarChart extends Component {

	static defaultProps = {
	    displayTitle:true,
	    displayLegend: true,
	    legendPosition:'right',
	    location:'City'
	  }

	getCalendarItems(data, scheduleItems, semesterStartDate, numberOfWeeks){
		const personalItems = scheduleItems.filter((item) => item.itemType === 'personal')
		const meetingItems = scheduleItems.filter((item) => item.itemType === 'meeting')
		const taskItems = scheduleItems.filter((item) => item.itemType === 'task')
		const assessmentItems = scheduleItems.filter((item) => item.itemType === 'assessment')
		let personalItemsArr = [];
		let meetingItemsArr = [];
		let taskItemsArr = [];
		let assessmentItemsArr = [];
		let count = 0;
		let d1 = semesterStartDate.format('YYYY-MM-DD');
		let d2 = semesterStartDate.add(7, 'days').format('YYYY-MM-DD');

		for(var i=0 ; i<numberOfWeeks ; i++){
			// console.log("d1: ", d1)
			// console.log("d2: ", d2)
			count = 0;
			
			personalItems.forEach((item) => {
				if(item.startDate>d1 && item.startDate<d2){
					// console.log("personal exist in between")
					count++;
				}
			})
			personalItemsArr.push(count);
			count = 0;

			meetingItems.forEach((item) => {
				if(item.startDate>d1 && item.startDate<d2){
					// console.log("meeting exist in between")
					count++;
				}
			})
			meetingItemsArr.push(count);
			count = 0;

			taskItems.forEach((item) => {
				if(item.startDate>d1 && item.startDate<d2){
					// console.log("task exist in between")
					count++;
				}
			})
			taskItemsArr.push(count);
			count = 0;

			assessmentItems.forEach((item) => {
				if(item.startDate>d1 && item.startDate<d2){
					// console.log("task exist in between")
					count++;
				}
			})
			assessmentItemsArr.push(count);

			d1 = d2;
			d1 = moment(d1).format('YYYY-MM-DD');
			d2 = moment(d2).add(7,'days').format('YYYY-MM-DD');
			// console.log("d1 updated: ", d1)
			// console.log("d2 updated: ", d2)
		}

		// console.log("personal arr: ", personalItemsArr);
		// console.log("meeting arr: ", meetingItemsArr);
		// console.log("task arr: ", taskItemsArr);
		console.log("data:", data);
		data.datasets[0].data = personalItemsArr;
		data.datasets[1].data = meetingItemsArr;
		data.datasets[2].data = taskItemsArr;
		data.datasets[3].data = assessmentItemsArr;
		console.log("updated data: ", data)
		return data;
	}

	renderBarChart(data, numberOfWeeks){
		const semester = this.props.semester;
		let weekLabels = []
		for(var i=1 ; i<=numberOfWeeks ; i++){
			weekLabels.push('Week ' + i);
		}
		data.labels = weekLabels;
		console.log("BAR CHART", data)
		return (
			<div>
			<div style={{textAlign: 'center'}}>
				<h3>Semester Weekly Chart</h3>
				<p className="lead">{semester.title}</p>
			</div>
			<Bar
		          data={data}
		          onClick={(info) => console.log(info)}
		          options={{
		          		barValueSpacing: 5,
		          		// maintainAspectRatio: false,
		          		scales: {
					      yAxes: [{
					        ticks: {
					          min: 0,
					        }
					      }]
					    },
		            	legend:{
		              		display:true,
		              		position:'right'
		            	}
		          	}}
		          	height={100}
		        />
		        </div>	
		)
	}

	render(){
		let data = this.props.chartData;
		const scheduleItems = this.props.scheduleItems;
		const numberOfWeeks = this.props.numberOfWeeks;
		const semesterStartDate = this.props.semesterStartDate;
		
		data = this.getCalendarItems(data, scheduleItems, semesterStartDate, numberOfWeeks);

		return(
			<div>

				{
					(scheduleItems.length>0 && numberOfWeeks>0) ?
					(<div>{this.renderBarChart(data, numberOfWeeks)}</div>)
					: (<div style={{display: 'flex', justifyContent: 'center'}}><FadingCircle color="#6495ED" size={100}/></div>) 	
				}
			</div>
		)
	}

}

export default BarChart;