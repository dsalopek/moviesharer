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
import LoadingIndicator from '../common/LoadingIndicator';

import Login from '../user/login/Login';
import {NewPost} from '../post/NewPost';
import MovieSearchMaster from '../post/MovieSearchMaster';
import Feed from '../post/Feed';
import ServerError from '../common/ServerError'
import Profile from '../user/profile/Profile';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: null,
      isAuthenticated: false,
      isLoading: false
    }
    // this.handleLogin = this.handleLogin.bind(this);
    // this.loadCurrentUser = this.loadCurrentUser.bind(this);
  }

  loadCurrentUser = () => {
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

  handleLogin = () => {
    this.loadCurrentUser();
    this.props.history.push("/feed");
  }

  render() {
    if (this.state.isLoading) {
      return <LoadingIndicator />
    }
    return (
      <div className="page-container">
        <div className="page-header">
          <Navbar currentUser={this.state.currentUser}/>
        </div>
        <div className="page-content">
          <Switch>
            <Route exact path="/feed">
              <Feed/>
            </Route>
            <Route exact path="/newpost">
              <NewPost/>
            </Route>
            <Route exact path="/login">
              <Login onLogin={this.handleLogin}/>
            </Route>
            <Route exact path="/profile" isAuthenticated={this.state.isAuthenticated}>
              <Profile/>
            </Route>
          </Switch>
        </div>
      </div>
    );
  }
}

export default withRouter(App);