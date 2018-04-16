import React, { Component } from 'react';
import { Bar } from 'react-chartjs-2';
import { observer } from 'mobx-react';
import moment from 'moment';

import ScheduleItemStore from '../../../stores/ScheduleItemStore/ScheduleItemStore';
import GroupScheduleItemStore from '../../../stores/ScheduleItemStore/GroupScheduleItemStore';
import GroupStore from '../../../stores/GroupStore/GroupStore';
import { MEETING_ITEM_COLOR, TASK_ITEM_COLOR } from '../../../utils/constants';
import './styles.css';

@observer
export default class GroupScheduleItemsChart extends Component {
  getScheduleItemsCount() {
    const numberOfWeeks = ScheduleItemStore.semesterNumberOfWeeks;
    let startDate = moment(ScheduleItemStore.semester.startDate).format('YYYY-MM-DD');
    let endDate = moment(ScheduleItemStore.semester.startDate).add(7, 'days').format('YYYY-MM-DD');
    const meetingItemsCounts = [];
    const taskItemsCounts = [];

    for (let i = 0; i < numberOfWeeks; i += 1) {
      meetingItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, GroupScheduleItemStore.meetingItems));
      taskItemsCounts.push(this
        .getScheduleItemsCountWithinDates(startDate, endDate, GroupScheduleItemStore.taskItems));
      startDate = endDate;
      endDate = moment(endDate).add(7, 'days').format('YYYY-MM-DD');
    }
    return {
      meetingItemsCounts,
      taskItemsCounts,
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
      meetingItemsCounts, taskItemsCounts,
    } = this.getScheduleItemsCount();
    const weekLabels = this.getWeekLabels();
    const data = {
	        labels: weekLabels,
	        datasets: [
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
	        ],
	      };
      return data;
    }

  render() {
    return (
      <div className="weeklyChart">
        <h3>{GroupStore.selectedGroup.title} Weekly Chart</h3>
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
      </div>
    );
  }
}
