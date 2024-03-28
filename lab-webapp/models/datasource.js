const mysql = require('mysql');
let for_query_connection;

const db_config = {
	host: '127.0.0.1',
	user: 'labuser',
	password: 'labuser23!',
	port: 3306,
	database: 'lab',
	connectionLimit: 20,
	pool: {
		max: 20,
		idleTimeoutMillis: 30000
	}
};

let pool = mysql.createPool(db_config);

const db = {
  getConnection : (callback)=>{
    pool.getConnection((err, conn)=>{ 
      if(err) throw err;
      callback(conn);
    });
  }
}

exports.executeSql = function (sql, callback) {
	db.getConnection((conn)=>{
		conn.query(sql,(err,rows)=>{
			console.log("SQL : " + sql);
			if(err) callback(null, err);
			callback(rows);
		});
		conn.release();
	});
};