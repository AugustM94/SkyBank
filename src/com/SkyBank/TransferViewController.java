package com.SkyBank;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/transfer/list")
public class TransferViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	AccountDao accountDao = new AccountDao();
	
	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferViewController() {
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
		PrintWriter out = response.getWriter();
		int clientId = Integer.valueOf(request.getParameter("clientid"));
		System.out.println("clientId: " + clientId);
		ResultSet accountResult = accountDao.getAccountByClientId(clientId);
		TransactionDto accountDto = new TransactionDto();
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			while(accountResult.next()){
				Account account = new Account();
				account.setId(accountResult.getInt("ACCOUNT_ID"));
				account.setBalance(accountResult.getDouble("BALANCE"));
				account.setName(accountResult.getString("ACCOUNT_NAME"));
				account.setRegNumber(accountResult.getInt("REG_NO"));
				account.setAccountNumber(accountResult.getInt("ACCOUNT_NO"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountDto.setAccounts(accounts);
		
		JSONObject jsonObject = new JSONObject(accountDto);
		
	    System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());	
		

	}
}

