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
        var dateobj = new Date('October 15, 1996 05:35:32'); 
  
            // Contents of above date object is converted 
            // into a string using toISOString() function. 
            var B = dateobj.toISOString(); 
            console.log(B);
        return B;
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
                    <div className="date">
                        <label className="form-label">
                            Date &amp; Time
                            <input className="date-time" type="datetime-local" value={this.formatDate(this.props.proposedTime)} onChange={() => this.props.handleChangeDate()}/>
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