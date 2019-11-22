import React, {Component} from 'react';
import './Navbar.css';
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom';
import Login from '../user/login/Login';
import NewPost from '../post/NewPost';

class Navbar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Router>
                <ul>
                    <li><Link to="/feed">Feed</Link></li>
                    <li><Link to="/newpost">New Post</Link></li>
                    <li className='menu-right'><Link to="/login">Login</Link></li>
                </ul>
                <Switch>
                    <Route path="/feed">
                        {/* <About /> */}
                    </Route>
                    <Route path="/newpost">
                        <NewPost/>
                    </Route>
                    <Route path="/login">
                        <Login onLogin={this.props.handleLogin}/>
                    </Route>
                </Switch>
            </Router>
        )
    }
}

export default Navbar;