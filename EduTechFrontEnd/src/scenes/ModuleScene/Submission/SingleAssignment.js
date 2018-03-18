import React, {Component} from 'react';
import {Table, thead, tbody} from 'react-bootstrap';
import moment from 'moment';

class SingleAssignment extends Component {

	render(){

		return(
			<div className="standardTopGap">
				<Table striped bordered condensed hover>
				  <thead>
				    <tr>
				      <th>#</th>
				      <th>Submited By</th>
				      <th>Timestamp</th>
				      <th>Submissions</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>1</td>
				      <td>Nan Da</td>
				      <td>{moment(new Date()).format('MMMM Do, h:mm:ss a')}</td>
				      <td>
				      	nanda_firstSubmission.docx
				      	<a href="https://www.w3schools.com" className="pull-right">Download</a>
				      </td>
				    </tr>
				    <tr>
				      <td>2</td>
				      <td>Tengku Hafidz</td>
				      <td>{moment(new Date()).format('MMMM Do, h:mm:ss a')}</td>
				      <td>
				      	<p>Hafidz_asgn1.pdf <a href="https://www.w3schools.com" className="pull-right">Download</a></p> 
				      	<p>hafidz_updated.pdf <a href="https://www.w3schools.com" className="pull-right">Download</a></p>     	
				      </td>
				    </tr>
				    <tr>
				      <td>2</td>
				      <td>Jacob</td>
				      <td>{moment(new Date()).format('MMMM Do, h:mm:ss a')}</td>
				      <td>None</td>
				    </tr>
				  </tbody>
				</Table>
			</div>
		)
	}
}

export default SingleAssignment;