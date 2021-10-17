package com.bank.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Accounts {
	
	
	
	
	@Id
    private String phone_id;
    
	
	@Field
    private String name;
	
	
	
	@Field
    private List<BankAccounts> accounts;



	public String getPhone_id() {
		return phone_id;
	}



	public void setPhone_id(String phone_id) {
		this.phone_id = phone_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<BankAccounts> getAccounts() {
		return accounts;
	}



	public void setAccounts(List<BankAccounts> accounts) {
		this.accounts = accounts;
	}
	
	
	
	
	
	
}
