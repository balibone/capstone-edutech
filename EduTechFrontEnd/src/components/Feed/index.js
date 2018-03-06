import React, { Component } from 'react';
import { FormControl } from 'react-bootstrap';
import { observer } from 'mobx-react';
// import swal from 'sweetalert';

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
    feedStore.fetchPagePosts();
  }
  componentWillReceiveProps(newProps) {
    feedStore.currentPageId = newProps.pageId;
    feedStore.fetchPagePosts();
  }

  addPost(e, pageId) {
    if (e.target.value && e.which === 13) {
      feedStore.addPost(pageId, e.target.value);
      e.target.value = '';
    }
  }

  replyPost(e, parentPostId) {
    if (e.target.value && e.which === 13) {
      feedStore.addReply(e.target.value, parentPostId);
      e.target.value = '';
    }
  }

  renderPinnedPost(pageId) {
    const { pinnedPost } = feedStore;
    if (pinnedPost) {
      return (
        <SinglePost
          post={pinnedPost}
          addPost={e => this.addPost(e, pageId, pinnedPost.id)}
          feedStore={feedStore}
          pageId={pageId}
        />
      );
    }
    return null;
  }

  renderPosts(pageId) {
    // console.log('feedStore.mainPosts', feedStore.mainPosts)
    return feedStore.mainPosts.map(post => (
      <SinglePost
        post={post}
        replyPost={e => this.replyPost(e, post.id)}
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
          onKeyPress={e => this.addPost(e, pageId, null)}
          type="text"
          placeholder="Post in feed"
        />
        {this.renderPinnedPost(pageId)}
        {this.renderPosts(pageId)}
      </div>
    );
  }
}
