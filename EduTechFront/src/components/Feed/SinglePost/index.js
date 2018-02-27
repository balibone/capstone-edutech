import React, { Component } from 'react';
import { Media, FormControl } from 'react-bootstrap';
import { Paper, Popover, Menu, MenuItem } from 'material-ui';
import { observer } from 'mobx-react';

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

  toggleVoteIdea(feedStore, post) {
    const hasLiked = feedStore.toggleLikePost(post, liker);
    this.setState({ hasLiked });
  }

  renderPopoverMenuItems(feedStore, post) {
    if (post.isPinned) {
      return <MenuItem primaryText="Delete post" onClick={() => feedStore.deletePost(post)} />;
    }
    return (
      <div>
        <MenuItem primaryText="Pin post" onClick={() => feedStore.pinPost(post)} />
        <MenuItem primaryText="Delete post" onClick={() => feedStore.deletePost(post)} />
      </div>
    );
  }

  renderPostMenu(feedStore, post) {
    // TODO: if not post owner, don't show anything

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

  renderReplies(feedStore, groupId) {
    const replies = feedStore.getReplies(this.props.post.id);
    return replies.map(post => (
      <SinglePostReply
        poster={post.poster}
        postId={post.id}
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
      post, createPost, feedStore, groupId,
    } = this.props;
    const { poster } = post;
    return (
      <Paper className="paperDefault standardTopGap feedPaper">
        <Media>
          <Media.Left>
            <img width={64} height={64} src={`/img/${poster.img}`} alt="thumbnail" className="img-circle" />
          </Media.Left>
          <Media.Body>
            <Media.Heading>
              {poster.name}
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

            <p>{post.message} {this.renderLike(feedStore, post)}</p>
            <FormControl
              onKeyPress={createPost}
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
