package com.SkyBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDao {
	public ResultSet getTransactionsByAccountId(int accountId){
		ResultSet transactionResult = null;
		
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "SELECT * FROM DTUGRP01.MONEY_TRANSACTION WHERE (ACCOUNT_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, accountId);
			transactionResult = stmt.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return transactionResult;
	}
	
	public boolean insertTransaction(int account1, int account2, double amount, String description, String currency){
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		String SQL = "INSERT INTO DTUGRP01.TRANSACTION"
				+ "(ACCOUNT_ID, AMOUNT, CURRENCY_ID, TRANSACTION_TYPE, DESCRIPTION) VALUES"
				+ "(?,?,?,?,?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt =  db2Conn.prepareStatement(SQL);
			stmt.setInt(1, account1);
			stmt.setDouble(2, amount);
			stmt.setString(3, currency);
			stmt.setInt(4, TransactionType.WITHDRAW.hashCode());
			stmt.setString(5, description);
			stmt.executeUpdate();
			
			PreparedStatement stmt2 =  db2Conn.prepareStatement(SQL);
			stmt2.setInt(1, account1);
			stmt2.setDouble(2, amount);
			stmt2.setString(3, currency);
			stmt2.setInt(4, TransactionType.DEPOSIT.hashCode());
			stmt2.setString(5, description);
			stmt2.executeUpdate();
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
