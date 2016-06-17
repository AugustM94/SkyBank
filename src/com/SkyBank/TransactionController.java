package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/transactions/list")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Connection db2Conn;

	AccountDao accountDao = new AccountDao();
	
	TransactionDao transactionDao = new TransactionDao();
	
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	
	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
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
		
		int clientid = Integer.valueOf(request.getParameter("clientid"));
		
		TransactionDto transactionDto = new TransactionDto();
		List<Account> accounts = new ArrayList<Account>();
		
		ResultSet accountResult = accountDao.getAccountByClientId(clientid);
		try {
			while(accountResult.next()){
				List<Transaction> transactions = new ArrayList<Transaction>();
				Account account = new Account();
				account.setId(accountResult.getInt("ACCOUNT_ID"));
				account.setBalance(accountResult.getDouble("BALANCE"));
				account.setName(accountResult.getString("ACCOUNT_NAME"));
				account.setRegNumber(accountResult.getInt("REG_NO"));
				account.setAccountNumber(accountResult.getInt("ACCOUNT_NO"));
				ResultSet transactionResult = transactionDao.getTransactionsByAccountId(accountResult.getInt("ACCOUNT_ID"));
				while(transactionResult.next()){
					Transaction transaction = new Transaction();
					transaction.setAccount(transactionResult.getInt("ACCOUNT_ID"));
					transaction.setAmount(transactionResult.getDouble("AMOUNT"));
					transaction.setDescription(transactionResult.getString("DESCRIPTION"));
					transaction.setType(TransactionType.values()[transactionResult.getInt("TRANSACTION_TYPE")]);
					transactions.add(transaction);
				}
				account.setTransactions(transactions);
				accounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		transactionDto.setAccounts(accounts);
		JSONObject jsonObject = new JSONObject(transactionDto);
		
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());	
		
		
	}
}
