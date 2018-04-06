import React, {Component} from 'react';
import { PanelGroup} from 'react-bootstrap';
import {observer} from 'mobx-react';
import {toJS} from 'mobx';

import SingleLesson from './SingleLesson';
import LessonStore from '../../../stores/LessonStore/LessonStore';

@observer
class Lesson extends Component {



	render(){
		var lessonList = toJS(LessonStore.lessonList);
		var uploadedFile = toJS(LessonStore.uploadedFile); 
		// if(lessonList){
		// 	console.log("Lesson from store", lessonList)
		// }
		// console.log("first lessonList: ", lessonList[0])

		return(
			<div className="standardTopGap">
				<PanelGroup accordion id="accordion-example">
				  {
				  	lessonList.map((lesson) => {
				  		LessonStore.fetchFilesForLesson(lesson.id);
				  		return <SingleLesson key={lesson.id} lesson={lesson} uploadedFile={uploadedFile}/>
				  	})
				  }
				</PanelGroup>
			</div>
		)
	}
}

export default Lesson;