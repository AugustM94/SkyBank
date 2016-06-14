package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/MainOverview")
public class MainOverview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Connection db2Conn;

	private String status = "";
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	
	public void init(ServletConfig config) throws ServletException {
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		
		/*
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			status = e.getMessage();
		} */
	}

	public void destroy() {
		/*try {
			db2Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainOverview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		// fix mit lorte sql August!
		//String SQL = "SELECT * FROM DTUGRP01.LOGIN WHERE (USER_NAME, PASSWORD) = (" +
		//		request.getParameter("user_id") + "," + request.getParameter("user_password") + ")";
		/*
		try {
			PreparedStatement pstmt = db2Conn.prepareStatement(SQL);
			ResultSet result = pstmt.executeQuery();

		    status = ResultSetConverter.convert(result).toString();
		    
			System.out.println(status);
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}*/
		
		System.out.println("loggin in with name: " +request.getParameter("userid") + " and password: " + request.getParameter("password"));
		if (request.getParameter("username").equals("Martin") && request.getParameter("password").equals("Noob")){
			out.println("true");
		} else {
			out.println("false");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		
		JSONArray list = new JSONArray();
		
		MainOverviewDto mainOverviewDto = new MainOverviewDto();
		List<Account> accounts = new ArrayList<Account>();
		List<Currency> currencies = new ArrayList<Currency>();

		Account account1 = new Account();
		Account account2 = new Account();
		
		account1.setBalance(12734);
		account1.setCard("MasterCard");
		account1.setId(2);
		account1.setName("Primary");
		accounts.add(account1);
		
		account2.setBalance(5411);
		account2.setCard("Visa");
		account2.setId(4);
		account2.setName("Budget");
		accounts.add(account2);
		
		Currency currency1 = new Currency();
		Currency currency2 = new Currency();
		
		currency1.setName("Danish Crown");
		currency1.setIso("DKK");
		currency1.setValue(100.00);
		
		currency2.setName("US Dollar");
		currency2.setIso("USD");
		currency2.setValue(673.23);
		
		currencies.add(currency1);
		currencies.add(currency2);
		
		
		mainOverviewDto.setAccounts(accounts);
		mainOverviewDto.setCurrencies(currencies);
		mainOverviewDto.setDeposits(16);
		mainOverviewDto.setWithdrawels(54);	
		
		JSONObject jsonObject = new JSONObject(mainOverviewDto);
		/*
		try {
			JSONObject obj1 = new JSONObject();
			obj1.put("name", "NEM-KONTO");
			obj1.put("id", new Integer(1231232765));
			
			JSONObject obj2 = new JSONObject();
			obj2.put("name", "Budget");
			obj2.put("id", new Integer(1575758383));
			
			list.put(obj1);
			list.put(obj2);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	     System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());
		
		
	}
}
