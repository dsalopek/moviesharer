import React, { Component } from 'react';
import { Form, Input, Button } from 'antd';
import MoviePicker from './MoviePicker';

class NewPost extends Component {
    render() {
        const LoginForm = Form.create()(NewPostForm)
        return (
            <div className="login-container">
                {/* <h1 className="page-title">Login</h1> */}
                <div className="login-content">
                    <LoginForm />
                </div>
            </div>
        )
    }
}

class NewPostForm extends Component {
    // constructor(props) {
    //     super(props);
    // }
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };


    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Form onSubmit={this.handleSubmit}>
                <Form.Item label="Movie">
                <MoviePicker />
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('password', {})(
                        <Input
                            size="large"
                            name="password"
                            type="password"
                            placeholder=""
                        ></Input>
                    )}
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Log in
          </Button>
                </Form.Item>
            </Form>
        )
    }
}


export default NewPost;