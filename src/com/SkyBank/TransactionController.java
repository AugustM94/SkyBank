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

@WebServlet("/transactions/list")
public class TransactionController extends HttpServlet {
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
    public TransactionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		TransactionDto transactionDto = new TransactionDto();
		
		List<Account> accounts = new ArrayList<Account>();
		List<Transaction> transactions1 = new ArrayList<Transaction>();
		List<Transaction> transactions2 = new ArrayList<Transaction>();
		/**
		 * account 1
		 */
		Account account1 = new Account();

		Transaction transaction1 = new Transaction();
		transaction1.setAccount(2);
		transaction1.setAmount(500.0);
		transaction1.setType(TransactionType.DEPOSIT);
		transaction1.setDescription("Martin er noob");
		transactions1.add(transaction1);
		Transaction transaction2 = new Transaction();
		transaction2.setAccount(2);
		transaction2.setAmount(5000.0);
		transaction2.setType(TransactionType.WITHDRAW);
		transaction2.setDescription("August er heldig i bordfodbold");
		transactions1.add(transaction2);
		
		account1.setBalance(12734);
		account1.setCard("MasterCard");
		account1.setId(2);
		account1.setName("Primary");
		account1.setTransactions(transactions1);
		accounts.add(account1);

		/**
		 * account 2
		 */
		Account account2 = new Account();
		
		Transaction transaction3 = new Transaction();
		transaction3.setAccount(2);
		transaction3.setAmount(5200.0);
		transaction3.setType(TransactionType.DEPOSIT);
		transaction3.setDescription("Betting");
		transactions2.add(transaction3);
		Transaction transaction4 = new Transaction();
		transaction4.setAccount(2);
		transaction4.setAmount(870.0);
		transaction4.setType(TransactionType.WITHDRAW);
		transaction4.setDescription("testsasa");
		transactions2.add(transaction4);
		
		account2.setBalance(7342);
		account2.setId(3);
		account2.setName("Budget");
		account2.setTransactions(transactions2);
		accounts.add(account2);
		
		transactionDto.setAccounts(accounts);
		
		/*
		String newClientSQL = "";
		try {
			PreparedStatement stmt = db2Conn.prepareStatement(newClientSQL);
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		JSONObject jsonObject = new JSONObject(transactionDto);
		
		
		
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());	
		
		
	}
}
