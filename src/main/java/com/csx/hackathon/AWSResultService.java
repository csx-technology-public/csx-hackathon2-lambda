package com.csx.hackathon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AWSResultService {
	final String STATUSCODE = "200";

	@SuppressWarnings("unchecked")
	public String formatAWSResult(JSONArray jsonResult) throws Exception {
		JSONObject result = new JSONObject();
		JSONObject headerJson = new JSONObject();

		result.put("statusCode", STATUSCODE);
		result.put("headers", headerJson);
		result.put("body", jsonResult.toJSONString());

		return result.toJSONString();
	}
}
