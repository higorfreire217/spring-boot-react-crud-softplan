import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserList from './users/UserList';
import UserEdit from './users/UserEdit';
import ProcessList from "./processes/ProcessList";
import ProcessEdit from "./processes/ProcessEdit";

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/users' exact={true} component={UserList}/>
            <Route path='/users/:id' component={UserEdit}/>
            <Route path='/processes' exact={true} component={ProcessList}/>
            <Route path='/processes/:id' component={ProcessEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;