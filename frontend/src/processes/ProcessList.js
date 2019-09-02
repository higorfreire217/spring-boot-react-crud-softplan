import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class ProcessList extends Component {

    constructor(props) {
        super(props);
        this.state = {processes: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('http://localhost:8080/api/processes')
            .then(response => response.json())
            .then(data => this.setState({processes: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`http://localhost:8080/api/process/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedProcesss = [...this.state.processes].filter(i => i.id !== id);
            this.setState({processes: updatedProcesss});
        });
    }

    render() {
        const {processes, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const processList = processes.map(process => {
            return <tr key={process.id}>
                <td style={{whiteSpace: 'nowrap'}}>{process.id}</td>
                <td>{process.city}</td>
                <td>{process.stateOrProvince}</td>
                <td>{process.pending}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/processes/" + process.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(process.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/processes/new">Add Process</Button>
                    </div>
                    <h3>Processos</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">ID</th>
                            <th width="30%">City</th>
                            <th width="30%">State/Province</th>
                            <th width="30%">Pending</th>

                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {processList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default ProcessList;
