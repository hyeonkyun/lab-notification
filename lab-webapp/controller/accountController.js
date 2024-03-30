const db = require("../models/dbquery");

const responseJSON = {
    responseCode: '1000',
    responseMessage: 'OK',
    responseData: ''
};

exports.craeteAccount = function (req, res) {
    db.createAccount(req.body, function (data, err) {
        if(err){
            console.log("db.createAccount error : " + err);
        } else {
            res.writeHead(200, {'Content-Type': 'application/json'});
            res.end(JSON.stringify(responseJSON));
        }
    });
};

exports.searchAccount = function (req, res) { 
    db.searchAccount(req.body, function (data, err) {
        if(err){
            console.log("db.searchAccount error : " + err);
        } else {
            res.writeHead(200, {'Content-Type': 'application/json'});
            responseJSON.responseData = data;
            console.log(data);
            console.log(JSON.stringify(responseJSON));
            res.end(JSON.stringify(responseJSON));
        }
    });
};
    


