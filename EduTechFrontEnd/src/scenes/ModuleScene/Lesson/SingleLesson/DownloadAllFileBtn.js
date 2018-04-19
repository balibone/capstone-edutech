import React, {Component} from 'react';
import {observer} from 'mobx-react';
import {Button} from 'react-bootstrap';
import { RaisedButton } from 'material-ui';
import moment from 'moment';

import LessonStore from '../../../../stores/LessonStore/LessonStore';

@observer
class DownloadAllFileBtn extends Component {

	downloadAllFiles() {
		const {title, startDate} = this.props.lesson;
		const dateTimeFormat = moment(startDate).format('Do MMMM');
		const lessonId = this.props.lessonId;
		LessonStore.downloadAllFiles(lessonId, title, dateTimeFormat);
	}

	render() {
		return (
			<div>
				<RaisedButton
					label="Download All Attachments"
					fullWidth
					primary
					onClick={() => this.downloadAllFiles()}
				/>
			</div>
		)
	}
}

export default DownloadAllFileBtn;
