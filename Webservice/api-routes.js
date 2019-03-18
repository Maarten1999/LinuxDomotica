// Filename: api-routes.js
// Initialize express router
var router = require('express').Router();

// Set default API response
router.get('/lights', function (req, res) {
    res.json({
       success: true,
       lights: [
           {
                id: 1,
                name: 'light1',
                color: 'red',
                on: false
           },
           {
               id: 2,
               name: 'light2',
               color: 'green',
               on: true
           }
       ],
    });
});

// Database nodig
router.get('/lights/:id', function(req, res) {
    res.json({
       success: true,
       light: 
       {
            id: req.params.id,
            name: 'light1',
            color: 'red',
            on: false
       }
    });
});

router.put('lights/:id/:on', function(req, res) {
    var id = req.id;
    var on = req.on;
    
    res.json({
       success: true,
       message: 'saved'
    });
});

// Export API routes
module.exports = router;