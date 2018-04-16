import React, { Component } from 'react';
import { Media, FormControl } from 'react-bootstrap';
import { Paper, Popover, Menu, MenuItem } from 'material-ui';
import { observer } from 'mobx-react';
import moment from 'moment';
import swal from 'sweetalert';

import { USER_IMAGE_PATH } from '../../../utils/constants';
import SinglePostReply from './SinglePostReply';
import './styles.css';

@observer
export default class SinglePost extends Component {
  state = {
    open: false,
  }

  handlePopoverClick(event) {
    // This prevents ghost click.
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

  handlePinPost(feedStore, post) {
    this.setState({
      open: false,
    });
    feedStore.pinPost(post);
  }

  renderPopoverMenuItems(feedStore, post, scene) {
    if (post.isPinned || (scene === 'module' && localStorage.getItem('userType') === 'student')) {
      return <MenuItem primaryText="Delete post" onClick={() => this.handleDeletePost(feedStore, post)} />;
    }
    return (
      <div>
        <MenuItem primaryText="Pin post" onClick={() => this.handlePinPost(feedStore, post)} />
        <MenuItem primaryText="Delete post" onClick={() => this.handleDeletePost(feedStore, post)} />
      </div>
    );
  }

  renderPostMenu(feedStore, post) {
    if (post.createdBy.username === localStorage.getItem('username')) {
      if (post.isPinned) {
        return (
          <div className="pull-right">
            <i className="fas fa-thumbtack postMenu" onClick={() => feedStore.unpinPost(this.props.scene)} /> &nbsp;
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
          <i className="fas fa-thumbtack postMenu" onClick={() => feedStore.unpinPost(post)} />
        </div>
      );
    }
    return <span />;
  }

  renderReplies(feedStore, groupId) {
    // const replies = feedStore.posts(this.props.post.id);
    const { replies } = this.props.post;

    return replies.map(post => (
      <SinglePostReply
        poster={post.poster}
        postId={post.id}
        feedStore={feedStore}
        post={post}
        postMessage={post.message}
        groupId={groupId}
        key={post.id}
      />
    ));
  }

  render() {
    const {
      post, replyPost, feedStore, groupId, scene,
    } = this.props;
    return (
      <Paper className="singlePost animated fadeInDown">
        <Media>
          <Media.Left>
            <img width={64} height={64} src={USER_IMAGE_PATH + post.createdBy.imgFileName} alt="thumbnail" className="img-circle" />
          </Media.Left>
          <Media.Body>
            <Media.Heading>
              <span className="capitalize">{post.createdBy.username}</span>
              <span className="postedAt">posted {moment(post.createdAt).fromNow()}</span>
              <div>
              {this.renderPostMenu(feedStore, post, scene)}
                <Popover
                  open={this.state.open}
                  anchorEl={this.state.anchorEl}
                  anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
                  targetOrigin={{ horizontal: 'right', vertical: 'top' }}
                  onRequestClose={this.handlePopoverRequestClose}
                >
                  <Menu>
                    {this.renderPopoverMenuItems(feedStore, post, scene)}
                  </Menu>
                </Popover>
              </div>
            </Media.Heading>

            <p>{post.message} </p>
            <FormControl
              onKeyPress={replyPost}
              type="text"
              placeholder="Reply to post"
            />

            {this.renderReplies(feedStore, groupId)}
          </Media.Body>

        </Media>
      </Paper>
    );
  }
}
