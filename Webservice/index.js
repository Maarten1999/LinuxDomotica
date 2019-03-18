// FileName: index.js

// Import express
var express = require('express')
var router = express.Router;
var apiRoutes = require("./api-routes")

// Initialize the app
var app = express();
app.use('/api', apiRoutes)
// Setup server port
var port = process.env.PORT || 8080;


// Send message for default URL
app.get('/', (req, res) => res.send('Linux Domotica REST service.'));


// Launch app to listen to specified port
app.listen(port, function () {
     console.log("Running REST on port " + port);
});