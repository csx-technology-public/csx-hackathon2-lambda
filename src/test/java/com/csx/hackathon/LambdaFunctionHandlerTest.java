package com.csx.hackathon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

	private static InputStream input;
	private static OutputStream outputStream;

	@BeforeClass
	public static void createInput() throws IOException {
		// TODO: set up your sample input object here.
		input = null;
	}

	public static void createOutput() throws IOException {
		// TODO: set up your sample input object here.
		outputStream = null;
	}

	private Context createContext() {
		TestContext ctx = new TestContext();

		// TODO: customize your context here if needed.
		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	@Test
	public void testLambdaFunctionHandler() throws IOException {
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		Context ctx = createContext();

		handler.handleRequest(input, outputStream, ctx);

		// TODO: validate output here if needed.
		//Assert.assertEquals("Hello from Lambda!", output);
	}
}
