var express = require('express');
var bodyParser = require('body-parser');
app = express();
//const cors = require('cors');

var appRoutes = require('./routes/app_routes');



//cors
//app.use(cors({origin: 'http://localhost:4200'}));
app.use(express.json());

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use('/', appRoutes);

module.exports = app;