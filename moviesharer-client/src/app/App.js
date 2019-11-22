import React, { Component } from 'react';
import './App.css';
import {
  Route,
  withRouter,
  Switch,
  Link
} from 'react-router-dom';

import Navbar from '../nav/Navbar'

import { getCurrentUser } from '../util/APIUtils';
import { ACCESS_TOKEN } from '../constants';
import PostList from '../post/PostList';
import MovieList from '../post/MovieList'
import LoadingIndicator from '../common/LoadingIndicator';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      isAuthenticated: false,
      isLoading: false
    }
    // this.handleLogout = this.handleLogout.bind(this);
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
  }

  loadCurrentUser() {
    this.setState({
      isLoading: true
    });
    getCurrentUser()
      .then(response => {
        this.setState({
          currentUser: response,
          isAuthenticated: true,
          isLoading: false
        });
      }).catch(error => {
        this.setState({
          isLoading: false
        });
      });
  }

  componentDidMount() {
    this.loadCurrentUser();
  }

  handleLogin() {
    this.loadCurrentUser();
    this.props.history.push("/");
  }

  render() {
    if (this.state.isLoading) {
      return <LoadingIndicator />
    }
    return (
      <div>
        <Navbar/>
        <MovieList/>
      </div>
    );
  }
}

export default withRouter(App);