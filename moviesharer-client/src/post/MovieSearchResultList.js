import React, { Component } from 'react';
import MovieSearchBar from './MovieSearchBar';
import MovieModal from './MovieModal';
import './MovieSearchResultList.css';
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

class MovieSearchResultList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            value: '',
            selectedMovieId: undefined,
            movieDetails: undefined,
            showModal: false
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

    handleClick = (rowId) => {
        this.setState({
            selectedMovieId: rowId
        })

        getMovieDetails(rowId)
            .then(response => {
                this.setState({
                    movieDetails: response,
                    showModal: true
                })

            });
    }

    handleSelect = () => {
        this.setState({
            showModal: false,
            movieDetails: null
        })
    }

    closeModal = () => {
        this.setState({
            selectedMovieId: undefined,
            movieDetails: undefined,
            showModal: false
        })
    }

    render() {
        const results = this.state.data.map(
            (row) => <div key={row.id} className={`movie-card ${this.props.chosenMovieId == row.id ? "selected-card" : ""}`} onClick={() => this.handleClick(row.id)}>
                            <a><img className="movie-poster" src={'https://image.tmdb.org/t/p/w342/'+row.poster_path}/></a>
                    </div>);

        return (
            <div className="searching-container">      
                <MovieSearchBar 
                    onSearch={this.handleSearch} 
                    onChange={this.handleChange}
                    onKeyPress={this.handleKeyPress}
                    data={this.state.data} 
                />
                <div className="movie-cards">{results}</div>
                <MovieModal 
                    showModal={this.state.showModal}
                    handleSelect={this.handleSelect}
                    onSelectMovie={this.props.onSelectMovie}
                    movieDetails={this.state.movieDetails}
                    closeModal={this.closeModal}
                />
            </div>
        );
    }
}

export default MovieSearchResultList;