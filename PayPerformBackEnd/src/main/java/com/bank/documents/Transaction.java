package com.bank.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Transaction {

	
	//Transaction Id
	@Id
    private String tid;
    
	
	@Field
    private String sourcePhoneNumber;
	
	
	
	@Field
	private String debitedFrom;


	@Field
    private String destPhoneNumber;
	
	@Field
	private String creditedTo;
	
	
	
	@Field
    private int amount;
	
	
	@Field
    private Date date;
	
	//Success or Failure flag
	@Field
	private String flag;


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getSourcePhoneNumber() {
		return sourcePhoneNumber;
	}


	public void setSourcePhoneNumber(String sourcePhoneNumber) {
		this.sourcePhoneNumber = sourcePhoneNumber;
	}


	


	


	

	public String getDestPhoneNumber() {
		return destPhoneNumber;
	}


	public void setDestPhoneNumber(String destPhoneNumber) {
		this.destPhoneNumber = destPhoneNumber;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getDebitedFrom() {
		return debitedFrom;
	}


	public void setDebitedFrom(String debitedFrom) {
		this.debitedFrom = debitedFrom;
	}


	public String getCreditedTo() {
		return creditedTo;
	}


	public void setCreditedTo(String creditedTo) {
		this.creditedTo = creditedTo;
	}
	
	
	
	
	
	
	
}
