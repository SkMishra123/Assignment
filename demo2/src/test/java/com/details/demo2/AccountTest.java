package com.details.demo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.details.demo2.bean.Account;
import com.details.demo2.bean.Holding;

public class AccountTest {
	
	Account account = new Account ();
	List<Holding> holdings = new ArrayList<Holding>();

	@BeforeEach
	public void setUp() {
		account.setAccountId(101);
		account.setProfileId(10101);
		account.setTotalAccountBalance(5678);
		account.setAccountName("Shakti");
		account.setHolding(holdings);
	}
	
	@Test
	public void test_getterSetter() {
		assertEquals(101, account.getAccountId());
		assertEquals(10101, account.getProfileId());
		assertEquals("Shakti", account.getAccountName());
		assertEquals(5678, account.getTotalAccountBalance());
		assertEquals(holdings, account.getHolding());
	}
	
}

