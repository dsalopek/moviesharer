import React, { Component } from 'react';
// import './Navbar.css';
import { BrowserRouter as Router, Link, withRouter, Switch } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';


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
            <li key='feed' ><Link to="/feed">Feed</Link></li>,
            <li key='newpost'><Link to="/newpost">New Post</Link></li>
        ];

        if (this.props.currentUser) {
            navBarItems.push(<li className='menu-right' key='profile'><Link to="/profile">{this.props.currentUser.email}</Link></li>);
        } else {
            navBarItems.push(<li className='menu-right' key='login'><Link to="/login">Login</Link></li>);
        }

        return (
            <Menu pointing secondary>
                <Menu.Item
                    name='home'
                    active={activeItem === 'home'}
                    as={Link}
                    to="/feed"
                    onClick={this.handleItemClick}
                />
                <Menu.Item
                    name='messages'
                    active={activeItem === 'messages'}
                    as={Link}
                    to="/newpost"
                    onClick={this.handleItemClick}
                />
                <Menu.Menu position='right'>
                    <Menu.Item
                        name='logout'
                        active={activeItem === 'logout'}
                        as={Link}
                        to="/login"
                        onClick={this.handleItemClick}
                    />
                </Menu.Menu>
            </Menu>
        )
    }
}

export default withRouter(Navbar);