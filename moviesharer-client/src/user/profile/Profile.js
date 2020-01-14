import React, { Component } from 'react';
import './Profile.css';

class Profile extends Component {
    constructor(props) {
        super(props)
    }

    render() {
        return (
        <div className="profile-card">
            {/* <img className="profile-image" src="https://image.tmdb.org/t/p/w342//8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg"/> */}
            <div className="profile-details">
                <h2 className="profile-full-name">Dylan Salopek</h2>
                <p className="profile-handle">dylan.salopek</p>
            </div>
        </div>
        )

    }
}

export default Profile;