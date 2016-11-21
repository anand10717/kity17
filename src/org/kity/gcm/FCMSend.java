package org.kity.gcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class FCMSend {
	public static void sendData(Message message,String token_id) throws IOException, JSONException {
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(
			"https://fcm.googleapis.com/fcm/send");
		
		String serverKey = "AIzaSyBCCC3eOYPqDI2fAuswYLMTMR0We3H-2Ek";  

		System.out.println(token_id);
		JSONObject json = new JSONObject();
		json.put("to",token_id);
		JSONObject info = new JSONObject();
		
		info.put("title", message.getTitle());   
		info.put("body", message.getBody());
		
		json.put("notification", info);
	//	json.put("data", message.getData());
		StringEntity input = new StringEntity(json.toString());
		input.setContentType("application/json");
		
		postRequest.setEntity(input);
		
		postRequest.addHeader("Authorization","key="+serverKey);
		postRequest.addHeader("Content-Type","application/json");
		
		HttpResponse response = httpClient.execute(postRequest);

		
		if (response.getStatusLine().getStatusCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));

		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		httpClient.getConnectionManager().shutdown();
	
	/*	String serverKey = "AIzaSyBCCC3eOYPqDI2fAuswYLMTMR0We3H-2Ek";  
		
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization","key="+serverKey);
		conn.setRequestProperty("Content-Type","application/json");

		
		JSONObject json = new JSONObject();
		json.put("to",token_id);
		JSONObject info = new JSONObject();
		
		info.put("title", message.getTitle());   
		info.put("body", message.getBody());
		
		json.put("notification", info);
	//	json.put("data", message.getData());
	
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		
		BufferedInputStream inputStreamReader = new BufferedInputStream(conn.getInputStream());
		byte[] b = null;
		while(inputStreamReader.read(b)!=0){
			System.out.println(b);
		}
	*/
		}
}
