import React, { Component } from 'react';

class Poll extends Component {
    render() {    
        return (
            <div className="poll-content">
                <div className="poll-header">
                    <div className="poll-creator-info">
                        <Link className="creator-link" to={`/users/${this.props.poll.createdBy.username}`}>
                            <Avatar className="poll-creator-avatar" 
                                style={{ backgroundColor: getAvatarColor(this.props.poll.createdBy.name)}} >
                                {this.props.poll.createdBy.name[0].toUpperCase()}
                            </Avatar>
                            <span className="poll-creator-name">
                                {this.props.poll.createdBy.name}
                            </span>
                            <span className="poll-creator-username">
                                @{this.props.poll.createdBy.username}
                            </span>
                            <span className="poll-creation-date">
                                {formatDateTime(this.props.poll.creationDateTime)}
                            </span>
                        </Link>
                    </div>
                    <div className="poll-question">
                        {this.props.poll.question}
                    </div>
                </div>
                <div className="poll-choices">
                    <RadioGroup 
                        className="poll-choice-radio-group" 
                        onChange={this.props.handleVoteChange} 
                        value={this.props.currentVote}>
                        { pollChoices }
                    </RadioGroup>
                </div>
                <div className="poll-footer">
                    { 
                        !(this.props.poll.selectedChoice || this.props.poll.expired) ?
                        (<Button className="vote-button" disabled={!this.props.currentVote} onClick={this.props.handleVoteSubmit}>Vote</Button>) : null 
                    }
                    <span className="total-votes">{this.props.poll.totalVotes} votes</span>
                    <span className="separator">•</span>
                    <span className="time-left">
                        {
                            this.props.poll.expired ? "Final results" :
                            this.getTimeRemaining(this.props.poll)
                        }
                    </span>
                </div>
            </div>
        );
    }
}


export default Poll;