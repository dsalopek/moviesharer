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
                    <span className="close" onClick={props.closeModal}>&times;</span>
                    <h2>{props.movieDetails.title}</h2>
                    <p>{props.movieDetails.overview}</p>
                    <p>{props.movieDetails.release_date.split("-")[0]}</p>
                    <p>{displayRuntime(props.movieDetails.runtime)}</p>
                </div>
            </div>
        );
    } else {
        return null;
    }
    
}

export default MovieModal;