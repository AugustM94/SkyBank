package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
	public ResultSet getAccountByClientId(int clientId){
		ResultSet accountResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.ACCOUNT WHERE (CLIENT_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, clientId);
			accountResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return accountResult;
	}
	
	public boolean insertAccount(String accountName, int regNumber , int accountNumber, int clientId){
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.ACCOUNT"
				+ "(ACCOUNT_NAME, REG_NO, ACCOUNT_NO, CURRENCY_ID, CLIENT_ID) VALUES"
				+ "(?,?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, accountName);
			stmt.setInt(2, regNumber);
			stmt.setInt(3, accountNumber);
			stmt.setInt(4, 1);
			stmt.setInt(5, clientId);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
