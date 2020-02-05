import React, {Component} from 'react';
import {Jumbotron, Button} from 'reactstrap';

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            counter: 0
        }
    }

    componentDidMount() {
        this.timer = setInterval(this.increment, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.timer);
    }

    increment = () => {
        this.setState({counter: this.state.counter + 1});
    }
    reset = () => {
        this.setState({counter: 0});
    }

    render() {
        return (
            <div className="container">
                <div>
                    <Jumbotron>
                        <h1>{this.state.counter}</h1>
                    </Jumbotron>
                </div>
                <div>
                    <Button onClick={this.reset}>RE-FRÄSCH</Button>
                </div>
            </div>

        )
    }

}

export default App;
