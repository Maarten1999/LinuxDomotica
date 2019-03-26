const mysql = require('mysql');

const mc = mysql.createConnection({
        host: 'anveon.nl',
        user: 'u22038p18228_domotica',
        password: 'Zuccc',
        database: 'u22038p18228_lights'
});

mc.connect();

module.exports = mc;