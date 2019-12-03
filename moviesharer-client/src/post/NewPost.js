import React, { Component } from 'react';
import MovieList from './MovieList';
import { now } from '../../../../../../../Users/andrewsalopek/Library/Caches/typescript/3.6/node_modules/moment/moment';
import { getAllUsers } from '../util/APIUtils';


class NewPost extends Component {
    constructor(props){
        super(props);
        this.state = {
            movieId: undefined,
            proposedDate: now() + 7,
            attendees: null
        }
    }

    getFriends() {
        getAllUsers()
            .then(response => {
                let attendees;
                response.forEach(r => {
                    attendees.push({
                        r
                    });
                });
                console.log(attendees);
            })
    }

    render() {
        return (
            <div>
                <MovieList/>
            </div>
        )
    }
}


export default NewPost;