package org.kity.dbconnection;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConf {

	private static Connection connection = null;
	
	
	//private static DbConf conf = null;
	
	public static Connection getInstance(){
		if(connection == null){
			getDBConn();
		}
		return connection;
	}
	
	private DbConf()
	{
		
	}
	
	/*private static Connection getConnection() {
		return connection;
	}

	private static void setConnection(Connection connection) {
		DbConf.connection = connection;
	}*/

	private static void getDBConn(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		}

		try {	

			URI dbUri = new URI("postgres://tkmliwaapruamw:XF2bN8pRx1c7bGrWmU5ZHVxPzf@ec2-54-75-230-140.eu-west-1.compute.amazonaws.com:5432/d9hg25jjkrk9ec");

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();


			connection = DriverManager.getConnection(dbUrl,username,password);
		} catch (SQLException e) {	
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

	}
}
