package com.vietsci.cms.frontend.etopup.model;

/** 
 * Lop the hien thong tin giao dich.
 * 
 * @author trung.doduc
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class TransactionType {

	private String code;
	private String description;
	private long id;
	private String status;
	private int delStatus;
	
	public TransactionType(){
		delStatus = 0;
	}
	
	public TransactionType(String code, String description, String status){
		this.code = code;
		this.description = description;
		this.status = status;
		delStatus = 0;
	}
	
	public TransactionType(long id, String code, String description, String status){
		this.id = id;
		this.code = code;
		this.description = description;
		this.status = status;
		delStatus = 0;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}
}
