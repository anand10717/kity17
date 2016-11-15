package org.kity;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kity.dbconnection.DbConf;


public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "";
		name = request.getParameter("name");
		try {
			Statement stmt = DbConf.getInstance().createStatement();
	         String sql = "CREATE TABLE COMPANY " +
	                      "(ID INT PRIMARY KEY     NOT NULL," +
	                      " NAME           TEXT    NOT NULL, " +
	                      " AGE            INT     NOT NULL, " +
	                      " ADDRESS        CHAR(50), " +
	                      " SALARY         REAL)";
	         stmt.executeUpdate(sql);
	        
	         
	          sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
	                 + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
	          stmt.executeUpdate(sql);
	          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
	          while ( rs.next() ) {
	             int id = rs.getInt("id");
	               name = rs.getString("name");
	             int age  = rs.getInt("age");
	             String  address = rs.getString("address");
	             float salary = rs.getFloat("salary");
	             System.out.println( "ID = " + id );
	             System.out.println( "NAME = " + name );
	             System.out.println( "AGE = " + age );
	             System.out.println( "ADDRESS = " + address );
	             System.out.println( "SALARY = " + salary );
	             System.out.println();
	          }
	          rs.close();
	          stmt.close();

	       //  c.close();
			//DbConf.getInstance().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().append("Hello" + name);
	}

	

}
