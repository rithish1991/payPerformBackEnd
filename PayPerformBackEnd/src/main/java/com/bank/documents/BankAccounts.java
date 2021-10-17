package com.bank.documents;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class BankAccounts {
	
	@Field
    private String bankId;
    
	
	@Field
    private String bankName;
	
	
	
	@Field
	private int account_balance;


	@Field
	private boolean primary_acct;
	
	
	

	public String getBankId() {
		return bankId;
	}



	public void setBankId(String bankId) {
		this.bankId = bankId;
	}



	public String getBankName() {
		return bankName;
	}



	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	public int getAccount_balance() {
		return account_balance;
	}



	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}



	public boolean isPrimary_acct() {
		return primary_acct;
	}



	public void setPrimary_acct(boolean primary_acct) {
		this.primary_acct = primary_acct;
	}
	
	
	
	
	

}
