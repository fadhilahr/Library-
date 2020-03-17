package com.sti.bootcamp.exerciselibrary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "library_transaction")
public class LibraryTransactions {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@ManyToOne
	@JoinColumn(name = "student")
	private Students student;
	@ManyToOne
	@JoinColumn(name = "books")
	private Books book;
	@Column(name = "lend_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date lendDate;
	@Column(name = "due_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dueDate;
	private String status;
	private Double price;

	public String getId() {
		return id;
	}

	public LibraryTransactions() {

	}

	public LibraryTransactions(String string) {
		this.status = string;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
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
