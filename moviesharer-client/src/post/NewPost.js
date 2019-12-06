import React, { Component } from 'react';
import MovieSearchResultList from './MovieSearchResultList';
import './NewPost.css';
import { now } from '../../../../../../../Users/andrewsalopek/Library/Caches/typescript/3.6/node_modules/moment/moment';
import { getAllUsers } from '../util/APIUtils';
import { func } from 'prop-types';


export class NewPost extends Component {
    constructor(props){
        super(props);
        this.state = {
            currentStep: 1,
            movieId: null,
            proposedDate: null,
            attendees: []
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
                    <form>
                        <div className="form-container">
                            <label>Address</label>
                            <input type="text" list="data" className="form-input"/>
                            <datalist id="data">
                                <option key={1} value="4905 Snow Rd, Las Cruces" about="hello"/>
                            </datalist>
                            <br/>
                            <textarea className={`form-input form-text-area`}/>
                        </div>
                    </form>
                )
        }
    }

    render() {
        const names = this.state.attendees.map(r => 
            <p>{r.username}</p>
        );

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



// getAllUsers()
//             .then(response => {
//                 if(this._isMounted) {
//                     this.setState({
//                         attendees: response
//                     });
//                 }
//                 console.log(this.state.attendees);
//             });