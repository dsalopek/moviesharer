import React, { Component } from 'react';
import { getAllPosts } from '../util/APIUtils';
import { Card, Icon, notification } from 'antd';
import { POLL_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import './PostList.css';

class PostList extends Component {
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

        // if(this.props.username) {
        //     if(this.props.type === 'USER_CREATED_POLLS') {
        //         promise = getUserCreatedPolls(this.props.username, page, size);
        //     } else if (this.props.type === 'USER_VOTED_POLLS') {
        //         promise = getUserVotedPolls(this.props.username, page, size);                               
        //     }
        // } else {
        //     promise = getAllPolls(page, size);
        // }

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
        // const pollViews = [];
        // this.state.polls.forEach((poll, pollIndex) => {
        //     pollViews.push(<Poll 
        //         key={poll.id} 
        //         poll={poll}
        //         currentVote={this.state.currentVotes[pollIndex]} 
        //         handleVoteChange={(event) => this.handleVoteChange(event, pollIndex)}
        //         handleVoteSubmit={(event) => this.handleVoteSubmit(event, pollIndex)} />)            
        // });
        console.log(this.state);
        const data = this.state.posts;
        return (
            <div>
                {data.map(function (d, idx) {
                    return (

                        <div style={{ background: '#ECECEC', padding: '30px' }}>
                            <Card title={d.post.postId} bordered={false} style={{ width: 300 }}>
                                <img src={ 'https://image.tmdb.org/t/p/w500/rGE9sOt1jOtKtb3bARi33Eg1xfK.jpg' } />
                                <p>Attendees</p>
                                <ul>
                            {d.attendeeList.map(function (d, idx) {
                                    return (<li key = {idx}>{d.username}</li>)
                                })}
                                </ul>
                            </Card>
                        </div>
                        // <li key={idx}>
                        //     {d.post.postId}
                        //     <ul>
                        //         {d.attendeeList.map(function (d, idx) {
                        //             return (<li key = {idx}>{d.username}</li>)
                        //         })}
                        //     </ul>
                        // </li>
                    )
                })}
            </div>
            // <div className="polls-container">
            //     {this.state}
            //     {/* {
            //         !this.state.isLoading && this.state.polls.length === 0 ? (
            //             <div className="no-polls-found">
            //                 <span>No Polls Found.</span>
            //             </div>    
            //         ): null
            //     }  
            //     {
            //         !this.state.isLoading && !this.state.last ? (
            //             <div className="load-more-polls"> 
            //                 <Button type="dashed" onClick={this.handleLoadMore} disabled={this.state.isLoading}>
            //                     <Icon type="plus" /> Load more
            //                 </Button>
            //             </div>): null
            //     }              
            //     {
            //         this.state.isLoading ? 
            //         <LoadingIndicator />: null                     
            //     } */}
            // </div>
        );
    }
}

export default withRouter(PostList);