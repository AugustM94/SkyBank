package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	public ResultSet getLogin(String username){
		
		ResultSet login = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT USER_NAME FROM DTUGRP01.LOGIN WHERE (USER_NAME) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
				
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, username);
			login = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return login;
	}
	
	public boolean insertLogin(String username, String password){
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.LOGIN"
				+ "(USER_NAME, PASSWORD) VALUES"
				+ "(?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
				
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
}
