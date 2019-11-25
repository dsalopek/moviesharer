import React, { Component } from 'react';
import './MovieSearch.css'; 

class MovieSearchBar extends Component {
	constructor(props) {
		super(props);
		this.state = {
			data: [],
			value: '',
			isLoading: false
		};
	}
	render() {
		return (
			<div className="search-container">
				<span>
					<input 
						className="search-bar" 
						type="text" placeholder={"Lord of the Rings"} 
						value={this.props.value}
						onKeyPress={this.props.onKeyPress}
						onChange={this.props.onChange}
					/>
					<input 
						className="search-button" 
						type="button" 
						value="Search" 
						onClick={this.props.onSearch}
					/>
				</span>
			</div>
		);
	}
}

export default MovieSearchBar;