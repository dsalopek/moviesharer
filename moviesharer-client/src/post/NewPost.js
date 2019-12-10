import React, { Component } from 'react';
import MovieSearchResultList from './MovieSearchResultList';
import './NewPost.css';
import { now } from '../../../../../../../Users/andrewsalopek/Library/Caches/typescript/3.6/node_modules/moment/moment';
import { func } from 'prop-types';
import { getAllUsers } from '../util/APIUtils';
import { NewPostDetails } from './NewPostDetails';


export class NewPost extends Component {
    constructor(props){
        super(props);
        this.state = {
            currentStep: 1,
            movieId: null,
            proposedDate: null,
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

    next = () => {
        if(this.state.currentStep < states.length) {
            this.setState({currentStep: this.state.currentStep+1})
        } else {
            this.setState({currentStep: this.state.currentStep})
        }
    }
    prev = () => {
        if(this.state.currentStep > 1) {
            this.setState({currentStep: this.state.currentStep-1})
        } else {
            this.setState({currentStep: this.state.currentStep})
        }
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
        if(!list.includes(friend)){
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

    currentStep() {
        switch(this.state.currentStep) {
            case 1:
                return(
                    <MovieSearchResultList
                        chosenMovieId={this.state.movieId}
                        onSelectMovie={this.selectedMovie}
                        next={this.next}
                        prev={this.prev}
                    />
                )
            case 2:
                return(
                    <NewPostDetails 
                        friendList={this.state.friendList}
                        handleSelectFriend={this.handleSelectFriend}
                        handleRemoveFriend={this.handleRemoveFriend}
                        selectedFriends={this.state.attendees}
                    />
                )
        }
    }

    render() {
        const previousButton = this.state.currentStep > 1 ? (<input className={`button-prev wizard-button`} type="button" value="Back" onClick={this.prev}/>) : (null);
        const nextButton = this.state.currentStep < states.length ? (<input className={`button-next wizard-button`} type="button" value="Next" onClick={this.next}/>) : (null);
        return (
            <div>
                {this.currentStep()}
                <div className="wizard-buttons">
                    {previousButton}
                    {nextButton}
                </div>
            </div>
        )
    }
}

export const states = ["Movie", "Location & Time", "Attendees", "Review"];



