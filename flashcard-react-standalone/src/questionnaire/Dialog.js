import React, { useState, useEffect } from 'react'
import _ from 'lodash'
import { Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Col } from 'reactstrap'
import InputGroup from './InputGroup'

/**
 * Modale Dialog Komponente.
 * 
 * @param buttonLabel Label des Buttons in der Tabelle
 * @param title Der Titel des Dialogs
 * @param actionButtonLabel Label des 'Submit' Buttons
 * @param questionnaire Der Questionnaire
 * @param isReadOnly True, wenn die Input Felder im Dialog nicht editierbar sind, false sonst (Default: false)
 * @param actionFn Die Funktion, welche aufgerufen wird, wenn der Dialog geschlossen wird (Save, Close)
 * @param css Die CSS Klasse für den Button in der Tabelle
 */
const Dialog = ({ buttonLabel, title, actionButtonLabel, questionnaire: qx, isReadOnly = false, actionFn, css }) => {

    let [showModal, setShowModal] = useState(false)
    let [questionnaire, setQuestionnaire] = useState(qx)
    
    /**
     * Wir beobachten hier qx. qx ist der Questionnaire, der als props in die Komponente
     * hinein gegeben wird. Wenn sich qx ändert, wollen wir diese Änderung übernehmen.
     */
    useEffect(() => {
        setQuestionnaire(qx)
    }, [qx])

    const change = event => 
        setQuestionnaire({ ...questionnaire, [event.target.name]: event.target.value })

    const close = () => 
        setShowModal(false)

    const open = () => 
        setShowModal(true)

    /**
     * Diese Funktion wird aufgerufen, wenn Save, oder Close gedrückt wird.
     * Sie übernimmt die actionFn, die mittels props in diese Komponente 
     * übergeben wird. Falls es keine actionFn gibt, wird identity verwendet.
     * Diese Funktion schliesst nach dem Aufruf der actionFn den Dialog.
     * 
     * @param questionnaire Der Questionnaire
     * @param actionFn Die Action-Funktion (update, create)
     */
    const onAction = (questionnaire, actionFn) => {
        (actionFn || _.identity)(questionnaire)
        close()
    }
    
    return <div>
        <Button color={ css || 'secondary'} onClick={ open }
            className='float-right'>{ buttonLabel }</Button>
        <Modal isOpen={ showModal } toggle={ close } size='lg' autoFocus={false}>
            <ModalHeader toggle={ close } >
                { title }
            </ModalHeader>
            <ModalBody>
                <Form>
                    <InputGroup 
                        label='Title'
                        id='formTitle'
                        changeFn={ change }
                        name='title'
                        value={ questionnaire.title }
                        isReadOnly={ isReadOnly }
                    />

                    <InputGroup 
                        label='Description'
                        id='formDescription'
                        changeFn={ change }
                        name='description'
                        value={ questionnaire.description }
                        isReadOnly={ isReadOnly }
                    /> 

                    <FormGroup>
                        <Col className='clearfix' style={{ padding: '.2rem' }}>
                            <Button className='float-right' color='secondary'
                                onClick={ _.partial(onAction, questionnaire, actionFn) }>{ actionButtonLabel }</Button>
                        </Col>
                    </FormGroup>
                </Form>
            </ModalBody>
        </Modal>
    </div>
}

export default Dialog