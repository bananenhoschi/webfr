"use strict"

const log4js = require('log4js')
const Questionnaire = require('../domain/questionnaire')

// Create a logger
const logger = log4js.getLogger('controller')

/*
 * Returns all questionnaires
 * HTTP-GET to '/questionnaires'
 */
exports.findAll = (req, res) => {
    Questionnaire.find((err, questionnaires) => {
        if (err) {
            return res.status(400).send('database error')
        }
        logger.debug(`Found ${questionnaires.length} questionnaires`)
        res.status(200).json(questionnaires)
    })
}

/*
 * Returns a given questionnaire
 * HTTP-GET to '/questionnaires/{id}'
 */
exports.findById = (req, res) => {
    Questionnaire.findById(req.params.id, (err, questionnaire) => {
        if (err) {
            return res.status(400).send('database error')
        }
        logger.debug(`Found questionnaire with id "${questionnaire.id}"`)
        res.status(200).json(questionnaire)
    })
}

/*
 * Creates a new questionnaire
 * HTTP-POST to '/questionnaires'
 */
exports.create = (req, res) => {
    // Create a new instance of the Questionnaire model
    let questionnaire = new Questionnaire()
    questionnaire.title = req.body.title
    questionnaire.description = req.body.description

    // Save the questionnaire and check for errors
    questionnaire.save((err, questionnaireCreated) => {
        if (err) {
            logger.error(`Could not create new questionnaire`)
            res.status(412).send('database error')
        } else {
            logger.debug(`Successfully created questionnaire with id "${questionnaire.id}"`)
            res.status(201).json(questionnaireCreated)
        }
    })
}

/*
 * Updates a given questionnaire
 * HTTP-PUT to to '/questionnaires/{id}'
 */
exports.update = (req, res) => {
    Questionnaire.findById(req.params.id, (err, questionnaire) => {
        if (err) {
            logger.error(`Could not update questionnaire with id "${req.params.id}"`)
            return res.status(404).send('database error')
        }
        questionnaire.title = req.body.title
        questionnaire.description = req.body.description

        // Update the questionnaire and check for errors
        questionnaire.save(err => {
            if (err) {
                return res.status(404).send('database error')
            }
            logger.debug(`Successfully updated questionnaire with id "${questionnaire.id}"`)
            res.status(200).json(questionnaire)
        })
    })
}

/*
 * Deletes a given questionnaire
 * HTTP-DELETE to '/questionnaires/{id}'
 */
exports.delete = (req, res) => {
    Questionnaire.deleteOne({ _id: req.params.id }, err => {
        if (err) {
            logger.error(`Could not delete questionnaire with id "${req.params.id}"`)
            return res.status(404).send('database error')
        }
        logger.debug(`Successfully deleted questionnaire with id "${req.params.id}"`)
        res.status(200).send()
    })
}