import React, { Component } from 'react';
import MovieSearchResultList from './MovieSearchResultList';
import './NewPost.css';
import { now } from '../../../../../../../Users/andrewsalopek/Library/Caches/typescript/3.6/node_modules/moment/moment';
import { func } from 'prop-types';
import { getAllUsers, createPost } from '../util/APIUtils';
import { NewPostDetails } from './NewPostDetails';


export class NewPost extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentStep: 1,
            movieId: null,
            proposedDate: Date.now(),
            movieDetails: {},
            attendees: [],
            friendFilter: '',
            friendList: []
        }
    }

    selectedMovie = (movieDetails) => {
        this.setState({
            movieDetails: movieDetails,
            movieId: movieDetails.id
        });
    }

    componentDidMount() {
        getAllUsers()
            .then(response => {
                this.setState({
                    friendList: response
                });
            });
    }

    handleSelectFriend = (friend) => {
        let list = this.state.attendees;
        if (!list.includes(friend)) {
            list.push(friend);
        }
        this.setState({
            attendees: list,
            friendFilter: ''
        });
    }

    handleRemoveFriend = (friend) => {
        let list = this.state.attendees;
        list.splice(list.indexOf(friend), 1);
        this.setState({
            attendees: list,
            friendFilter: ''
        })
    }

    handleFormSubmit = () => {
        var postRequest = {
            movie: this.state.movieDetails,
            proposedDate: Math.floor(this.state.proposedDate/1000),
            attendeeList: this.state.attendees
        };
        console.log(postRequest);
        createPost(postRequest);
    }

    handleChangeDate = (event) => {
        let value = event.target.value;
        this.setState({
            proposedDate: value
        })
    }

    render() {
        return (
            <div className="post-content">
                <div className="main-content">
                    <div className="pane-contents">
                        <MovieSearchResultList
                            chosenMovieId={this.state.movieId}
                            onSelectMovie={this.selectedMovie}
                            next={this.next}
                            prev={this.prev}
                        />
                    </div>
                </div>
                <div className="sidebar-content">
                    <div className="pane-contents">
                        <NewPostDetails
                            friendList={this.state.friendList}
                            handleSelectFriend={this.handleSelectFriend}
                            handleRemoveFriend={this.handleRemoveFriend}
                            handleChangeDate={this.handleChangeDate}
                            proposedDate={this.state.proposedDate}
                            selectedFriends={this.state.attendees}
                            handleFormSubmit={this.handleFormSubmit}
                        />
                    </div>
                </div>
            </div>
        )
    }
}

export const states = ["Movie", "Location & Time", "Attendees", "Review"];



