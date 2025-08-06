package com.tfl.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import com.tfl.constants.OracleDatabaseConstants;
import com.tfl.constants.MysqlDatabaseConstants;


public class ConnectionUtils {

	static Connection connection_mysql;
	static Connection connection_oracle;

	public static Connection getOracleConnection() {
		try {
			Class.forName(OracleDatabaseConstants.oracleDriverClassName);
			connection_oracle = DriverManager.getConnection(OracleDatabaseConstants.databaseUrl, OracleDatabaseConstants.databaseUserName,
			OracleDatabaseConstants.databasePassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return connection_oracle;
	}

	public static Connection getMysqlConnection() {
		try {
			Class.forName(MysqlDatabaseConstants.mySqlDriverClassName);
			connection_mysql = DriverManager.getConnection(MysqlDatabaseConstants.databaseUrl, MysqlDatabaseConstants.databaseUserName,
			MysqlDatabaseConstants.databasePassword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return connection_mysql;
	}
}
