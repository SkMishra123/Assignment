package com.example.task;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class TestAccount {
	
	Account account = new Account ();
	List<Holding> holdings = new ArrayList<Holding>();

	@BeforeEach
	public void setUp() {
		account.setAccountId(101);
		account.setProfileId(10101);
		account.setTotalAccountBalance(5678);
		account.setAccountName("Ajay");
		account.setHoldings(holdings);
	}
	
	@Test
	public void test_getterSetter() {
		assertEquals(101, account.getAccountId());
		assertEquals(10101, account.getProfileId());
		assertEquals("Ajay", account.getAccountName());
		assertEquals(5678, account.getTotalAccountBalance());
		assertEquals(holdings, account.getHoldings());
	}
	
}
