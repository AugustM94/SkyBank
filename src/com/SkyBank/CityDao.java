package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDao {
	public ResultSet getCityById(int cityId){
		ResultSet cityResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.CITY WHERE (CITY_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, cityId);
			cityResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return cityResult;
	}
	
	public ResultSet getCity(String cityName){
		ResultSet cityResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.CITY WHERE (CITY_NAME) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, cityName);
			cityResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return cityResult;
	}
	
	public boolean insertCity(String cityName, String zipNumber, String country){
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.CITY"
				+ "(COUNTRY, CITY_NAME, POSTAL_CODE) VALUES"
				+ "(?,?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, country);
			stmt.setString(2, cityName);
			stmt.setString(3, zipNumber);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
