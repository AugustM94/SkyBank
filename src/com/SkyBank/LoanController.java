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

@WebServlet("/LoanView")
public class LoanController extends HttpServlet {
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
    public LoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		Account account1 = new Account();
		account1.setBalance(12734);
		account1.setCard("MasterCard");
		account1.setId(2);
		account1.setName("Primary");
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		TransactionDto transactionDto = new TransactionDto();
		
		Transaction dumTransaction = new Transaction();
		dumTransaction.setAccount(account1);
		dumTransaction.setAmount(500.);
		dumTransaction.setType(TransactionType.DEPOSIT);
		dumTransaction.setDescription("Martin er noob");
		
		transactions.add(dumTransaction);
		transactionDto.setTransactions(transactions);
		transactionDto.setAccount(account1);
		
		JSONObject jsonObject = new JSONObject(transactionDto);
		
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());		
	    
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		
		Account account1 = new Account();
		account1.setBalance(12734);
		account1.setCard("MasterCard");
		account1.setId(2);
		account1.setName("Primary");
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		TransactionDto transactionDto = new TransactionDto();
		
		Transaction dumTransaction = new Transaction();
		dumTransaction.setAccount(account1);
		dumTransaction.setAmount(500.);
		dumTransaction.setType(TransactionType.DEPOSIT);
		dumTransaction.setDescription("Martin er noob");
		
		transactions.add(dumTransaction);
		transactionDto.setTransactions(transactions);
		transactionDto.setAccount(account1);
		
		JSONObject jsonObject = new JSONObject(transactionDto);
		
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());
		
		
	}
}
