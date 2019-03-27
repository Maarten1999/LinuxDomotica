const express = require('express');
const router = express.Router();
var functionList = require('../controller/app_controller');

router.route('/lights')
    .get(functionList.list_all_lights)
    .post(functionList.create_light);

router.route('/lights/:id')
.get(functionList.get_light)
.put(functionList.update_light)         // Update light status
.delete(functionList.delete_light);

module.exports = router;