package org.kity.register;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.kity.dbconnection.DBVariables;
import org.kity.dbconnection.DbUtils;


public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_acc = "";
		String user_password = "";
		String user_name = "";
		String user_id = "";

		if(request.getParameter("user_acc") != null){
			user_acc = request.getParameter("user_acc");
		}
		if(request.getParameter("user_password") != null){
			user_password = request.getParameter("user_password");
		}
		if(request.getParameter("user_name") != null){
			user_name = request.getParameter("user_name");
		}

		user_id = getUserID(); 
				
		String query = "insert into " + DBVariables.user_table + "(user_name,user_acc,user_password,user_id) values ('"+user_name+"','"+user_acc+"','"+user_password+"','"+user_id+"')"; 
		JSONObject responseJson =  null;
		
		try {
			responseJson =  new JSONObject();
			responseJson.put("method", "USER_REGISTER");
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
	private String getUserID() {
		return "USER"+new Random().nextInt(9999)+System.currentTimeMillis()/1000;	
//		return null;
	}

}
