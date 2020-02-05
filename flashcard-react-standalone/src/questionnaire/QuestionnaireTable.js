import React from 'react'
import _ from 'lodash'
import { Table } from 'reactstrap'
import QuestionnaireTableElement from './QuestionnaireTableElement'

const QuestionnaireTable = ({ qs, save, remove }) =>
    <Table hover>
        <tbody>
        { 
            _.map(qs, questionnaire => 
                <QuestionnaireTableElement 
                    key={ questionnaire.id } 
                    questionnaire={ questionnaire }
                    save={ save }
                    remove={ remove } />
            ) 
        }
        </tbody>
    </Table>

export default QuestionnaireTable