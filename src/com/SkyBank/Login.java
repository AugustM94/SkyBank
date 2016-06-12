package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Connection db2Conn;

	private String status = "";
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	
	public void init(ServletConfig config) throws ServletException {
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			status = e.getMessage();
		} 
	}

	public void destroy() {
		try {
			db2Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		System.out.println("loggin in with name: " +request.getParameter("username") + " and password: " + request.getParameter("password"));
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
		
		JSONObject obj = new JSONObject();
		
		System.out.println("loggin in with name: " +request.getParameter("username") + " and password: " + request.getParameter("password"));
		
	    try {
	    	if (request.getParameter("username").equals("Martin") && request.getParameter("password").equals("Noob")){
	    		obj.put("success", true);
	  		} else {
	  			obj.put("success", false);
	  		}	
		} catch (JSONException e) {
			e.printStackTrace();
		}
	      
	      
	    out.println(obj.toString());
		
		
	}

}
