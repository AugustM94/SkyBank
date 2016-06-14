package com.SkyBank;

import java.util.ArrayList;
import java.util.List;

public class MainOverviewDto {
	List<Account> accounts;  
	List<Currency> currencies;  
	int withdrawels;
	int deposits;
	int totalBalance;
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	public int getWithdrawels() {
		return withdrawels;
	}
	public void setWithdrawels(int withdrawels) {
		this.withdrawels = withdrawels;
	}
	public int getDeposits() {
		return deposits;
	}
	public void setDeposits(int deposits) {
		this.deposits = deposits;
	}
	public int getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(int totalBalance) {
		this.totalBalance = totalBalance;
	}
}
