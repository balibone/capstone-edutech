import React from 'react';
import { Media } from 'react-bootstrap';

const SinglePost = ({ poster, postMessage }) => (
  <Media>
    <hr />
    <Media.Left>
      <img width={64} height={64} src={`/img/${poster.img}`} alt="thumbnail" className="img-circle" />
    </Media.Left>
    <Media.Body>
      <Media.Heading>{poster.name}</Media.Heading>
      <p>{postMessage}</p>
    </Media.Body>
  </Media>
);

export default SinglePost;
