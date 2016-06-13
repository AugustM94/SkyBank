package com.SkyBank;

import java.util.List;

public class TransactionDto {
	private Account account;
	private List<Transaction> transactions;
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	} 
	
}
