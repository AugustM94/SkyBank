package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/user/overview")
public class UserOverviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClientDao clientDao = new ClientDao();
	AddressDao addressDao = new AddressDao();
	CityDao cityDao = new CityDao();
	CountryDao countryDao = new CountryDao();
	
	ResultSetConverter resultSetConverter = new ResultSetConverter();

	public void init(ServletConfig config) throws ServletException {
		
	}

	public void destroy() {
		
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

		int clientId = Integer.valueOf(request.getParameter("clientid"));
		UserOverviewDto userOverviewDto = new UserOverviewDto();
		
		
		
		Address address = new Address();
		try {
			ResultSet clientResult = clientDao.getClientById(clientId);
			clientResult.next();
			ResultSet AddressResult = addressDao.getAddressById(clientResult.getInt("ADDRESS_ID"));
			AddressResult.next();
			ResultSet cityResult = cityDao.getCityById(AddressResult.getInt("CITY_ID"));
			cityResult.next();
			address.setAddress(AddressResult.getString("STREET_NAME") + " " + AddressResult.getString("STREET_NO"));
			address.setCity(cityResult.getString("CITY_NAME"));
			address.setCountry(cityResult.getString("COUNTRY"));
			address.setZip(cityResult.getString("POSTAL_CODE"));
			
			userOverviewDto.setFirstName(clientResult.getString("FIRST_NAME"));
			userOverviewDto.setLastName(clientResult.getString("LAST_NAME"));
			userOverviewDto.setPhone(clientResult.getInt("PHONE"));
			userOverviewDto.setUsername(clientResult.getString("USER_NAME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userOverviewDto.setAddress(address);
		
		Currency currency = new Currency();
		currency.setId(0);
		currency.setName("DKK");
		currency.setValue(100.0);
		userOverviewDto.setCurrency(currency);
		
		
		
		JSONObject jsonObject = new JSONObject(userOverviewDto);
	      
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());

	}
}
