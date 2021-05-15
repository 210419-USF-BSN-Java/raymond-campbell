package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;
	
	public static Connection getConnectionFromEnv()
	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dbName = "postgres";
		String RDS_HOSTNAME = "database-1.c9r6curxr2qu.us-east-2.rds.amazonaws.com";
		String RDS_USERNAME = "postgres";
		String password = "password";
		String jdbcUrl = "jdbc:postgresql://" + RDS_HOSTNAME + ":" + 5432 + "/" + dbName + "?user=" + RDS_USERNAME 
				        + "&password=" + password;
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(jdbcUrl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
