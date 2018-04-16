import React, { Component } from 'react';
import { Bar } from 'react-chartjs-2';
import { observer } from 'mobx-react';
import { Paper } from 'material-ui';
import moment from 'moment';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import {
  PERSONAL_ITEM_COLOR, MEETING_ITEM_COLOR,
  ASSESSMENT_ITEM_COLOR, TASK_ITEM_COLOR,
} from '../../../utils/constants';
import './styles.css';

@observer
export default class ScheduleItemsChart extends Component {
  getScheduleItemsCount() {
    const numberOfWeeks = ScheduleItemStore.semesterNumberOfWeeks;
    let startDate = moment(ScheduleItemStore.semester.startDate).format('YYYY-MM-DD');
    let endDate = moment(ScheduleItemStore.semester.startDate).add(7, 'days').format('YYYY-MM-DD');
    const personalItemsCounts = [];
    const meetingItemsCounts = [];
    const taskItemsCounts = [];
    const assessmentItemsCounts = [];

    for (let i = 0; i < numberOfWeeks; i += 1) {
      personalItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, ScheduleItemStore.personalItems));
      meetingItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, ScheduleItemStore.meetingItems));
      taskItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, ScheduleItemStore.taskItems));
      assessmentItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, ScheduleItemStore.assessmentItems));
      startDate = endDate;
      endDate = moment(endDate).add(7, 'days').format('YYYY-MM-DD');
    }
    return {
      personalItemsCounts,
      meetingItemsCounts,
      taskItemsCounts,
      assessmentItemsCounts,
    };
  }

  getScheduleItemsCountWithinDates(startDate, endDate, scheduleItems) {
    const correctScheduleItems = scheduleItems.filter(scheduleItem =>
        scheduleItem.startDate > startDate && scheduleItem.startDate < endDate);
    return correctScheduleItems.length;
  }

  getWeekLabels() {
    const numberOfWeeks = ScheduleItemStore.semesterNumberOfWeeks;
    const weekLabels = [];
    for (let i = 1; i <= numberOfWeeks; i += 1) {
      if (i === 7) weekLabels.push('Recess Week');
      else if (i === 14) weekLabels.push('Reading Week');
      else if (i > 14) weekLabels.push('Exam Week');
      else weekLabels.push(`Week ${i}`);
    }
    return weekLabels;
  }

  getScheduleItemsDataset() {
    const {
      personalItemsCounts, meetingItemsCounts, taskItemsCounts, assessmentItemsCounts,
    } = this.getScheduleItemsCount();
    const weekLabels = this.getWeekLabels();
    const data = {
	        labels: weekLabels,
	        datasets: [
	          {
	              	label: 'Personal',
	              	backgroundColor: PERSONAL_ITEM_COLOR,
	              	data: personalItemsCounts,
	          },
	          {
	              	label: 'Meeting',
	              	backgroundColor: MEETING_ITEM_COLOR,
	              	data: meetingItemsCounts,
	          },
	          {
	              	label: 'Task Deadline',
	              	backgroundColor: TASK_ITEM_COLOR,
	              	data: taskItemsCounts,
	          },
	          {
	          		label: 'Assessment',
	              	backgroundColor: ASSESSMENT_ITEM_COLOR,
	              	data: assessmentItemsCounts,
	          },
	        ],
	      };
      return data;
    }

  render() {
    return (
      <Paper className="weeklyChart">
        <h3>Semester Weekly Chart</h3>
        <p className="lead">{ScheduleItemStore.semester.title}</p>
        <p className="dateRange">
          {moment(ScheduleItemStore.semester.endDate).format('DD/MM/YYYY')}
          &nbsp;-&nbsp;
          {moment(ScheduleItemStore.semester.endDate).format('DD/MM/YYYY')}
        </p>
        <Bar
          data={this.getScheduleItemsDataset()}
          options={{
          		barValueSpacing: 5,
          		scales: {
			      yAxes: [{
			        ticks: {
			          min: 0,
			        },
			      }],
			    },
        	title: {
          		display: this.props.displayTitle,
          		text: 'Semester view in weeks ',
          		fontSize: 20,
        	},
        	legend: {
          		display: true,
          		position: 'right',
        	},
      	}}
          height={100}
        />
      </Paper>
    );
  }
}
