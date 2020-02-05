"use strict";

const log4js = require('log4js');
const logger = log4js.getLogger('controller');

exports.index = (req, res) => {
    logger.debug('index called')
    res.redirect(req.baseUrl + "/questionnaires")
}
