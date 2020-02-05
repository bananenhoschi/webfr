import React from 'react';
import { Row, Col } from 'reactstrap';

const Footer = ({ leftMessage }) =>
    <Row>
        <Col>{leftMessage}</Col>
    </Row>;

export default Footer;
