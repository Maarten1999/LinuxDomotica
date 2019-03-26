'user strict';
var sql = require('./db.js');

var Light = function(light) {
    this.name = light.name;
    this.color = light.color;
    this.status = light.status;
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
    sql.query("SELECT light FROM lights WHERE id = ?", id, function(err, res) {
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
    sql.query("UPDATE lights SET light = ? WHERE id = ?", [light.status, id], function(err, res) {
        if (err)
        {
            console.log("Error: ", err);
            result(null, err);
        }
        else
        {
            return(null, res);
        }
    });
};

module.exports= Light;