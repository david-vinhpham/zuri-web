import React from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import BocTabs from 'components/BocTabs';
import Header from 'components/Header';

import s from './Activity.css';

class Activity extends React.Component {
  render() {
    return (
      <div className={s.container}>
        <Header title="Hoạt động của quán" />
        <BocTabs activeIndex={2} />
      </div>
    );
  }
}

export default withStyles(s)(Activity);
