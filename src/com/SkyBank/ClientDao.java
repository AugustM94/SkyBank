package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {
	public ResultSet getClient(String username){
		ResultSet client = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.CLIENT WHERE (USER_NAME) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, username);
			client = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return client;
	}
	
	public ResultSet getClientById(int clientId){
		ResultSet client = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.CLIENT WHERE (CLIENT_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, clientId);
			client = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return client;
	}
	
	public boolean insertClient(String username, int addressId, String firstName, String lastName, int phone, int cpr){
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.CLIENT"
				+ "(USER_NAME, FIRST_NAME, LAST_NAME, PHONE, CPR, ADDRESS_ID) VALUES"
				+ "(?,?,?,?,?,?)";
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setInt(4, phone);
			stmt.setInt(5, cpr);
			stmt.setInt(6, addressId);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return false;
	}
}
