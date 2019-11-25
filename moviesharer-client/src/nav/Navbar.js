import React, {Component} from 'react';
import './Navbar.css';
import {BrowserRouter as Router, Link, withRouter, Switch} from 'react-router-dom';


class Navbar extends Component {
    constructor(props) {
        super(props);
    }

    render() {

        let navBarItems;

        navBarItems = [
            <li key='feed' ><Link to="/feed">Feed</Link></li>,
            <li key='newpost'><Link to="/newpost">New Post</Link></li>
        ];

        if(this.props.currentUser) {
            navBarItems.push(<li className='menu-right' key='profile'><Link to="/profile">{this.props.currentUser.email}</Link></li>);
        } else {
            navBarItems.push(<li className='menu-right' key='login'><Link to="/login">Login</Link></li>);
        }

        return (
            <ul>
                {navBarItems}
            </ul>
        )
    }
}

export default withRouter(Navbar);