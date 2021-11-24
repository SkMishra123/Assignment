package com.example.task;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	
	@GetMapping("/Accounts")
	public ResponseEntity<List<Account>> getAllCustomer() throws Exception{
		File json = new File("data.json");
		return ResponseEntity.ok().body(accountService.getAllAccount(json));
	}
	
	@PostMapping("/CreateAccount") public ResponseEntity<Account> createAccount(@RequestBody Account c) throws Exception {
		File json = new File("data.json");
		return ResponseEntity.ok().body(accountService.createAccount(c, json)); 
	} 
	
	@PutMapping("/UpdateAccount/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable long id,@RequestBody Account c) {
		File json = new File("data.json");
		return ResponseEntity.ok().body(accountService.updateAccount(id, c, json)); 
	}
	
	@DeleteMapping("/DeleteAccount/{id}")
	public HttpStatus deleteProduct(@PathVariable long id){
		File json = new File("data.json");
		return this.accountService.deleteAccount(id,json);
	}
	
	@GetMapping("/healthcheck")
	public HttpStatus healthcheck() throws Exception{
		return HttpStatus.OK;
	}
}
