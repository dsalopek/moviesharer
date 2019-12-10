import React, {Component} from 'react';
import { getAllUsers } from '../util/APIUtils';
import './NewPostDetails.css';

export class NewPostDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            friendList: [],
            nameFilter: ''
        }
    }

    filterFriends = (event) => {
        let value = event.target.value;
        this.setState({nameFilter: value});
    }

    handleSelectFriend = (friend) => {
        this.props.handleSelectFriend(friend);
        this.setState({nameFilter: ''});
    }

    getFriends() {
        if(this.state.nameFilter) {
            return this.props.friendList.filter((friend) => {
                let friendName = friend.firstName + ' ' + friend.lastName;
                return friendName.toLowerCase().includes(this.state.nameFilter) && !this.props.selectedFriends.includes(friend);
            });
        } else {
            return [];
        }
    }

    submitForm = (event) => {
        event.preventDefault();
    }

    render() {
        const results = this.getFriends().map(result => {
                return (
                    <li className="friend-result"
                        onClick={()=>this.handleSelectFriend(result)} 
                        key={result.id}>
                        <a className="friend-name">
                            {result.firstName + ' ' +result.lastName}
                        </a>
                    </li>
                )
            });        
        
        const selectedFriends = this.props.selectedFriends.map(friend => {
            return (
                <div className="selected-friend">
                    <div className="selected-name">
                        {friend.firstName + " " + friend.lastName}
                    </div>
                    <div className="remove-friend"onClick={()=>this.props.handleRemoveFriend(friend)}>
                        &times;
                    </div>
                </div>
                )
        })


        return (
            <form onSubmit={this.submitForm}>
                <div className="form-container">
                    <div className="friend-select">
                        {selectedFriends} 
                        <div className="friend-search">
                            <input className="friend-search-bar" type="text" onChange={this.filterFriends} value={this.state.nameFilter}/>
                            <ul className="friend-results">
                                {results}
                            </ul>
                        </div>
                    </div>
                    
                </div>
            </form>
        )
    }
}