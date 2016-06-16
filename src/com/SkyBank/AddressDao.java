package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {
	public ResultSet getAddressById(int addressId){
		ResultSet addressResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.ADDRESS WHERE (ADDRESS_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, addressId);
			addressResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return addressResult;
	}
	
	public ResultSet getAddress(String streetName, String addressNumber){
		ResultSet addressResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.ADDRESS WHERE (STREET_NAME, STREET_NO) = (?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setString(1, streetName);
			stmt.setString(2, addressNumber);
			addressResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return addressResult;
	}
	
	public boolean insertAddress(String streetName, String addressNumber, int cityId){
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.ADDRESS"
				+ "(CITY_ID, STREET_NAME, STREET_NO) VALUES"
				+ "(?,?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, cityId);
			stmt.setString(2, streetName);
			stmt.setString(3, addressNumber);
			stmt.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
