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

router.get('/login', function (req, res) {
    res.render('login', {messages:req.flash('loginMessage')});
});

router.post('/login', passport.authenticate('login', {
    successRedirect : '/main',
    failureRedirect : '/login',
    failureFlash: true
}));

// router.get('/logout', function(req, res){
// 	let userName = "NONAME";
// 	let projectId = 0;
// 	if(req.user && req.user.USER_NAME) {
// 		userName = req.user.USER_NAME;
// 		projectId = req.user.PROJECT_ID;
//     }
	
// 	db.insertAuthAccessLogData({ USER_NAME : userName, AUTH_ACTION : 'logout', AUTH_RESULT : 'success', AUTH_RESPONSE : '로그아웃 성공했습니다', PROJECT_ID: projectId });
// 	req.logout((err) => {
// 		if (err) { return next(err); }
// 		req.session.destroy();
// 		res.redirect('/login');
// 	});
// });

router.get('/main', isLoggedIn, function (req, res) {    
    res.render('main', {index : 'main', user: req.user});
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
