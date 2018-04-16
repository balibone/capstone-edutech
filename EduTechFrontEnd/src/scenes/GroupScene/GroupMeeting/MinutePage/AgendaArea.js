import React, { Component } from 'react';
import { observer } from 'mobx-react';
import { Form, FormGroup, FormControl, Button, PanelGroup } from 'react-bootstrap';

import SingleAgenda from './SingleAgenda';
import MinuteStore from '../../../../stores/MeetingStore/MinuteStore';

@observer
class AgendaArea extends Component {
	constructor(props) {
		super(props)
		this.state = {
			agendaList: this.props.agendas,
			agenda: '',
		}
	}
	componentDidUpdate(prevProps) {
		if (prevProps.agendas !== this.props.agendas) {
			this.handleUpdateAgendaProps(this.props.agendas);
		}
	}
	handleUpdateAgendaProps(agendas) {
		console.log('UPDATING PROPS', agendas)
		this.setState({ agendaList: agendas })
	}
	addAgenda(event) {
		event.preventDefault();
		const { agenda, agendaList } = this.state;
		const agendaObj = { title: agenda, discussion: '', conclusion: '' };
		agendaList.push(agendaObj);
		MinuteStore.addAgenda(this.props.minuteId, agendaObj, this.props.groupId);
		// MeetingStore.populateMeetings(this.props.groupId);
		this.setState({ agenda: '', agendaList })
	}
	handleAgendaChange(event) {
		this.setState({ agenda: event.target.value })
	}
	renderAgendaList() {
		return 	this.state.agendaList.map(agenda => (
			<SingleAgenda
				agenda={agenda}
				minuteId={this.props.minuteId}
				groupId={this.props.groupId}
			/>
		))
	}
	render() {
		// console.log("agendalist : ", this.state.agendaList)
		return (
			<div>
				<hr />
				<Form>
				  <FormGroup>
				    <FormControl type="text" placeholder="Add agenda title" value={this.state.agenda} onChange={this.handleAgendaChange.bind(this)}/>
				  </FormGroup>{' '}
				  <Button type="submit" bsStyle="primary" onClick={this.addAgenda.bind(this)}>Add Agenda</Button>
				</Form>

				<PanelGroup accordion id="accordion-example">
				  {this.renderAgendaList()}
				</PanelGroup>
			</div>
		)
	}
}

export default AgendaArea;
