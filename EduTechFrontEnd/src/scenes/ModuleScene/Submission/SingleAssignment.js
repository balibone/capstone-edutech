import React, {Component} from 'react';
import {Table, thead, tbody} from 'react-bootstrap';

class SingleAssignment extends Component {

	render(){

		return(
			<div className="standardTopGap">
				<Table striped bordered condensed hover>
				  <thead>
				    <tr>
				      <th>#</th>
				      <th>Name</th>
				      <th>Student ID</th>
				      <th>Submissions</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td>1</td>
				      <td>Nan Da</td>
				      <td>A8364836X</td>
				      <td>
				      	Assignment 1
				      	<a href="https://www.w3schools.com" className="pull-right">View</a>
				      </td>
				    </tr>
				    <tr>
				      <td>2</td>
				      <td>Tengku Hafidz</td>
				      <td>A8374838X</td>
				      <td>
				      	<p>Assignment 1 <a href="https://www.w3schools.com" className="pull-right">View</a></p> 
				      	<p>Assignment 2 <a href="https://www.w3schools.com" className="pull-right">View</a></p>     	
				      </td>
				    </tr>
				    <tr>
				      <td>2</td>
				      <td>Jacob</td>
				      <td>A6846273X</td>
				      <td>None</td>
				    </tr>
				  </tbody>
				</Table>
			</div>
		)
	}
}

export default SingleAssignment;