package com.example.task;

import java.util.List;


public class Account {
	
	public Account(long accountId, long profileId, String accountName, int totalAccountBalance, List<Holding> holdings) {
		super();
		this.accountId = accountId;
		this.profileId = profileId;
		this.accountName = accountName;
		this.totalAccountBalance = totalAccountBalance;
		this.holdings = holdings;
	}
	
	public Account() {
		super();
	}

	private long accountId;
	

	private long profileId;
	
	
	private String accountName;
	
	
	private int totalAccountBalance;
	
	private List<Holding> holdings;
	
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public List<Holding> getHoldings() {
		return holdings;
	}
	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}
	public int getTotalAccountBalance() {
		return totalAccountBalance;
	}
	public void setTotalAccountBalance(int totalAccountBalance) {
		this.totalAccountBalance = totalAccountBalance;
	}

	
}
