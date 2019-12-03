import React from 'react';
import './MovieModal.css';
import {getMovieDetails} from '../util/APIUtils';


function MovieModal(props) {

    function displayRuntime(runtime) {
        const hours = Math.floor(runtime / 60);
        const minutes = runtime % 60;
        return hours + ' hrs ' + minutes + ' minutes';
    }

    if(props.movieDetails) {        
        return ( 
            <div className="movie-modal">
                <div className="modal-content">
                    <div className="movie-header">
                        <div className="movie-title">
                            {props.movieDetails.title}
                        </div>
                        <div className="release-year">
                            {props.movieDetails.release_date.split("-")[0]}
                        </div>
                        <div className="close" 
                            onClick={props.closeModal}>
                            &times;
                        </div>
                    </div>
                    <p className="movie-overview">{props.movieDetails.overview}</p>
                    {props.movieDetails.genres.map(
                        function (g, idx) { return (<p className="movie-genre-tag">{g.name}</p>)})}
                    <p>{displayRuntime(props.movieDetails.runtime)}</p>
                    <input type="button" className="select-button" value ="Select this movie"></input>
                </div>
            </div>
        );
    } else {
        return null;
    }
    
}

export default MovieModal;