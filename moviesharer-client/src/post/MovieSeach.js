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
			<div class="flex-container">
				<input class="fill-width" type="text" value={this.props.value} onChange={this.props.onChange}/>
				<input type="button" value="Submit" onClick={this.props.onSearch} />
			</div>
		);
	}
}

export default MovieSearchBar;