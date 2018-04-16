import React, { Component } from 'react';
import { PanelGroup } from 'react-bootstrap';
import { observer } from 'mobx-react';
import { toJS } from 'mobx';

import SingleLesson from './SingleLesson';
import LessonStore from '../../../stores/LessonStore/LessonStore';

@observer
class Lesson extends Component {
	render() {
		const lessonList = toJS(LessonStore.lessonList);
		const uploadedFile = toJS(LessonStore.uploadedFile);
		return (
			<div className="standardTopGap">
				<PanelGroup accordion id="accordion-example">
				  {
				  	lessonList.map((lesson) => {
				  		LessonStore.fetchFilesForLesson(lesson.id);
				  		return <SingleLesson key={lesson.id} lesson={lesson} uploadedFile={uploadedFile} />
				  	})
				  }
				</PanelGroup>
			</div>
		)
	}
}

export default Lesson;
