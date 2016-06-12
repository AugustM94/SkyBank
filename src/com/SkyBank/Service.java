package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Service
 */
@WebServlet("/Service")
public class Service extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Connection db2Conn;
	private static Statement stmt;
	private String status = "Empty";

	// Hej 

	public void init(ServletConfig config) throws ServletException {
		String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
			stmt = db2Conn.createStatement();
			status = "Connection found";

		    ResultSet result = stmt.executeQuery("SELECT * FROM DTUGRP01.CITY");

		    while(result.next())
		    {
		    	status += result.getString(2);
		    }
		    
			System.out.println("connection and statement created");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			status = e.getMessage();
		}
	}

	public void destroy() {
		try {
			db2Conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("status" + status);
		/*String updateSQL = "select * from DTUGRP01.SAMPLE";
		ResultSet result = null;
		String msg = "";

		try {
			result = db2Conn.createStatement().executeQuery(updateSQL);
			out.println("resultsize: "+result.getFetchSize());
			out.println("result: "+result.getMetaData());
			
		} catch (SQLException sqle) {
			out.println("fuck this catch");
			out.println(sqle);
			sqle.printStackTrace();
		}

		if (result == null) {
			msg = "If";
		} else {
			out.println("else-result: " + result.toString());
			msg = "Else";
		}
		out.println(msg);*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
