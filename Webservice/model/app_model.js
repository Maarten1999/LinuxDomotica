'user strict';
var sql = require('./db.js');
var pythonShell = require('python-shell');

var Light = function(light) {
    this.name = light.name;
    this.color = light.color;
    this.status = light.status;
};

Light.createLight = function createUser(newLight, result) {    
        sql.query("INSERT INTO lights set ?", newLight, function (err, res) {
                
                if(err) {
                    console.log("error: ", err);
                    result(err, null);
                }
                else{
                    console.log(res.insertId);
                    result(null, res.insertId);
                }
            });     
};
Light.getAllLights = function getAllLights(result) {
    sql.query("SELECT * FROM lights", function(err, res) {
        if (err)
        {
            console.log("Error: ", err);
            result(null, err);
        }
        else
        {
            console.log('lights : ', res);
            result(null, res);
        }
    });
};

Light.getLightById = function(id, result) {
    sql.query("SELECT id,name,color,status FROM lights WHERE id = ?", id, function(err, res) {
        if (err)
        {
            console.log("Error: ", err);
            result(err, null);
        }
        else
        {
            result(null, res);
        }
    });
};
Light.updateById = function(id, light, result)
{
    console.log(light.name);
    sql.query("UPDATE lights SET status = ? WHERE id = ?", [light.status, id], function(err, res) {
        if (err)
        {
            console.log("Error: ", err);
            result(null, err);
        }
        else
        {
            let options = {
                args: [light.status, id]
            };
            pythonShell.PythonShell.run('gpio.py', options, function(err, results) {
                if (!err) console.log("Succesfull called python script");
            });
            result(null, res);
        }
    });
};

Light.remove = function(id, result){
     sql.query("DELETE FROM lights WHERE id = ?", [id], function (err, res) {

                if(err) {
                    console.log("error: ", err);
                    result(null, err);
                }
                else{
               
                 result(null, res);
                }
            }); 
};

module.exports= Light;