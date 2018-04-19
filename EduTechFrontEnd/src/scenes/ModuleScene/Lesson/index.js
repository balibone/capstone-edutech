import React, { Component } from 'react';
import { PanelGroup } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';

import SingleLesson from './SingleLesson';
import LessonStore from '../../../stores/LessonStore/LessonStore';

@observer
class Lesson extends Component {
	renderLessons(lessonList, uploadedFile) {
		if(!lessonList || lessonList.length < 1) {
			return (
				<div className="feedEmptyState">
					<i className="fas fa-book fa-10x" />
					<p className="lead standardTopGap"> No Lessons yet</p>
					<p className="lead"> Do not worry, it will be added soon. </p>
				</div>
			);
		}

			return (
				<PanelGroup accordion id="accordion-example">
				{
					lessonList.map((lesson) => {
						LessonStore.fetchFilesForLesson(lesson.id);
						// console.log('lesson lesson: ', lesson)
						return <SingleLesson key={lesson.id} lesson={lesson} uploadedFile={uploadedFile} />
					})
				}
			</PanelGroup>
		)
	}

	render() {
		const lessonList = toJS(LessonStore.lessonList);
		const uploadedFile = toJS(LessonStore.uploadedFile);
		console.log('first uploadedFile: ', uploadedFile)
		return (
			<div className="standardTopGap">
				{this.renderLessons(lessonList, uploadedFile)}
			</div>
		)
	}
}

export default Lesson;
