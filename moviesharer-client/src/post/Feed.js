import React, { Component } from 'react';
import { getAllPosts } from '../util/APIUtils';
import { POLL_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import './Feed.css';

class Feed extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            isLoading: false
        };
        this.loadPostList = this.loadPostList.bind(this);
    }

    loadPostList(page = 0, size = POLL_LIST_SIZE) {
        let promise = getAllPosts();

        if (!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {
                const posts = this.state.posts.slice();

                this.setState({
                    posts: posts.concat(response),
                    isLoading: false
                })
            }).catch(error => {
                this.setState({
                    isLoading: false
                })
            });

    }

    componentDidMount() {
        this.loadPostList();
    }

    componentDidUpdate(nextProps) {
        if (this.props.isAuthenticated !== nextProps.isAuthenticated) {
            // Reset State
            this.setState({
                polls: [],
                isLoading: false
            });
            this.loadPostList();
        }
    }

    render() {
        console.log(this.state);
        const data = this.state.posts;
        return (
            <div className="post-cards">
                {data.map(function (d, idx) {
                    return (
                        <div key={idx} className="post-card">
                            <img className="movie-poster" src={'https://image.tmdb.org/t/p/w342/'+'5VTN0pR8gcqV3EPUHHfMGnJYN9L.jpg'} />
                            {/* { <div title={'https://image.tmdb.org/t/p/w342/'+d.post.postId} bordered={false} style={{ width: 300 }}> */}
                                {/* <img src={'https://image.tmdb.org/t/p/w500/rGE9sOt1jOtKtb3bARi33Eg1xfK.jpg'} />
                                <p>Attendees</p>
                                <ul>
                                    {d.attendeeList.map(function (d, idx) {
                                        return (<li key={idx}>{d.username}</li>)
                                    })}
                                </ul>
                            </div> */}
                        </div>
                    )
                })}
            </div>
        );
    }
}

export default withRouter(Feed);