package com.se.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {

	public static Connection getConnect() throws Exception{
		String user = "spring01";
		String password = "spring01";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
	
	/*public static void main(String[] args) {
		try {
			Connection con= DBConnector.getConnect();
			System.out.println(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void disConnect(PreparedStatement st,Connection con) throws Exception{
		st.close();
		con.close();
	}
	
	public static void disConnect(ResultSet rs,PreparedStatement st,Connection con) throws Exception{
		rs.close();
		st.close();
		con.close();
	}
}
