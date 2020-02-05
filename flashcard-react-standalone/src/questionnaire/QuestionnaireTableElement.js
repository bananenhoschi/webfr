import React from 'react'
import _ from 'lodash'
import { Button } from 'reactstrap'
import QuestionnaireShowDialog from './QuestionnaireShowDialog'
import QuestionnaireUpdateDialog from './QuestionnaireUpdateDialog'

const QuestionnaireTableElement = ({ questionnaire, save, remove }) => (
    <tr key={questionnaire.id} >
        <td>{questionnaire.id}</td>
        <td>{questionnaire.title}</td>
        <td>{questionnaire.description}</td>
        <td>
            <div className="btn-group float-right" role="group">
                <QuestionnaireShowDialog questionnaire={questionnaire} />
                <QuestionnaireUpdateDialog save={save} questionnaire={questionnaire} />
                <Button color='danger'
                    onClick={_.partial(remove, questionnaire.id)}
                    className='float-right' >Delete</Button>
            </div>
        </td>

    </tr>
)

export default QuestionnaireTableElement