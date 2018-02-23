package com.csx.hackathon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;

import com.csx.hackathon.util.JSONFormatter;
import com.csx.hackathon.ConnectionService;

public class RDSService {

	public JSONArray getLocoGPSResults(String search, Context context) throws Exception {
		ConnectionService connSvc = new ConnectionService();
		Connection conn = connSvc.getRemoteConnection(context);
		ResultSet resultSet = null;
		JSONArray jsonResult = null;
		String where = null;
		String field = null;
		String value = null;
		try {
			Statement readStatement = conn.createStatement();
			if (search != null) {
				JSONObject json = JSONFormatter.getJSONObjectFromString(search);
				Iterator<?> keys = json.keySet().iterator();
				String clause = "";
				while (keys.hasNext()) {
					String key = (String) keys.next();
					field = key.toString().toUpperCase();
					value = json.get(key).toString();
					clause = clause + "\""+ field + "\"='" + value + "'";
					if (keys.hasNext()) {
						clause = clause + " AND ";
					}
				}
				if (field.equals("ALL")) {
					where = "WHERE 1=1";
				} else {
					where = "WHERE " + clause;
				}
			} else {
				where = "";
			}
			resultSet = readStatement.executeQuery("SELECT * from loco_gps " + where + " limit 20000");
			jsonResult = JSONFormatter.convertToJSON(resultSet);
			resultSet.close();
			readStatement.close();
			conn.close();

		} catch (SQLException ex) {
			// Handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
		return jsonResult;
	}
}