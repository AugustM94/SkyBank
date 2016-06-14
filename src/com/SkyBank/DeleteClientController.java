
	package com.SkyBank;

	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	import javax.servlet.ServletConfig;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet("/DeleteClientView")
	public class DeleteClientController extends HttpServlet{
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
	    public DeleteClientController() {
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
			String userid = request.getParameter("userid");
			
			System.out.println("create new client: " +
					", userid: " + userid
					);
			
		}
	}

