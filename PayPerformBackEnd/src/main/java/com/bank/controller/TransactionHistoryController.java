package com.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.documents.Accounts;
import com.bank.documents.Transaction;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransactionHistoryController {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@PostMapping("/getTransactions")
	public List<Transaction> getTransactions(@RequestParam("phoneId") String phoneId) {
		
		
		Query query = new Query();
		query.addCriteria(Criteria.where("sourcePhoneNumber").is(phoneId));
		List<Transaction> tx = mongoTemplate.findAll(Transaction.class);
		List<Transaction> list  = tx.stream().filter(x->x.getSourcePhoneNumber().equalsIgnoreCase(phoneId)).collect(Collectors.toList());
		return list;
	}

}
