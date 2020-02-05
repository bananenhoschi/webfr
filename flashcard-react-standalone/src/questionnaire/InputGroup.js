import React from 'react'
import { FormGroup, Label, Col, Input } from 'reactstrap'

/**
 * Zeigt ein Label und ein Input Field an.
 * 
 * @param label Das Label des Input Field
 * @param id Die id, welche das Label und das Input Field verbindet (for - id)
 * @param changeFn Die onChange Funktion des Input Fields
 * @param name Der Name des Input Fields
 * @param value Der Value des Input Fields
 * @param isReadOnly True, wenn das Input Field readonly ist, false sonst
 */
const InputGroup = ({ label, id, changeFn, name, value, isReadOnly }) => 
    <FormGroup row>
        <Label md={ 2 } for={ id }>
            { label }
        </Label>
        <Col md={ 10 }>
            <Input 
                type='text' 
                id={ id }
                onChange={ changeFn }
                name={ name }
                value={ value }
                plaintext={ isReadOnly }
                disabled={ isReadOnly } />
        </Col>
    </FormGroup>

export default InputGroup