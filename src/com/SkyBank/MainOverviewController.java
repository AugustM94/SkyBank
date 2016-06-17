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

@WebServlet("/main/overview")
public class MainOverviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	AccountDao accountDao = new AccountDao();
	TransactionDao transactionDao = new TransactionDao();
	CurrencyDao currencyDao = new CurrencyDao();
	
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	
	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainOverviewController() {
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
		int clientId = Integer.valueOf(request.getParameter("clientid"));
		
		MainOverviewDto mainOverviewDto = new MainOverviewDto();
		List<Account> accounts = new ArrayList<Account>();
		List<Currency> currencies = new ArrayList<Currency>();
		
		
		try {
			ResultSet currencyResult = currencyDao.getCurrencies();
			while(currencyResult.next()){
				Currency currency = new Currency();
				currency.setName(currencyResult.getString("CURRENCY_ID"));
				currency.setValue(currencyResult.getDouble("CONVERSION_RATE"));
				currencies.add(currency);
			}
			
			ResultSet accountResult = accountDao.getAccountByClientId(clientId);
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
		
		mainOverviewDto.setAccounts(accounts);
		mainOverviewDto.setCurrencies(currencies);
		mainOverviewDto.setDeposits(0);
		mainOverviewDto.setWithdrawels(0);	
		
		JSONObject jsonObject = new JSONObject(mainOverviewDto);
	      
	     System.out.println(jsonObject.toString());
	    out.println(jsonObject.toString());
		
		
	}
}
