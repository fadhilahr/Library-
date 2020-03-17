package com.sti.bootcamp.exerciselibrary.exception;

public class StockBukuHabisException extends Exception {

	public StockBukuHabisException() {
		super("Stock Buku yang Ingin Dipinjam Habis");
		
	}

	public StockBukuHabisException(String message) {
		super(message);
		
	}

	
}
