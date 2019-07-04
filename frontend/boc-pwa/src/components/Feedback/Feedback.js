/**
 * BOC VN (http://www.bocvietnam.com/)
 *
 * Copyright © 2018-present BOCVN, LLC. All rights reserved.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

import React from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './Feedback.css';

class Feedback extends React.Component {
  render() {
    return (
      <div className={s.root}>
        <div className={s.container}>
          <span className={s.link}>Ask a question </span>{' '}
          <span className={s.spacer}> | </span>{' '}
          <a className={s.link} href=" /issues/new">
            Report an issue{' '}
          </a>{' '}
        </div>{' '}
      </div>
    );
  }
}

export default withStyles(s)(Feedback);
