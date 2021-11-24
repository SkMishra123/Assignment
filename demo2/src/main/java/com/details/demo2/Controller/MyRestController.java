package com.details.demo2.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.details.demo2.bean.Account;
import com.details.demo2.repo.AccountRepo;
import com.details.demo2.repo.HoldingRepo;


@RestController
public class MyRestController {
	
 	
	@Autowired
	AccountRepo repo;
	
	@Autowired
	HoldingRepo holding;
	
	@GetMapping(path = "/Accounts")
	public List<Account> allAccounts(){
		ArrayList<Account> list = new ArrayList<Account>();
		Iterator<Account> it = repo.findAll().iterator();
		while(it.hasNext())
			list.add(it.next());
		return list;
	}
	
	@PostMapping(path = "/CreateAccount")
	public Account insertAccount(@RequestBody Account account) {
		return  repo.save(account);
	}
	
	@DeleteMapping(path ="/DeleteAccount") 
	public void deleteAccount(Integer accountId) {
		repo.deleteById(accountId);
	}
	
	
	@GetMapping(path = "/Account/{accountId}")
	public Account getAccount(@PathVariable Integer accountId ) {
		return repo.findById(accountId).get();
	}
	
	@PutMapping("/UpdateAccount/{accountId}")
	public void updateAccount(@PathVariable Integer accountId,@RequestBody Account account) {
		
		Optional<Account> acc = repo.findById(accountId);
		System.out.println(account);
		if (acc.isPresent()) {
			
			
			
			Account accountUpdate = acc.get();
			accountUpdate.setAccountId(account.getAccountId());
			accountUpdate.setAccountName(account.getAccountName());
			accountUpdate.setTotalAccountBalance(account.getTotalAccountBalance());
			accountUpdate.setProfileId(account.getProfileId());
			accountUpdate.setHolding(account.getHolding());
			repo.save(accountUpdate);
			
			
		}
	}
	
	
	}
