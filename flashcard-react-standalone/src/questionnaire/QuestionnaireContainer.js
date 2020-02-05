import React, {useEffect, useState} from 'react';
import _ from 'lodash'
import QuestionnaireTable from './QuestionnaireTable';
import QuestionnaireCreateDialog from "./QuestionnaireCreateDialog";

const QuestionnaireContainer = ({serverUrl}) => {

        let [qs, setQuestionnaires] = useState([]);

        useEffect(() => {
            const readAll = async () => {
                const response = await fetch(serverUrl);
                const qs = await response.json();
                setQuestionnaires(qs)
            };
            readAll().catch(error => console.error(error))
        }, [serverUrl]);

        const create = async questionnaire => {
            const request = new Request(serverUrl, {
                method: 'POST',
                headers: new Headers({
                    'Content-Type': 'application/json; charset=utf-8'
                }),
                body: JSON.stringify(questionnaire)
            });
            try {
                const response = await fetch(request);
                if (!response.ok) {
                    console.log('Status Code: ' + response.status);
                } else {
                    const q = await response.json();
                    setQuestionnaires(_.concat(qs, q))
                }
            } catch (error) {
                console.error(error)
            }
        };

        const save = async questionnaire => {
            const request = new Request(serverUrl + '/' + questionnaire.id, {
                method: 'PUT',
                headers: new Headers({
                    'Content-Type': 'application/json; charset=utf-8'
                }),
                body: JSON.stringify(questionnaire)
            });
            console.log(request);
            try {
                const response = await fetch(request);
                if (!response.ok) {
                    console.log('Status Code: ' + response.status);
                } else {
                    setQuestionnaires(_.map(qs, q => q.id === questionnaire.id ? questionnaire : q))
                }
            } catch (error) {
                console.error(error);
            }
        };

        const remove = async id => {
            const request = new Request(serverUrl + '/' + id, {
                method: 'DELETE',
            });
            try {
                const response = await fetch(request);
                if (!response.ok) {
                    console.log('Status Code: ' + response.status);
                } else {
                    setQuestionnaires(_.reject(qs, { id: id }))
                }
            } catch (error) {
                console.error(error);
            }
        };

        return (
            <div>
                <QuestionnaireCreateDialog create={create}/>
                <h3>Questionnaires</h3>
                <QuestionnaireTable qs={qs}
                                    save={save}
                                    remove={remove}/>
            </div>
        );
    }
;


export default QuestionnaireContainer;