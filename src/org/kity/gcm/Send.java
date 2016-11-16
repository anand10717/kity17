package org.kity.gcm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiver_token_id = "";
		String sender_user_id = "";
		String receiver_user_id = "";

		if(request.getParameter("receiver_token_id") != null){
			receiver_token_id = request.getParameter("receiver_token_id");
		}
		if(request.getParameter("sender_user_id") != null){
			sender_user_id = request.getParameter("sender_user_id");
		}
		if(request.getParameter("receiver_user_id") != null){
			receiver_user_id = request.getParameter("receiver_user_id");
		}

		Message message= new Message("Test", "Test");
		JSONObject responseJson = new JSONObject(); 
		try {
			
			FCMSend.sendData(message, receiver_token_id);
			responseJson.put("result", "sent");
			responseJson.put("method", "FCM_SEND");
			} catch (JSONException e) {
				
			e.printStackTrace();
		}
		response.getWriter().append(responseJson.toString());
	}
}
