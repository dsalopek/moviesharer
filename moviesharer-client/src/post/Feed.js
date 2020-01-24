import React, { Component } from 'react';
import { getMyFeed } from '../util/APIUtils';
import { POLL_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import './Feed.css';

function determineAttendeeColor(responseId) {
    let color = "";
    if(responseId == 1) {
        color = "attendee-decline";
    } else if(responseId == 2) {
        color = "attendee-accept";
    } else if(responseId == 3) {
        color = "attendee-tentative";
    } else {
        color = "attendee-accept";
    }

    return color;
}

class Feed extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            isLoading: false
        };
        this.loadPostList = this.loadPostList.bind(this);
    }

    loadPostList() {
        let promise = getMyFeed();

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
        // console.log(this.state);
        const data = this.state.posts;
        return (
            <div className="post-container">
                <div className="post-cards">
                    {data.map(function (d, idx) {
                        return (
                            <div key={idx} className="post-card">
                                {/* <div className="post-card-header">
                                    <div className={`post-poster`}>Dylan Salopek</div>
                                </div> */}
                                <div className="post-card-contents">
                                    <img className="movie-poster" src={'https://image.tmdb.org/t/p/w342/'+d.movie.posterURL} />
                                    <div className="movie-details">
                                        <h2>{d.movie.title}</h2>
                                        <p>{d.movie.overview}</p>
                                        <p>{d.post.proposedDate}</p>
                                        <h3>Attendees</h3>
                                            <div className="post-attendees">
                                                {
                                                    d.attendeeResponses ? 
                                                    (d.attendeeResponses.map(function (d1, idx) {
                                                        return (<div key={idx} className={`post-attendee ${determineAttendeeColor(d1.responseId)}`}>{d1.firstName+' '+d1.lastName}</div>)
                                                    })) : null}
                                            </div>
                                    </div>
                                </div>
                            </div>
                        )
                    })}
                </div>
            </div>
        );
    }
}

export default withRouter(Feed);