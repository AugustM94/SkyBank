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
		String SQL = "INSERT INTO DTUGRP01.MONEY_TRANSACTION"
				+ "(ACCOUNT_ID, AMOUNT, CURRENCY_ID, TRANSACTION_TYPE, DESCRIPTION) VALUES"
				+ "(?,?,?,?,?)";
		
		String updateAccountBalanceSQL = "UPDATE DTUGRP01.ACCOUNT SET BALANCE = ? WHERE (ACCOUNT_ID) = (?)";
		String balanceSQL = "SELECT BALANCE FROM DTUGRP01.ACCOUNT WHERE (ACCOUNT_ID) = (?)";
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			
			PreparedStatement stmt1 = db2Conn.prepareStatement(balanceSQL);
			stmt1.setInt(1, account1);
			ResultSet balanceSenderResult = stmt1.executeQuery();
			balanceSenderResult.next();
			Double balanceSender = balanceSenderResult.getDouble("BALANCE");
			double newBalanceSender = balanceSender - amount;
			
			if(newBalanceSender > 0){	
				PreparedStatement stmt2 = db2Conn.prepareStatement(SQL);
				stmt2.setInt(1, account2);
				stmt2.setDouble(2, amount);
				stmt2.setString(3, currency);
				System.out.println(TransactionType.DEPOSIT.ordinal());
				stmt2.setInt(4, TransactionType.DEPOSIT.ordinal());
				stmt2.setString(5, description);
				stmt2.executeUpdate();
				
				PreparedStatement stm3 = db2Conn.prepareStatement(SQL);
				stm3.setInt(1, account1);
				stm3.setDouble(2, amount);
				stm3.setString(3, currency);
				stm3.setInt(4, TransactionType.WITHDRAW.ordinal());
				stm3.setString(5, description);
				stm3.executeUpdate();

				PreparedStatement stmt4 = db2Conn.prepareStatement(updateAccountBalanceSQL);
				stmt4.setDouble(1, newBalanceSender);
				stmt4.setDouble(2, account1);
				stmt4.executeUpdate();
				System.out.println("new balance sender: " + newBalanceSender);
				
				PreparedStatement stmt5 = db2Conn.prepareStatement(balanceSQL);
				stmt5.setInt(1, account2);
				ResultSet balanceReceiverResult = stmt5.executeQuery();
				balanceReceiverResult.next();
				Double balanceReciever = balanceReceiverResult.getDouble("BALANCE");
				double newBalanceReciever = balanceReciever + amount;
				System.out.println("new balance receiver: " + newBalanceReciever);
				
				PreparedStatement stmt6 = db2Conn.prepareStatement(updateAccountBalanceSQL);
				stmt6.setDouble(1, newBalanceReciever);
				stmt6.setDouble(2, account2);
				stmt6.executeUpdate();
				
				return true;
			} else {
				return false;
			}
			
			
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
