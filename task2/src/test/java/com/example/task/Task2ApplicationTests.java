
package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class Task2ApplicationTests {

	@Autowired
	private AccountService service;

	ObjectMapper om = new ObjectMapper();
	
	Account account;
	List<Holding> holdings = new LinkedList<Holding>();
	File file;
	
	@BeforeAll
	public void setUp() {
		file = new File("account.json");
		account = new Account(212L, 2122L, "Ankt", 5678, holdings);
	}
	@AfterAll
	public void cleanUp() {
		file.delete();
	}

	@Test 
	@Order(1)
	public void createAccountTest() throws Exception {
		service.createAccount(account, file);
		assertEquals(account.getAccountId(),
				om.readValue(file,
						new TypeReference<List<Account>>(){}).get(0).getAccountId());
	}

	@Test
	@Order(2)
	public void getAccountsTest() throws Exception {
		service.getAllAccount(file);
		assertThat(om.readValue(file,
						new TypeReference<List<Account>>(){}).size()).isGreaterThan(0);
	}

	@Test()
	@Order(3)
	public void updateAccountTest() throws Exception {
		Account account = new Account(212L, 2122L, "Ram", 5678, holdings);
		service.updateAccount(212L, account, file);
		assertEquals(account.getAccountName(),
				om.readValue(new File("account.json"),
						new TypeReference<List<Account>>(){}).get(0).getAccountName());
	}
		
	@Test
	@Order(4)
	public void deleteAccountTest() throws Exception {
		long id = account.getAccountId();
		service.deleteAccount(id, file);
		assertEquals(0,
				om.readValue(new File("account.json"),
						new TypeReference<List<Account>>(){}).size());

	}
}
