"use strict";

const dispatcher = require('express').Router();

const index_controller = require('./index-controller')
const questionnaire_controller = require('./questionnaire-controller')

dispatcher.route('/').get(index_controller.index)
dispatcher.route('/questionnaires').get(questionnaire_controller.findAll)
dispatcher.route('/questionnaires/:id').get(questionnaire_controller.findById)
dispatcher.route('/questionnaires').post(questionnaire_controller.create)
dispatcher.route('/questionnaires/:id').put(questionnaire_controller.update)
dispatcher.route('/questionnaires/:id').delete(questionnaire_controller.delete)

module.exports = dispatcher;