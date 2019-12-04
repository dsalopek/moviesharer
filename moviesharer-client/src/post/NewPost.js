import React, { Component } from 'react';
import MovieList from './MovieList';
import { now } from '../../../../../../../Users/andrewsalopek/Library/Caches/typescript/3.6/node_modules/moment/moment';
import { getAllUsers } from '../util/APIUtils';
import { func } from 'prop-types';


class NewPost extends Component {
    _isMounted = false;
    constructor(props){
        super(props);
        this.state = {
            movieId: null,
            proposedDate: now() + 7,
            attendees: []
        }
    }

    componentDidMount() {
        this._isMounted = true;
        getAllUsers()
            .then(response => {
                if(this._isMounted) {
                    this.setState({
                        attendees: response
                    });
                }
                console.log(this.state.attendees);
            });
    }

    componentWillUnmount() {
        this._isMounted = false;
    }

    render() {
        const names = this.state.attendees.map(r => 
            <p>{r.username}</p>
        );
        console.log("logs:" + names);
        return (
            <form>
                <label>
                    Movie
                </label>
                <br/>
                <label>
                    Proposed Date
                </label>
                <br/>
                <label>
                    FriendList
                </label>
            </form>
        )
    }
}


export default NewPost;