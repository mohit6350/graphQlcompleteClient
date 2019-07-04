package com.quinnox.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class GraphQLClient {
	public static void main(String[] args) throws Exception{
		HttpClient client = new DefaultHttpClient();
		String queryForOne = "{student(rollNumber:\"0607cs131009\"){ name email rollNumber address } }";
		String queryForAll = "{allStudent{  name  email  rollNumber  address}}";
		String url = "http://localhost:2222/rest/student/getAllStudent";
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(queryForAll);
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		InputStream stream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = reader.readLine();
		while(line != null){
			System.err.println(line);
			line = reader.readLine();
		}
	}
}
