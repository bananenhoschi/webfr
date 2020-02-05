import React from 'react'
import Dialog from './Dialog'

/**
 * Zeigt einen Questionnaire (readonly) an.
 * 
 * @param questionnaire Der Questionnaire, der angezeigt wird
 */
const QuestionnaireShowDialog = ({ questionnaire }) => 
    <Dialog 
        buttonLabel='Show' 
        title='Show Questionnaire' 
        actionButtonLabel='Close' 
        questionnaire={ questionnaire }  
        isReadOnly={ true } />

export default QuestionnaireShowDialog
