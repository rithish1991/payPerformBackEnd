package com.bank.controller;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.documents.Accounts;
import com.bank.documents.BankAccounts;

import org.springframework.data.mongodb.core.query.Criteria;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddBankController {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@PostMapping("/addBankAccount")
	public String addBankAccount(@RequestParam("bid") String bid,@RequestParam("bname") String bname,@RequestParam("phone") String phone,@RequestParam("name") String name) {
		
		String message ="";
		Query query = new Query();
		query.addCriteria(Criteria.where("phone_id").is(phone));
		Accounts document = mongoTemplate.findOne(query,Accounts.class);
		//starting account for first time
		if(document==null)
		{
			
			Accounts acctData = new Accounts();
			acctData.setPhone_id(phone);
			acctData.setName(name);
			List<BankAccounts> listAccounts =  new ArrayList<BankAccounts>();
			BankAccounts banksAccounts =  new BankAccounts();
			banksAccounts.setBankId(bid);
			banksAccounts.setBankName(bname);
			//Initial Deposit of 5000 rupees
			banksAccounts.setAccount_balance(5000);
			//setting primary account like UPI
			banksAccounts.setPrimary_acct(true);
			listAccounts.add(banksAccounts);
			acctData.setAccounts(listAccounts);
			 mongoTemplate.insert(acctData);
			 message = "Your Account has been Added Successfully";
		}
		else {
			 
			List<BankAccounts> listAccounts = document.getAccounts();
			int size = listAccounts.stream().filter(x->x.getBankId().equalsIgnoreCase(bid)).collect(Collectors.toList()).size();
			if(size>0) {
				 message = "Your Account Already Exists for this Bank";
				 return message;
			}
			else {
				
			
				BankAccounts banksAccounts =  new BankAccounts();
				banksAccounts.setBankId(bid);
				banksAccounts.setBankName(bname);
				//Initial Deposit of 5000 rupees
				banksAccounts.setAccount_balance(5000);
				//setting primary account like UPI
				banksAccounts.setPrimary_acct(false);
				
				document.getAccounts().add(banksAccounts);
				 mongoTemplate.save(document);
				 message = "Your Account has been Added Successfully";
			}
		}
		
		
		
		return message;
	}
	
	
	

}
