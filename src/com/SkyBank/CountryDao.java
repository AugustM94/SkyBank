package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDao {
	
	public ResultSet getContry(String country){
		ResultSet countryResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.COUNTRY WHERE (COUNTRY) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, country);
			countryResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return countryResult;
	}
	
	public boolean insertCountry(String countryName){
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.COUNTRY"
				+ "(COUNTRY) VALUES"
				+ "(?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, countryName);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
