package com.example.task;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class AccountService {
	
	public Account createAccount(Account c, File file) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Account> accounts;
		try {
			accounts = objectMapper.readValue(new File("account.json"), new TypeReference<List<Account>>(){});
		}
		catch(Exception e) { 
			accounts = new LinkedList<>();
		}
		
		for(int i=0; i<accounts.size(); i++)
			if(accounts.get(i).getAccountId()== c.getAccountId())
		    {
			System.out.println("Account already present with id : "+c.getAccountId());
			return null;
		    }
		accounts.add(c);
		objectMapper.writeValue(new File("account.json"), accounts);
		System.out.println("Created an account having id :"+c.getAccountId());
		return c; 
	}

	
	public Account updateAccount(long id, Account c, File file) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Account> accounts = null;
		Account updateThis = null;
		try {
			accounts = objectMapper.readValue(new File("account.json"), new TypeReference<List<Account>>(){});
			for (int i=0; i<accounts.size(); i++)
			{
				updateThis = accounts.get(i);
				if(updateThis.getAccountId()==id)
				{
					updateThis.setAccountName(c.getAccountName());
					updateThis.setProfileId(c.getProfileId());
					updateThis.setTotalAccountBalance(c.getTotalAccountBalance());
					updateThis.setHoldings(c.getHoldings());
					System.out.println("Updated account details having id : "+ id);
					break;
				}
				updateThis = null;
			}
			objectMapper.writeValue(new File("account.json"), accounts);
			if (updateThis == null)
			{
				System.out.println("No account found with id : "+id);
				throw new ResourceNotFoundException("Account Not Found having id : "+id);
			}
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Account Not Found having id : "+id);
		}
		return updateThis;
	}

	public List<Account> getAllAccount(File file) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Account> accounts = new LinkedList<>();
		try {
		accounts = objectMapper.readValue(new File("account.json"), new TypeReference<List<Account>>(){});
		System.out.println("Fetched data of follwing accounts having ids :");
		for (int i=0; i<accounts.size(); i++)
			System.out.println(accounts.get(i).getAccountId());
		}
		catch(Exception e) {
			System.out.println("No Accounts created yet");
		}
		return accounts;
	}

	
	public HttpStatus deleteAccount(long id, File file) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Account> accounts = null;
		Account deletedAcc = null;
		List<Account> remaining = new LinkedList<>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		try {
			accounts = objectMapper.readValue(file, new TypeReference<List<Account>>(){});
			for (int i=0; i<accounts.size(); i++)
			{
				Account c = accounts.get(i);
				if(c.getAccountId()!=id)
					remaining.add(c);
				else
					deletedAcc = c;
			}
			objectMapper.writeValue(file, remaining);
			if (deletedAcc != null) {
				status = HttpStatus.OK;
				System.out.println("Account deleted having id : "+id);
			}
			else
				System.out.println("Account Not Found having id : "+id);
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Account Not Found having id : "+id);		
		}
		return status;
	}
}
