import React, { Component } from 'react';
import { Media, FormControl } from 'react-bootstrap';
import { Paper, Popover, Menu, MenuItem } from 'material-ui';
import { observer } from 'mobx-react';
import moment from 'moment';

import SinglePostReply from './SinglePostReply';
import './styles.css';

const liker = {
  id: 1,
  name: 'Hafidz',
  img: 'avatar.png',
};

@observer
export default class SinglePost extends Component {
  state = {
    open: false,
    hasLiked: false,
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
    this.setState({
      open: false,
    });
    feedStore.deletePost(post);
  }

  handlePinPost(feedStore, post) {
    this.setState({
      open: false,
    });
    feedStore.pinPost(post);
  }

  toggleVoteIdea(feedStore, post) {
    const hasLiked = feedStore.toggleLikePost(post, liker);
    this.setState({ hasLiked });
  }

  renderPopoverMenuItems(feedStore, post) {
    if (post.isPinned) {
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
    // TODO: if not post owner, don't show anything
    console.log('renderPostMenu post', post)
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
      />
    ));
  }

  renderLike(feedStore, post) {
    const likeIcon = this.state.hasLiked ? 'fa fa-thumbs-up' : 'far fa-thumbs-up';
    const likeCount = post.likers.length > 0 ? post.likers.length : '';
    return (
      <div className="pull-right postMenu">
        { likeCount } &nbsp;
        <i
          className={likeIcon}
          onClick={() => this.toggleVoteIdea(feedStore, post)}
        />
      </div>
    );
  }

  render() {
    const {
      post, replyPost, feedStore, groupId,
    } = this.props;
    const { poster } = post;
    return (
      <Paper className="paperDefault standardTopGap feedPaper">
        <Media>
          <Media.Left>
            <img width={64} height={64} src={`http://localhost:8080/EduTechWebApp-war/uploads/commoninfrastructure/admin/images/${post.createdBy.imgFileName}`} alt="thumbnail" className="img-circle" />
          </Media.Left>
          <Media.Body>
            <Media.Heading>
              <span className="capitalize">{post.createdBy.username}</span>
              <span className="postedAt">posted {moment(post.createdAt).fromNow()}</span>
              <div>
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
