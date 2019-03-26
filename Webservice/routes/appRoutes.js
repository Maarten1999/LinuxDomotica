const express = require('express');
const router = express.Router();
var functionList = require('../controller/appController');

router.route('/lights')
    .get(functionList.list_all_lights);

router.route('/lights/:id')
.get(functionList.get_light)
.put(functionList.update_light);

module.exports = router;

  // todoList Routes
  
   
   