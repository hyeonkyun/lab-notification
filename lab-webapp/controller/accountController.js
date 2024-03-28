const db = require("../models/dbquery");

exports.craeteAccount = function (req, res) {

    const responseJSON = {
        responseCode: '1000',
        responseMessage: 'OK'
      };

    db.createAccount(req.body, function (data, err) {
        if(err){
            console.log("[ERR : craeteAccount]" + err);
        } else {
            res.writeHead(200, {'Content-Type': 'application/json'});
            res.end(JSON.stringify(responseJSON));
        }
    });
}