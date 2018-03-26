import React, {Component} from 'react';
import {observer} from 'mobx-react';
import {Button} from 'react-bootstrap';

import LessonStore from '../../../../stores/LessonStore/LessonStore';

@observer
class DownloadAllFileBtn extends Component {

	downloadAllFiles(){
		const lessonId = this.props.lessonId;
		LessonStore.downloadAllFiles(lessonId);
	}

	render(){
		return(
			<div>
				<Button className="pull-right" block onClick={this.downloadAllFiles.bind(this)}>Download All Attachments</Button>
			</div>
		)
	}
}

export default DownloadAllFileBtn;