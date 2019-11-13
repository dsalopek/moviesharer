import React, { Component } from 'react';
import 'antd/dist/antd.css';
// import './index.css';
import './NewPost.css';
import { queryMovies } from '../util/APIUtils';
import { Select, Icon } from 'antd';
// import jsonp from 'fetch-jsonp';
// import querystring from 'querystring';

const { Option } = Select;

let timeout;
let currentValue;

function fetch(value, callback) {
  if (timeout) {
    clearTimeout(timeout);
    timeout = null;
  }
  currentValue = value;

  function fake() {
      queryMovies(currentValue)
      .then(response => {
        if (currentValue === value) {
          const { results } = response;
          const data = [];
          results.forEach(r => {
            data.push({
              value: r['id'],
              text: r['title'],
            });
          });
          callback(data);
        }
      });
  }

  timeout = setTimeout(fake, 300);
}

class NewPost extends React.Component {
  state = {
    data: [],
    value: undefined,
  };

  handleSearch = value => {
    if (value) {
      fetch(value, data => this.setState({ data }));
    } else {
      this.setState({ data: [] });
    }
  };

  handleChange = value => {
    this.setState({ value });
  };

  render() {
    const options = this.state.data.map(d => <Option key={d.value}>{d.text}</Option>);
    console.log(this.state.value);
    return (
      <Select
        showSearch
        showArrow
        value={this.state.value}
        placeholder={'Begin typing a movie title'}
        style={this.props.style}
        defaultActiveFirstOption={false}
        showArrow={false}
        filterOption={false}
        onSearch={this.handleSearch}
        onChange={this.handleChange}
        notFoundContent={null}
      >
        {options}
      </Select>
    );
  }
}          

export default NewPost;
