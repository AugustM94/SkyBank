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

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/user/overview")
public class UserOverviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Connection db2Conn;

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
		}
	}

	public void destroy() {
		try {
			db2Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	    public UserOverviewController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
PrintWriter out = response.getWriter();
		
		
		JSONArray list = new JSONArray();
		
		UserOverviewDto userOverviewDto = new UserOverviewDto();
		
		Address address = new Address();
		address.setAddress("Akademivej 100B, st, 115");
		address.setCity("Kongens Lygnby");
		address.setCountry("Denmark");
		address.setZip("2800");
		userOverviewDto.setAddress(address);
		
		Currency currency = new Currency();
		currency.setId(0);
		currency.setIso("DKK");
		currency.setName("Danish Crown");
		currency.setValue(100.0);
		userOverviewDto.setCurrency(currency);
		
		userOverviewDto.setFirstName("Martin");
		userOverviewDto.setLastName("Champ");
		userOverviewDto.setInterestRate(0.5);
		userOverviewDto.setPhone("12345678");
		userOverviewDto.setUsername("Martin");
		
		JSONObject jsonObject = new JSONObject(userOverviewDto);
	      
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());

	}
}
