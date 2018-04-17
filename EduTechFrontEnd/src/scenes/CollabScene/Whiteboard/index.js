import React, { Component } from 'react';
import { SketchPad, TOOL_PENCIL,
	TOOL_LINE, TOOL_RECTANGLE,
	TOOL_ELLIPSE } from 'react-sketchpad/lib';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import {RaisedButton} from 'material-ui';
import {Row, Col} from 'react-bootstrap';

import { socket } from '../../../services/socketApi';
import GroupStore from '../../../stores/GroupStore/GroupStore';


const sketchPadStyle = {
  border: '10px solid #000',
}

export default class Whiteboard extends Component {
  constructor(props) {
    super(props);

    this.state = {
      tool:TOOL_PENCIL,
      size: 2,
      color: '#000000',
      fill: false,
      fillColor: '#444444',
      items: []
    }
  }

  componentDidMount() {
    socket.on('addItem', item => this.setState({ items: this.state.items.concat([item]) }));
  }

	printDocument() {
		const input = document.getElementById('divToPrint');
		html2canvas(input)
			.then((canvas) => {
				const imgData = canvas.toDataURL('image/jpeg');
				var a = document.createElement('a');
				// toDataURL defaults to png, so we need to request a jpeg, then convert for file download.
				a.href = imgData.replace("image/jpeg", "image/octet-stream");
				a.download = `${GroupStore.collabGroup.title}_Sketch.jpg`;
				a.click();
			});
	}
  render() {
    const { tool, size, color, fill, fillColor, items } = this.state;
    return (
      <div className="text-center">
			<Row>
				<Col md={7}>
        <div style={{ float:'left', marginRight:20, borderRight: '2px dotted #1fbcd3'}} id="divToPrint">
					<SketchPad
						style={sketchPadStyle}
            width={500}
            height={700}
            animate={true}
            size={size}
            color={color}
            fillColor={fill ? fillColor : ''}
            items={items}
            tool={tool}
            onCompleteItem={i => socket.emit('addItem', i, GroupStore.collabGroup.id)}
          />
        </div>
				</Col>
				<Col md={5}>
				<Row>
        <div style={{float:'left', padding: '20px'}}>
          <div className="tools" style={{marginBottom:20}}>
            <button
              style={tool == TOOL_PENCIL ? {fontWeight:'bold'} : undefined}
              className={tool == TOOL_PENCIL  ? 'item-active' : 'item'}
              onClick={() => this.setState({tool:TOOL_PENCIL})}
            >Pencil</button>
            <button
              style={tool == TOOL_LINE ? {fontWeight:'bold'} : undefined}
              className={tool == TOOL_LINE  ? 'item-active' : 'item'}
              onClick={() => this.setState({tool:TOOL_LINE})}
            >Line</button>
            <button
              style={tool == TOOL_ELLIPSE ? {fontWeight:'bold'} : undefined}
              className={tool == TOOL_ELLIPSE  ? 'item-active' : 'item'}
              onClick={() => this.setState({tool:TOOL_ELLIPSE})}
            >Ellipse</button>
            <button
              style={tool == TOOL_RECTANGLE ? {fontWeight:'bold'} : undefined}
              className={tool == TOOL_RECTANGLE  ? 'item-active' : 'item'}
              onClick={() => this.setState({tool:TOOL_RECTANGLE})}
            >Rectangle</button>
          </div>
          <div className="options" style={{marginBottom:20}}>
            <label htmlFor="">size: </label>
            <input min="1" max="20" type="range" value={size} onChange={(e) => this.setState({size: parseInt(e.target.value)})} />
          </div>
          <div className="options" style={{marginBottom:20}}>
            <label htmlFor="">color: </label>
            <input type="color" value={color} onChange={(e) => this.setState({color: e.target.value})} />
          </div>
          {(this.state.tool == TOOL_ELLIPSE || this.state.tool == TOOL_RECTANGLE) ?
            <div>
              <label htmlFor="">fill in:</label>
              <input type="checkbox" value={fill} style={{margin:'0 8'}}
                     onChange={(e) => this.setState({fill: e.target.checked})} />
              {fill ? <span>
                  <label htmlFor="">with color:</label>
                  <input type="color" value={fillColor} onChange={(e) => this.setState({fillColor: e.target.value})} />
                </span> : ''}
            </div> : ''}
        </div>
				</Row>
				<Row>
					<div style={{padding: '20px'}}>
						<br />
						<RaisedButton style={{marginTop: '15px', width: '80%'}} secondary label="Download" onClick={() => this.printDocument()} />
					</div>
				</Row>
				</Col>
				</Row>
      </div>
    );
  }
}
