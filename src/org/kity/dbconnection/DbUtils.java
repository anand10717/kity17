package org.kity.dbconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	
	
	public static boolean update(String query) throws SQLException{
		Statement stmt = DbConf.getInstance().createStatement();
		if(stmt.executeUpdate(query)>0){
			return true;
		}
		stmt.close();
		return false;
	}
	public static ResultSet execute(String query) throws SQLException{
		Statement stmt = DbConf.getInstance().createStatement();
		ResultSet rs = stmt.executeQuery(query);
		stmt.close();
		return rs;
	}
	/*public static boolean create(String query) throws SQLException{
		Statement stmt = DbConf.getInstance().createStatement();
		if(stmt.executeUpdate(query)>0){
			return true;
		}
		stmt.close();
		return false;
	}*/	
}
