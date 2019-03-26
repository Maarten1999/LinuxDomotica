'use strict';

var Light = require('../model/appModel.js');

exports.list_all_lights = function(req, res) {
    Light.getAllLights(function(err, light) {
        console.log('controller');
        
        if (err)
        {
            res.send(err);
        }
        console.log('res', light);
        res.send(light);
    });
};

exports.get_light = function(req, res) {

    Light.getLightById(req.params.id, function(err, light) {
        if (err)
            res.send(err);
        res.json(light);
    });
};

exports.update_light = function(req, res) {
    Light.updateById(req.params.id, new Light(req.body), function(err, light) {
        if (err)
            res.send(err);
        res.json(light);
    });
};