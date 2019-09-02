import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';

class UserEdit extends Component {

    emptyItem = {
        date: '',
        city: '',
        stateOrProvince: '',
        pending: '',
        description: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const process = await (await fetch(`http://localhost:8080/api/process/${this.props.match.params.id}`)).json();
            this.setState({item: process});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        if(item.id) {
            await fetch(`http://localhost:8080/api/process/${this.props.match.params.id}`, {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item),
            });
        } else {
            await fetch('http://localhost:8080/api/process', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(item),
            });
        }
        this.props.history.push('/processes');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit User' : 'Add User'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="date">Data</Label>
                        <Input type="date" name="date" id="date" value={item.date || ''}
                               onChange={this.handleChange} autoComplete="date"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="city">City</Label>
                        <Input type="text" name="city" id="city" value={item.city || ''}
                               onChange={this.handleChange} autoComplete="city-level1"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="type">State/Province</Label>
                        <Input type="text" name="stateOrProvince" id="stateOrProvince" value={item.stateOrProvince || ''}
                               onChange={this.handleChange} autoComplete="stateOrProvince-level1"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="pending">Pending</Label>
                        <Input type="checkbox" name="pending" id="pending" value={item.pending || 0}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="textarea" name="description" value={item.description || ''}
                               onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/processes">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(UserEdit);
