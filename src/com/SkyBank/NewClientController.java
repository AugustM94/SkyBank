package com.SkyBank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

@WebServlet("/client/create")
public class NewClientController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	private static Connection db2Conn;
	
	private LoginDao loginDao = new LoginDao();
	private ClientDao clientDao = new ClientDao();
	private CountryDao countryDao = new CountryDao();
	private CityDao cityDao = new CityDao();
	private AddressDao addressDao = new AddressDao();

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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewClientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		int phone = Integer.parseInt(request.getParameter("phone"));
		int cprNumber = Integer.parseInt(request.getParameter("cpr"));
		String street = request.getParameter("street");
		String streetNumber = request.getParameter("streetno");
		String zipNumber = request.getParameter("zip");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		int cpr = Integer.parseInt(request.getParameter("cpr"));
				
		
		ResultSet loginResult = loginDao.getLoginByUsername(username);
		ResultSet countryResult = countryDao.getContry(country);
		ResultSet cityResult = cityDao.getCity(city);
		ResultSet addressResult = addressDao.getAddress(street, streetNumber);
		
		// if login is empty, create new login
		if(resultSetConverter.isResultSetEmpty(loginResult)){
			loginDao.insertLogin(username, password);
			
			// check if country exists, else create it
			if(resultSetConverter.isResultSetEmpty(countryResult)){
				countryDao.insertCountry(country);
				System.out.println("did create new country");
			} 
			
			
			if(resultSetConverter.isResultSetEmpty(cityResult)){
				cityDao.insertCity(city, zipNumber, country);
				System.out.println("did create new city");
			}
			
			

			try {
				if(resultSetConverter.isResultSetEmpty(addressResult)){
					cityResult = cityDao.getCity(city);
					System.out.println(resultSetConverter.isResultSetEmpty(cityResult));
					int cityId = cityResult.getInt("CITY_ID");
					System.out.println("cityid: " + cityId);
					addressDao.insertAddress(street, streetNumber, cityId);
					System.out.println("did create new address");
				}
				
				addressResult = addressDao.getAddress(street, streetNumber);
				System.out.println(resultSetConverter.isResultSetEmpty(addressResult));
				int addressId = addressResult.getInt("ADDRESS_ID");
				
				boolean result = clientDao.insertClient(username, addressId, firstName, lastName, phone, cpr);
				System.out.println(result);
				
			} catch (NumberFormatException | SQLException e) {
				response.setStatus(501);
				e.printStackTrace();
			}
			
			
			
			
			
		} else {
			response.setStatus(501);
		}
		
		System.out.println("create new client: " +
				"  username: " + username +
				", password: " + password +
				", firstName: " + firstName +
				", lastName: " + lastName +
				", phone: " + phone +
				", cprNumber: " + cprNumber +
				", street: " + street +
				", streetno: " + streetNumber +
				", zipNumber: " + zipNumber +
				", city: " + city +
				", country: " + country
				);
		
	}
}
