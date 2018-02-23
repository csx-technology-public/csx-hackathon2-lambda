
package com.csx.hackathon.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFormatter {

	public static JSONObject getJSONObjectFromString(String is)
			throws UnsupportedEncodingException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(is);
		return jsonObject;
	}

	public static Object getObjectFromString(String is) 
			throws UnsupportedEncodingException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object json = jsonParser.parse(is);
		return json;
	}
	
	public JSONObject getJSONObjectFromInputStream(InputStream is)
			throws UnsupportedEncodingException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(is, "UTF-8"));
		return jsonObject;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray convertToJSON(ResultSet resultSet) throws Exception {
		JSONArray jsonArray = new JSONArray();
		int total_rows = resultSet.getMetaData().getColumnCount();
		while (resultSet.next()) {
			JSONObject obj = new JSONObject();
			for (int i = 0; i < total_rows; i++) {
				obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
			}
			jsonArray.add(obj);
		}
		return jsonArray;
	}
}
