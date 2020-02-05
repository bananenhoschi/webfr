import Header from './Header';
import Footer from './Footer';
import Loader from '../misc/Loader'
import Message from '../misc/Message'
import React, {useEffect, useState} from 'react'
import {Container} from 'reactstrap';
import QuestionnaireContainer from '../questionnaire/QuestionnaireContainer'

const App = () => {
    const [serverUrl, setServerUrl] = useState(null);
    const [isError, setIsError] = useState(false)

    useEffect(
        () => {
            fetch('application.json')
                .then(res => res.json())
                .then(config => setServerUrl(config.url))
        },
        []
    );

    let comp
    if (serverUrl === null) {
        comp = <Loader/>
    } else {
        if (isError) {
            comp = <Message message='Network error'/>
        } else {
            comp = <QuestionnaireContainer serverUrl={serverUrl}/>
        }
    }


    return (
        <Container>
            <Header title='Flashcard Client with React' subtitle='Version 1'/>
            {comp}
            <Footer message='@ The FHNW Team'/>
        </Container>
    )
}

export default App