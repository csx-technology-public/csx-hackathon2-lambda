package com.csx.hackathon;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Service {

	public String handle(Context context) {
		String bucket = "csx-hackathon-2";
		String file = "data.json";

		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
			String fileString = s3Client.getObjectAsString(bucket, file);

			return fileString;
		} catch (Exception e) {
			e.printStackTrace();
			context.getLogger().log(String.format("Error getting object %s from bucket %s. Make sure they exist and"
					+ " your bucket is in the same region as this function.", file, bucket));
			throw e;
		}
	}

}
