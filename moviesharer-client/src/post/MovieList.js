import React, { Component } from 'react';
import MovieSearchBar from './MovieSeach';
import MovieModal from './MovieModal';
import './MovieList.css';
import { queryMovies, getMovieDetails } from '../util/APIUtils';

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
            value: '',
            selectedMovieId: undefined,
            movieDetails: undefined
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

    handleKeyPress = event => {
        if(event.key=='Enter') {
            this.handleSearch();
        }
    }

    handleSelect = (rowId) => {
        console.log(rowId);
        this.setState({
            selectedMovieId: rowId
        })

        getMovieDetails(rowId)
            .then(response => {
                console.log(response);
                this.setState({
                    movieDetails: response
                })
            });
    }

    closeModal = () => {
        this.setState({
            selectedMovieId: undefined,
            movieDetails: undefined
        })
    }

    render() {
        const results = this.state.data.map(
            (row) => <div key={row.id} className="movie-card" onClick={() => this.handleSelect(row.id)}>
                        <img className="movie-poster"  src={'https://image.tmdb.org/t/p/w342/'+row.poster_path}/>
                    </div>);

        return (
            <div>      
                <MovieSearchBar 
                    onSearch={this.handleSearch} 
                    onChange={this.handleChange}
                    onKeyPress={this.handleKeyPress}
                    data={this.state.data} 
                />
                <div className="movie-cards">{results}</div>
                <MovieModal 
                    movieDetails={this.state.movieDetails}
                    closeModal={this.closeModal}
                />
            </div>
        );
    }
}

export default MovieList;