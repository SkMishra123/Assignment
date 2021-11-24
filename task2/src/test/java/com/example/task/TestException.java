
package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;


import java.io.File;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestException {

	@Autowired
	private AccountService service;

	List<Holding> holdings = new LinkedList<Holding>();

	@Test
	public void testExceptionMessage() throws Exception {

		File json = new File("account.json");
		Account account = new Account(111L, 1111L, "Rohit",5678 , holdings);
		service.createAccount(account, json);
		try {
			service.updateAccount(101, account, json);
		} catch (ResourceNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("Account Not Found having id : 101");
		}
		json.delete();
	}
}
