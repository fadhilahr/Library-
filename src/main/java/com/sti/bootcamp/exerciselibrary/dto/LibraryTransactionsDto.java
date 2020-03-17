package com.sti.bootcamp.exerciselibrary.dto;

import java.util.Date;

public class LibraryTransactionsDto {

	private StudentsDto student;
	private BooksDto book;
	private Date lendDate;
	private Date dueDate;
	private String status;
	private Double price;
	
	
	public StudentsDto getStudent() {
		return student;
	}
	public void setStudent(StudentsDto student) {
		this.student = student;
	}
	public BooksDto getBook() {
		return book;
	}
	public void setBook(BooksDto book) {
		this.book = book;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
