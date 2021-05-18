package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;
	
	public static Connection getConnectionFromEnv()
	{
		
		String RDS_HOSTNAME = System.getenv("RDS_HOSTNAME");
		String dbName = System.getenv("dbName");
		String RDS_USERNAME = System.getenv("RDS_USERNAME");
		String password = System.getenv("password");
		
//		String url = System.getenv("DB_AWS_URL");
//		String username = System.getenv("DB_USER");
//		String password = System.getenv("DB_PASS");
		
		String jdbcUrl = "jdbc:postgresql://" + RDS_HOSTNAME + ":" + 5432 + "/" + dbName + "?user=" + RDS_USERNAME 
		        + "&password=" + password;

		try {
			if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(jdbcUrl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
