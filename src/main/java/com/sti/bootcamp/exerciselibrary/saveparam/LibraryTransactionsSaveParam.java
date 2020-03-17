package com.sti.bootcamp.exerciselibrary.saveparam;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LibraryTransactionsSaveParam {
	private String name;
	private String title;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date lendDate;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
