import React from 'react';
import './MovieModal.css';
import {getMovieDetails} from '../util/APIUtils';


function MovieModal(props) {

    function displayRuntime(runtime) {
        const hours = Math.floor(runtime / 60);
        const minutes = runtime % 60;
        return hours + (hours > 1 ? ' hrs ' : ' hr ') + minutes + ' minutes';
    }

    function createObject() {
        props.handleSelect();
        var movieDetails = {
            id : props.movieDetails.id,
            title : props.movieDetails.title,
            overview : props.movieDetails.overview,
            posterURL : props.movieDetails.poster_path,
            backdropURL : props.movieDetails.backdrop_path
        };
        return movieDetails;
    }

    if(props.showModal) {        
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
                    <p>{displayRuntime(props.movieDetails.runtime)}</p>
                    <p className="movie-overview">{props.movieDetails.overview}</p>
                    {props.movieDetails.genres.map(
                        function (g, idx) { return (<p key={idx} className="movie-genre-tag">{g.name}</p>)})}
                    <input type="button" className="select-button" value ="Select this movie" onClick={()=>props.onSelectMovie(createObject())}></input>
                </div>
            </div>
        );
    } else {
        return null;
    }
    
}

export default MovieModal;