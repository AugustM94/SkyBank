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

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private static Connection db2Conn;
	LoginDao loginDao = new LoginDao();
	ClientDao clientDao = new ClientDao();
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	
	public void init(ServletConfig config) throws ServletException {
		/*String DB_USER = "DTU02";
		String DB_PASSWORD = "FAGP2016";
		
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			db2Conn = DriverManager.getConnection("jdbc:db2://192.86.32.54:5040/DALLASB:" + "user=" + DB_USER + ";"
					+ "password=" + DB_PASSWORD + ";");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			status = e.getMessage();
		}*/ 
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
    public LoginController() {
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
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginDto loginDto = new LoginDto();
		loginDto.setSuccess(false);
		ResultSet loginResult = loginDao.getLogin(username, password);
		ResultSet clientResult = clientDao.getClient(username);
		if(!resultSetConverter.isResultSetEmpty(loginResult)){
			try {
				System.out.println(resultSetConverter.isResultSetEmpty(clientResult));
				loginDto.setClientId(clientResult.getInt("CLIENT_ID"));
				loginDto.setUsername(username);
				loginDto.setSuccess(true);
			} catch (SQLException e) {
				System.out.println("loggin in with: " + username + " " + password);
				e.printStackTrace();
			}
		}

		JSONObject jsonObject = new JSONObject(loginDto);
	    out.println(jsonObject.toString());
		
		
	}

}
