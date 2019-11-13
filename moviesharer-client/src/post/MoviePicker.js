import React, { Component } from 'react';
import 'antd/dist/antd.css';
import './MoviePicker.css'; 

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
			<form>
				<span>
					<input type="text" value={this.props.value} onChange={this.props.onChange}></input>
					<input type="button" value="Submit" onClick={this.props.onSearch} />
				</span>
			</form>
		);
	}
}

export default MovieSearchBar;