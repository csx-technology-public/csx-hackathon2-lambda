package com.csx.hackathon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;

public class ConnectionService {
	public Connection getRemoteConnection(Context context) {
		try {
			Class.forName("org.postgresql.Driver");
			String jdbcUrl = "jdbc:postgresql://hackathon-loco-gps.cobhetvbklae.us-east-2.rds.amazonaws.com:5432/hackathon?user=xxx&password=xxx";
			Connection con = DriverManager.getConnection(jdbcUrl);
			context.getLogger().log("Remote connection successful.");
			return con;
		} catch (ClassNotFoundException e) {
			context.getLogger().log(e.toString());
		} catch (SQLException e) {
			context.getLogger().log(e.toString());
		}
		return null;
	}
}
