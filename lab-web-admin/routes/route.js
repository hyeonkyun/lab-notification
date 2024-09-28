const express = require('express');
const router = express.Router();
const passport = require('passport');
const accountController = require("../controller/accountController");

router.get('/', function (req, res) {
    res.render('login');
});

router.get('/signup', function (req, res) {
    res.render('signup');
});

router.post('/createAccount', accountController.craeteAccount);

router.post('/searchAccount', accountController.searchAccount);

router.post('/searchAccountList', accountController.searchAccountList);

router.get('/login', function (req, res) {
    res.render('login', {messages:req.flash('loginMessage')});
});

router.post('/login', passport.authenticate('login', {
    successRedirect : '/main',
    failureRedirect : '/login',
    failureFlash: true
}));

router.get('/main', isLoggedIn, function (req, res) {    
    //res.render('main', {index : 'main', user: req.user});
    res.render('message_send', {index : 'main', user: req.user});
});

router.get('/account', isLoggedIn, function (req, res) {    
    res.render('account', {index : 'account', user: req.user});
});

router.get('/message/send', isLoggedIn, function (req, res) {    
    res.render('message_send', {index : 'account', user: req.user});
});


function isLoggedIn(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    } else {
        console.error('req.isAuthenticated() = flase');
        res.redirect('/');
    }
}

module.exports = router;
