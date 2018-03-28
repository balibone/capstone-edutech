import React, {Component} from 'react';
import ReactDataGrid  from 'react-data-grid';
import {Button} from 'react-bootstrap';
import moment from 'moment';

import DownloadBtn from './DownloadBtn';

class SingleAssignmentTable extends React.Component {
  constructor(props) {
    super(props);

    let originalRows = this.createRows(10);
    let rows = originalRows.slice(0);
    this.state = { 
      _columns: [],
      originalRows, 
      rows,
      
    };
  }

  componentDidMount(){
    this.setState({
      _columns: [
      {
        key: 'id',
        name: '#',
        width: 40,
        sortable: false
      },
      {
        key: 'submittedBy',
        name: 'Submitted By',
        width: 150,
        sortable: false
      },
      {
        key: 'timestamp',
        name: 'Submitted Time',
        width: 120,
        sortable: true
      },
      {
        key: 'fileName',
        name: 'File Name',
        width: 200
      },
      {
        key: 'download',
        name: 'Download',
        width: 100,
        formatter : DownloadBtn,
        getRowMetaData: (row)=> row
      }
    ]
    })
  }

  handleRowData(){
    console.log("row datas")
  }

  createRows(rowNum){
    let rows = [];
    var index = 0;
    for (let i = 0; i < rowNum; i++) {
      index = i+1;
      rows.push({
        id: index,
        submittedBy: 'Nan Da ' + index,
        timestamp: moment(new Date()).format('D MMM, HH:mm'),
        fileName: 'assignment_1.pdf'
      });
    }

    return rows;
  };

  handleGridSort(sortColumn, sortDirection){
    const comparer = (a, b) => {
      if (sortDirection === 'ASC') {
        return (a[sortColumn] > b[sortColumn]) ? 1 : -1;
      } else if (sortDirection === 'DESC') {
        return (a[sortColumn] < b[sortColumn]) ? 1 : -1;
      }
    };

    const rows = sortDirection === 'NONE' ? this.state.originalRows.slice(0) : this.state.rows.sort(comparer);

    this.setState({ rows });
  };

  rowGetter(i){
    return this.state.rows[i];
  };

  render() {
    return  (
      <ReactDataGrid
        onGridSort={this.handleGridSort.bind(this)}
        columns={this.state._columns}
        rowGetter={this.rowGetter.bind(this)}
        rowsCount={this.state.rows.length}
        minHeight={500} />);
  }
}

export default SingleAssignmentTable;

