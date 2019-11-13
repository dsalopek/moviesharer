import React, { Component } from 'react';
import MovieSearchBar from './MoviePicker';
import { queryMovies } from '../util/APIUtils';

function fetch(value, callback) {
    queryMovies(value)
        .then(response => {
            const { results } = response;
            const data = [];
            results.forEach(r => {
                data.push({
                    r
                });
            });
            callback(results);
        });
}

class MovieList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            value: ''
        };
    }

    handleSearch = () => {
        const value = this.state.value;
        if (value) {
            fetch(value, data => this.setState({ data }));
        } else {
            this.setState({ data: [] });
        }
    };

    handleChange = event => {
        this.setState({ value: event.target.value });
    };

    render() {
        return (
            <MovieSearchBar onSearch={this.handleSearch} onChange={this.handleChange} data={this.state.data} />
            // todo: add in result list below
        );
    }
}

export default MovieList;