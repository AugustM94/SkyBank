package com.SkyBank;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transfer/create")
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ResultSetConverter resultSetConverter = new ResultSetConverter();
	TransactionDao transactionDao = new TransactionDao();
	AccountDao accountDao = new AccountDao();
	
	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferController() {
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
		int accountSenderId = Integer.valueOf(request.getParameter("accountid"));
		int senderRegNumber = Integer.valueOf(request.getParameter("regno"));
		int senderAccountNumber = Integer.valueOf(request.getParameter("accountno"));
		double amount = Double.valueOf(request.getParameter("amount"));
		String description = request.getParameter("description");
		
		ResultSet accountSender = accountDao.getAccount(accountSenderId);
		ResultSet accountReceiver = accountDao.getAccountByRegAndAccount(senderRegNumber, senderAccountNumber);
		
		try {
			double balance = accountSender.getDouble("BALANCE");
			if (balance - amount > 0.0){
				int accountReceiverId = accountReceiver.getInt("ACCOUNT_ID");
				transactionDao.insertTransaction(accountSenderId, accountReceiverId, amount, description, "DKK");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// insert into database
		
		//System.out.println("clientid: " + clientid + ", accountid: " + accountid + " , receiver: " + receiver + ", amount: " + amount);

	}
}

