package org.kity.register;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.kity.dbconnection.DBVariables;
import org.kity.dbconnection.DbUtils;


public class DeviceRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String token_id = "";
		String device_type = "";
		String user_id = "";

		if(request.getParameter("token_id") != null){
			token_id = request.getParameter("token_id");
		}
		if(request.getParameter("device_type") != null){
			device_type = request.getParameter("device_type");
		}
		if(request.getParameter("user_id") != null){
			user_id = request.getParameter("user_id");
		}

		String query = "insert into " + DBVariables.device_table + "(user_id,token_id,device_type) values ('"+user_id+"','"+token_id+"','"+device_type+"')"; 
		JSONObject responseJson =  null;

		try {
			responseJson =  new JSONObject();
			responseJson.put("method", "DEVICE_REGISTER");
			if(DbUtils.update(query)){
				responseJson.put("result", "true");
			}else{
				responseJson.put("result", "false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.getWriter().append(responseJson.toString());
	}

}
