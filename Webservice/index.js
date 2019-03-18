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

/*app.use('/lights', function(req, res, next) {
    res.json({
       success: true,
       lights: [
           {
                id: 1,
                name: 'light1',
                on: false
           },
           {
               id: 2,
               name: 'light2',
               on: true
           }
       ],
    });
});*/



// Send message for default URL
app.get('/', (req, res) => res.send('Maarten & Dion REST Service'));


// Launch app to listen to specified port
app.listen(port, function () {
     console.log("Running REST on port " + port);
});