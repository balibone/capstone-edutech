import React, { Component } from 'react';
import { Media } from 'react-bootstrap';
import { Popover, Menu, MenuItem } from 'material-ui';
import { observer } from 'mobx-react';
import moment from 'moment';
import swal from 'sweetalert';

import { USER_IMAGE_PATH } from '../../../../utils/constants';

@observer
export default class SinglePostReply extends Component {
  state = {
    open: false,
  }

  handlePopoverClick(event) {
    event.preventDefault();

    this.setState({
      open: true,
      anchorEl: event.currentTarget,
    });
  }

  handlePopoverRequestClose = () => {
    this.setState({
      open: false,
    });
  };

  handleDeletePost(feedStore, post) {
    this.setState({ open: false });
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this post if you delete it!',
      icon: 'warning',
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        feedStore.deletePost(post);
      }
    });
  }


  renderPopoverMenuItems(feedStore, post) {
    return <MenuItem primaryText="Delete post" onClick={() => this.handleDeletePost(feedStore, post)} />;
  }

  renderPostMenu(feedStore, post) {
    if (post.createdBy.username === localStorage.getItem('username')) {
      if (post.isPinned) {
        return (
          <div className="pull-right">
            <i className="fas fa-thumbtack postMenu" onClick={() => feedStore.unpinPost(post)} /> &nbsp;
            <i className="fas fa-ellipsis-h postMenu" onClick={e => this.handlePopoverClick(e)} />
          </div>
        );
      }

      return (
        <div className="pull-right postMenu">
          <i className="fas fa-ellipsis-h" onClick={e => this.handlePopoverClick(e)} />
        </div>
      );
    }

    if (post.isPinned) {
        return (
          <div className="pull-right">
            <i className="fas fa-thumbtack postMenu" onClick={() => feedStore.unpinPost(post)} /> &nbsp;
          </div>
        );
      }
    return <span />;
  }


  render() {
    const { postMessage, feedStore, post } = this.props;
    return (
      <Media className="animated fadeInDown">
        <hr />
        <Media.Left>
          <img width={64} height={64} src={USER_IMAGE_PATH + post.createdBy.imgFileName} alt="thumbnail" className="img-circle" />
        </Media.Left>
        <Media.Body>
          <Media.Heading>
            <span className="capitalize">{post.createdBy.username}</span>
            <span className="postedAt">posted {moment(post.createdAt).fromNow()}</span>
            {this.renderPostMenu(feedStore, post)}
            <Popover
              open={this.state.open}
              anchorEl={this.state.anchorEl}
              anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
              targetOrigin={{ horizontal: 'right', vertical: 'top' }}
              onRequestClose={this.handlePopoverRequestClose}
            >
              <Menu>
                {this.renderPopoverMenuItems(feedStore, post)}
              </Menu>
            </Popover>
          </Media.Heading>
          <p>{postMessage}</p>
        </Media.Body>
      </Media>
    );
  }
}
