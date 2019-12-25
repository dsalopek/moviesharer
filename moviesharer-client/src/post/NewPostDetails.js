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
        this.setTextInputRef = element => {
            this.textInput = element;
          };
      
          this.focusTextInput = () => {
            // Focus the text input using the raw DOM API
            if (this.textInput) this.textInput.focus();
          };
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
        this.props.handleFormSubmit();
    }

    componentDidMount() {
        this.focusTextInput();
    }

    formatDate = (value) => {
        var dateobj = new Date(value);
        var final = "".concat(dateobj.getFullYear(), '-', dateobj.getMonth(), '-', dateobj.getDate(), 'T', ('0'+dateobj.getHours()).slice(-2), ':', ('0'+dateobj.getMinutes()).slice(-2));
        return final;
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
        
        const selectedFriends = this.props.selectedFriends.map((friend, idx) => {
            return (
                <div className="selected-friend" key={idx}>
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
                    <div className="date">
                        <label className="form-label">
                            Date &amp; Time
                            <input className="date-time" type="datetime-local" value={this.formatDate(this.props.proposedDate)} onChange={() => this.props.handleChangeDate()}/>
                        </label>
                    </div>
                    <div className="friend-picker">
                        <label className="form-label">
                            Attendees
                            <div className="friend-select">
                                {selectedFriends}
                                <input className="friend-search-bar" 
                                    type="text" 
                                    onChange={this.filterFriends} 
                                    value={this.state.nameFilter}
                                    ref={this.setTextInputRef}
                                    placeholder="Search for a friend"
                                />
                            </div>
                            <ul className="friend-results">
                                {results}
                            </ul>
                        </label>
                    </div>
                    <div className="submit">
                        <input value="Submit" className="submit-button" type="submit"/>
                    </div>
                </div>
            </form>
        )
    }
}