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
	const reason = param.reason;
    let sql = util.format(`INSERT INTO tb_account 
                           ( USER_ID, PASSWORD, STATUS_CD, REQ_REASON, INS_USER, INS_DT, UPD_USER, UPD_DT )
                           VALUES('%s', '%s', '1', '%s', '%s', now(), '%s', now())`, userId, hash, reason, userId, userId);
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

exports.searchAccountList = function(param, callback) { 
    const userId = param.userId;
	const statusCd = param.statusCd;
	const offset = param.offset;
    let sql = util.format(`SELECT COUNT(*) OVER() AS TOTAL_COUNT,
								  USER_ID, 
								  CASE STATUS_CD 
										WHEN '1' THEN '등록요청'
										WHEN '2' THEN '요청취소'
										WHEN '3' THEN '요청반려'
										WHEN '4' THEN '승인완료'
										ELSE '요청전'
								   END AS STATUS_CD_NM,
									REQ_REASON, DATE_FORMAT(INS_DT, '%Y-%m-%d %H:%i:%s') AS INS_DT
						  	 FROM tb_account
						  	WHERE 1=1 `);
	if (userId !== '') { 
		sql = sql + util.format(`AND USER_ID = '%s' `, userId);
	}

	if (statusCd !== '') {
		sql = sql + util.format(`AND STATUS_CD = '%s' `, statusCd);
	}

	sql = sql + util.format(`LIMIT 10 OFFSET %d `, offset);
	
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
