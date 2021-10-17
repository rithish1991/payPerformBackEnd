package com.bank.controller;

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
import com.bank.documents.BankAccounts;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TransferController {
	
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@PostMapping("/transferAccounts")
	public String transferAccounts(@RequestParam("fromBid") String fromBid,@RequestParam("fromphone") String sourcePhoneNo,@RequestParam("toBid") String toBid,@RequestParam("toPhone") String toPhone,@RequestParam("amount") String amount) {
		
		
		
		Query query = new Query();
		query.addCriteria(Criteria.where("phone_id").is(sourcePhoneNo));
		
		Accounts document = mongoTemplate.findOne(query,Accounts.class);
		if(document==null) {
			return "Source Account Doesn't Exists";
		}
		query = new Query();
		query.addCriteria(Criteria.where("phone_id").is(toPhone));
		Accounts destDocument = mongoTemplate.findOne(query,Accounts.class);
		 if(destDocument==null) {
				return "Destination Account Doesn't Exists";
			}
		 int  fromSize = document.getAccounts().stream().filter(x->x.getBankId().equalsIgnoreCase(fromBid)).collect(Collectors.toList()).size();
		 int toSize  =  destDocument.getAccounts().stream().filter(x->x.getBankId().equalsIgnoreCase(toBid)).collect(Collectors.toList()).size();
		 if(fromSize==0)
		 {
			 return "Destination Account Doesn't Exists";
		 }
		 if(toSize==0)
		 {
			 
			 return "Destination Account Doesn't Exists";
		 }
		 int amountSize = Integer.parseInt(amount);
		 
		 int sourceAmount  = document.getAccounts().stream().filter(x->x.getBankId().equalsIgnoreCase(fromBid)).collect(Collectors.toList()).get(0).getAccount_balance();
		 if(amountSize>sourceAmount) {
			 
			 return "Insufficient Funds in your account";
		 }
		 
		 for(BankAccounts accts:document.getAccounts())
		 {
			 if(accts.getBankId().equalsIgnoreCase(fromBid))
			 {
				 
				 accts.setAccount_balance(accts.getAccount_balance() - amountSize);
				 break;
			 }
			 
		 }
		 mongoTemplate.save(document);
		 for(BankAccounts accts:destDocument.getAccounts())
		 {
			 if(accts.getBankId().equalsIgnoreCase(toBid))
			 {
				 
				 accts.setAccount_balance(accts.getAccount_balance() + amountSize);
				 break;
			 }
			 
		 }
		 mongoTemplate.save(destDocument);
		return null;
	}

}
