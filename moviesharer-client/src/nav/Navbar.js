import React, { Component } from 'react';
import './Navbar.css';
import { BrowserRouter as Router, NavLink, Link, withRouter, Switch } from 'react-router-dom';


class Navbar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            activeItem: "home"
        }
    }

    handleItemClick = (e, { name }) => this.setState({ activeItem: name })

    render() {

        let navBarItems;
        const { activeItem } = this.state;

        navBarItems = [
            <li className="navbar-item" key='feed' ><NavLink to="/feed" activeClassName="active">Feed</NavLink></li>,
            <li className="navbar-item" key='newpost'><NavLink to="/newpost" activeClassName="active">New Post</NavLink></li>
        ];

        if (this.props.currentUser) {
            navBarItems.push(<li className='navbar-item menu-right' key='profile'><NavLink to="/profile" activeClassName="active">{this.props.currentUser.email}</NavLink></li>);
        } else {
            navBarItems.push(<li className='navbar-item menu-right' key='login'><NavLink to="/login" activeClassName="active">Login</NavLink></li>);
        }

        return (
            <ul className="navbar">
                {navBarItems}
            </ul>
        )
    }
}

export default withRouter(Navbar);