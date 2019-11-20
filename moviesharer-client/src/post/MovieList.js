import React, { Component } from 'react';
import {
    Route,
    withRouter,
    Switch,
    Link
  } from 'react-router-dom';
import MovieSearchBar from './MovieSeach';
import './MovieList.css';
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
        const results = this.state.data.map(
            (row) => <div class="card">
                        <img src={'https://image.tmdb.org/t/p/w185/'+row.poster_path}/>
                        <div class="container">
                            <h4><b>{row.title}</b></h4>
                            <p>{row.overview}</p>
                        </div>
                    </div>);

        return (<div>
            <MovieSearchBar onSearch={this.handleSearch} onChange={this.handleChange} data={this.state.data} />
            {results}</div>
        );
    }
}

export default MovieList;