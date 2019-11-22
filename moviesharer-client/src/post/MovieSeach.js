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
			<div class="search-container">
				<span>
					<input class="search-bar" type="text" placeholder={"Lord of the Rings"} value={this.props.value} onChange={this.props.onChange}/>
					<input class="search-button" type="button" value="Search" onClick={this.props.onSearch}/>
				</span>
			</div>
		);
	}
}

export default MovieSearchBar;