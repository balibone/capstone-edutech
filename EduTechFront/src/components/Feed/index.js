import React, { Component } from 'react';
import { FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';

import SinglePost from './SinglePost';
import FeedStore from '../../stores/FeedStore/FeedStore';
// create a viewModel singleton
const feedStore = new FeedStore();

const poster = {
  id: 1,
  name: 'Hafidz',
  img: 'avatar.png',
};

@observer
export default class Feed extends Component {
  componentWillMount() {
    feedStore.currentPageId = this.props.pageId;
  }
  componentWillReceiveProps(newProps) {
    feedStore.currentPageId = newProps.pageId;
  }

  createPost(e, pageId, replyTo) {
    if (e.which === 13) {
      feedStore.createPost(pageId, poster, e.target.value, replyTo);
      e.target.value = '';
    }
  }

  renderPinnedPost(pageId) {
    const { pinnedPost } = feedStore;
    if (pinnedPost) {
      return (
        <SinglePost
          post={pinnedPost}
          createPost={e => this.createPost(e, pageId, pinnedPost.id)}
          feedStore={feedStore}
          pageId={pageId}
        />
      );
    }
    return null;
  }

  renderPosts(pageId) {
    return feedStore.mainPosts.map(post => (
      <SinglePost
        post={post}
        createPost={e => this.createPost(e, pageId, post.id)}
        feedStore={feedStore}
        pageId={pageId}
      />
    ));
  }

  render() {
    const { pageId } = this.props; // pageId in String

    return (
      <div className="standardTopGap">
        <FormControl
          onKeyPress={e => this.createPost(e, pageId, null)}
          type="text"
          placeholder="Post in feed"
        />
        {this.renderPinnedPost(pageId)}
        {this.renderPosts(pageId)}
      </div>
    );
  }
}
