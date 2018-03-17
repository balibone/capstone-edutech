import React, {Component} from 'react';
import { PanelGroup} from 'react-bootstrap';

import SingleLesson from './SingleLesson';

class Lesson extends Component {

	render(){
		let lessonList = [
			{
				id: 1,
				title: "Lesson 1",
				dateTime: new Date()
			},
			{
				id: 2,
				title: "Lesson 2",
				dateTime: new Date()
			},
			{
				id: 3,
				title: "Lesson 3",
				dateTime: new Date()
			}
		]

		return(
			<div className="standardTopGap">
				<PanelGroup accordion id="accordion-example">
				  {
				  	lessonList.map((lesson) => {
				  		return <SingleLesson key={lesson.id} lesson={lesson} />
				  	})
				  }
				</PanelGroup>
			</div>
		)
	}
}

export default Lesson;