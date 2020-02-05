import React from 'react';
import {Jumbotron} from 'reactstrap';

const Header = ({title, subtitle}) =>
    <Jumbotron>
        <h1>{title}</h1>
        <h2>{subtitle}</h2>
    </Jumbotron>;

export default Header;