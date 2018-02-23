package com.csx.hackathon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.json.simple.JSONArray;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.csx.hackathon.RDSService;
import com.csx.hackathon.util.JSONFormatter;

public class LambdaFunctionHandler implements RequestStreamHandler {

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		RDSService rdsSvc = new RDSService();
		AWSResultService ars = new AWSResultService();
		JSONFormatter json = new JSONFormatter();

		JSONArray result = null;
		String writeResult = null;
		Object search = null;

		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");

		try {
			search = json.getJSONObjectFromInputStream(input).get("body");
			result = rdsSvc.getLocoGPSResults(search.toString(), context);
			writeResult = ars.formatAWSResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer.write(writeResult);
		writer.close();
	}

}
