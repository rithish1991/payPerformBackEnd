package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.documents.Accounts;
import com.bank.documents.BankAccounts;
import com.bank.documents.Transaction;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BalanceController {
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@PostMapping("/getBalance")
	public List<BankAccounts> getTransactions(@RequestParam("phoneId") String phoneId) {
		
		
		
		Query query = new Query();
		query.addCriteria(Criteria.where("phone_id").is(phoneId));
		
		Accounts document = mongoTemplate.findOne(query,Accounts.class);
		return document.getAccounts();
	}

}
