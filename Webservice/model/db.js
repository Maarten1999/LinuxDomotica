'user strict';

var mysql = require('mysql');

var connection = mysql.createConnection({
        host: 'anveon.nl',
        user: 'u22038p18228_domotica',
        password: 'Zuccc',
        database: 'u22038p18228_lights'
});

connection.connect(function(err) {
    if (err) console.log("Failed to connect");
    else console.log("Connected");
});

module.exports = connection;