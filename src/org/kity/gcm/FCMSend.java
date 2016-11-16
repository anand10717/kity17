package org.kity.gcm;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class FCMSend {
	public static void sendData(Message message,String token_id) throws IOException, JSONException {
	
		String serverKey = "AIzaSyAZ6bghbqwyPDru2m7iI3S8kgNRS4fg36o";  
		
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
		json.put("data", message.getData());
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		conn.getInputStream();
		
		}
}
