// FileName: index.js

// Import express
var sql = require('mysql');
var express = require('express');
var router = express.Router;
var apiRoutes = require("./api-routes");
var bodyParser = require('body-parser');

// Initialize the app
var app = express();

app.use(bodyParser.json());

app.use(function (req, res, next) {
    //Enabling CORS 
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
    next();
});


 /*var dbConfig = {
    user: 'u22038p18228_domotica',
    password: 'Zuccc',
    server: 'anveon.nl',
    database:'u22038p18228_lights'
};

//Function to connect to database and execute query
var  executeQuery = function(res, query){             
     sql.connect(dbConfig, function (err) {
         if (err) {   
                     console.log("Error while connecting database :- " + err);
                     res.send(err);
                  }
                  else {
                         // create Request object
                         var request = new sql.Request();
                         // query to the database
                         request.query(query, function (err, res) {
                           if (err) {
                                      console.log("Error while querying database :- " + err);
                                      res.send(err);
                                     }
                                     else {
                                       res.send(res);
                                            }
                               });
                       }
      });           
}
*/

var connection = sql.createConnection({
        host: 'anveon.nl',
        user: 'u22038p18228_domotica',
        password: 'Zuccc',
        database: 'u22038p18228_lights'
});

connection.connect(function(err) {
    if (err) throw err;
    console.log("Connected");
});

//GET API
app.get("/api/lights", function(req , res){
    
    var request = new sql.Request();
    // query to the database
    request.query("SELECT * FROM lights", function (err, res) {
       if (err) {
                  console.log("Error while querying database :- " + err);
                  res.send(err);
                 }
                 else {
                   res.send(res);
                        }
           });
});


//GET API
 app.get("/api/light/:id", function(req , res){
        var id = req.body.id;
        
        var request = new sql.Request();
    // query to the database
    request.query("SELECT light FROM lights WHERE id = ?", id, function (err, res) {
       if (err) {
                  console.log("Error while querying database :- " + err);
                  res.send(err);
                 }
                 else {
                   res.send(res);
                        }
           });
});

//GET API
 app.put("/api/light/:id", function(req , res){
        var status = req.body.status;
        var id = req.body.id;
        var request = new sql.Request();
    // query to the database
    request.query("UPDATE lights SET light = ? WHERE id = ?", [status, id], function (err, res) {
       if (err) {
                  console.log("Error while querying database :- " + err);
                  res.send(err);
                 }
                 else {
                   res.send(res);
                        }
           });
});


/*var connection = mysql.createConnection({
        host: 'anveon.nl',
        user: 'u22038p18228_domotica',
        password: 'Zuccc',
        database: 'u22038p18228_lights'
});

connection.connect(function(err) {
    if (err) throw err;
    console.log("Connected");
});

    */
/*app.use(function(req, res, next) {
    res.locals.connection = mysql.createConnection({
        host: 'anveon.nl',
        user: 'u22038p18228_domotica',
        password: 'Zuccc',
        database: 'u22038p18228_lights'
    });
    
    res.locals.connect();
    next();
});*/

//app.use('/api', apiRoutes)

// Setup server port



//Setting up server
 var server = app.listen(process.env.PORT || 8080, function () {
    var port = server.address().port;
    console.log("App now running on port", port);
 });