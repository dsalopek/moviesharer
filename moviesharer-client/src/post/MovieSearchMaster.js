import React, { Component } from "react";
import MovieList from "./MovieList";
import NewPost from "./NewPost";

class MovieSearchMaster extends Component {
    constructor(props){
        super(props);
        this.state = {
            movieDetails: null
        }
    }

    selectedMovie = (movieDetails) => {
        this.setState({movieDetails});
    }

    render() {
        return (
            <div>
                {!this.state.movieDetails ? 
                    (<MovieList onSelectMovie={this.selectedMovie}/>)
                    : (<NewPost/>)
                }
            </div>
        )
    }
}

export default MovieSearchMaster;