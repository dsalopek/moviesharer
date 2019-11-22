import React, { Component } from 'react';
import { login } from '../../util/APIUtils';
import './Login.css';
import { Link } from 'react-router-dom';
import { ACCESS_TOKEN } from '../../constants';

import { Form, Input, Button, Icon, notification } from 'antd';
const FormItem = Form.Item;

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usernameOrEmail: '',
            password: ''
        }
    }

    handleSubmit = event => {
        event.preventDefault();
        const loginRequest = Object.assign({}, this.state);
        login(loginRequest).then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.accessToken);
            this.props.onLogin();
        });
    }

    handleInputChange = event => {
        const target = event.target;
        const name = target.name;
        const value = target.value;
        console.log(value);
        this.setState({
            [name]: value
        });
    }

    render() {
        return (
            <div className="login-container">
                <form onSubmit={this.handleSubmit}>
                    <input className="login-form-text" 
                        type="text" name={"usernameOrEmail"} 
                        value={this.state.usernameOrEmail} 
                        onChange={this.handleInputChange}
                        placeholder="Username">
                    </input>
                    <input 
                        className="login-form-text" 
                        type="text" 
                        name={"password"} 
                        onChange={this.handleInputChange}
                        placeholder="Password">
                    </input>
                    <input 
                        className="login-form-button" 
                        type="submit" 
                        value="Submit">
                    </input>
                </form>
            </div>
        );
    }
}


export default Login;