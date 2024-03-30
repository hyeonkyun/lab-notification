const bcrypt = require('bcrypt');
var db = require('../models/datasource');
var util = require("util");
const moment = require('moment');
require('moment-timezone');
moment.tz.setDefault("Asia/Seoul");

// search, create, remove, modify
exports.createAccount = function(param, callback) {    
    const hash = bcrypt.hashSync(param.password, 10);
    const userId = param.userId;
    let sql = util.format(`INSERT INTO tb_account 
                           ( USER_ID, PASSWORD, STATUS_CD, INS_USER, INS_DT, UPD_USER, UPD_DT )
                           VALUES('%s', '%s', '1', '%s', now(), '%s', now())`, userId, hash, userId, userId);
    db.executeSql(sql, function(rs1, err) {
		return callback(rs1, err);
	});
};

exports.searchAccount = function(param, callback) { 
    const userId = param.userId;
    let sql = util.format(`SELECT USER_ID, PASSWORD, STATUS_CD
	                         FROM tb_account
							 WHERE USER_ID = '%s'`, userId);	
    db.executeSql(sql, function(rs1, err) {
		return callback(rs1, err);
	});
};

exports.checkUserAuth = function (req, userId, password, done) {	
	let sql = util.format(`SELECT USER_ID, PASSWORD, STATUS_CD
							 FROM tb_account 
							WHERE USER_ID = '%s'`, userId);
	db.executeSql(sql, function(rows, err) {
		if(err) {
			return done(err);
		} else if(rows.length == 0){
			return done(null, false, {message : req.flash('loginMessage', '계정이 존재하지 않습니다.')});
        } else {
			if(rows[0].STATUS_CD === '4') { // 상태코드 (0:요청전, 1:등록요청, 2:등록취소, 3:반려, 4:등록승인)
				if(bcrypt.compareSync(password, rows[0].PASSWORD)) {
					return done(null, rows[0]);						
				} else {
					return done(null, false, {messages : req.flash('loginMessage', '비밀번호가 일치하지 않습니다.')});					
				}
			} else {				
				return done(null, false, {messages : req.flash('loginMessage', '미승인 사용자입니다. 관리자에게 문의하십시오.')});
			}
		}
	});
};
