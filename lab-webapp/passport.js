const LocalStrategy = require('passport-local').Strategy;
const db = require('./models/dbquery');

module.exports = function( passport ) {
    passport.serializeUser(function(user, done) {
        done(null, user);
    });

    passport.deserializeUser(function(user, done) {
        done(null, user);
    });

    passport.use('login', new LocalStrategy({
            usernameField : 'userId',
            passwordField : 'password',
            passReqToCallback: true
        }, function(req, userId, password, done) {            
            db.checkUserAuth(req, userId, password, done);            
        })
    );
};