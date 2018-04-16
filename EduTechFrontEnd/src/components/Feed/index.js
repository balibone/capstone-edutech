import React, { Component } from 'react';
import { FormControl, Button } from 'react-bootstrap';
import { observer } from 'mobx-react';

import SinglePost from './SinglePost';
import FeedStore from '../../stores/FeedStore/FeedStore';
import './styles.css';

@observer
export default class Feed extends Component {
  componentWillMount() {
    FeedStore.currentPageId = this.props.pageId;
    FeedStore.fetchPagePosts();
  }

  componentWillReceiveProps(newProps) {
    FeedStore.currentPageId = newProps.pageId;
    FeedStore.fetchPagePosts();
  }

  addPost(e, pageId) {
    if (e.target.value && e.which === 13) {
      FeedStore.addPost(pageId, e.target.value);
      e.target.value = '';
    }
  }

  replyPost(e, parentPostId) {
    if (e.target.value && e.which === 13) {
      FeedStore.addReply(e.target.value, parentPostId);
      e.target.value = '';
    }
  }

  renderPinnedPost(pageId) {
    const { pinnedPost } = FeedStore;
    if (pinnedPost) {
      return (
        <SinglePost
          post={pinnedPost}
          replyPost={e => this.replyPost(e, pinnedPost.id)}
          feedStore={FeedStore}
          pageId={pageId}
          key={pinnedPost.id}
          scene={this.props.scene}
        />
      );
    }
    return null;
  }

  renderPosts(pageId) {
    // console.log('feedStore.mainPosts', feedStore.mainPosts)
    return FeedStore.mainPosts.map(post => (
      <SinglePost
        post={post}
        replyPost={e => this.replyPost(e, post.id)}
        feedStore={FeedStore}
        pageId={pageId}
        key={post.id}
        scene={this.props.scene}
      />
    ));
  }

  renderAllPosts(pageId) {
    if (FeedStore.posts.length > 0) {
      return (
        <div>
          {this.renderPinnedPost(pageId)}
          {this.renderPosts(pageId)}
        </div>
      );
    }
    return (
      <div className="feedEmptyState">
        <i className="fas fa-comments fa-10x" />
        <p className="lead standardTopGap"> It is a lil quiet here...</p>
        <p className="lead"> Break the silence and start a conversation. </p>
      </div>
    );
  }

  render() {
    const { pageId } = this.props; // pageId in String
    return (
      <div>
        <FormControl
          onKeyPress={e => this.addPost(e, pageId, null)}
          type="text"
          placeholder="Start a conversation"
        />
        <div className="text-right">
          <Button bsStyle="link" onClick={() => FeedStore.fetchPagePosts()} >Refresh</Button>
        </div>
        {this.renderAllPosts(pageId)}

      </div>
    );
  }
}
