package com.sti.bootcamp.exerciselibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.bootcamp.exerciselibrary.exception.StockBukuHabisException;
import com.sti.bootcamp.exerciselibrary.model.LibraryTransactions;
import com.sti.bootcamp.exerciselibrary.saveparam.LibraryTransactionsSaveParam;

public interface LibraryTransactionsDao {
	LibraryTransactions addLibraryTransactions(LibraryTransactionsSaveParam addLibraryTransactions) throws StockBukuHabisException, Exception ;	
	List<LibraryTransactions> getAllLibraryTransactions() throws Exception;
	List<LibraryTransactions> getLibraryTransactionsByStudentId(String id) throws Exception;
	List<LibraryTransactions> getLibraryTransactionsByBookId(String id) throws Exception;
	void deleteLibraryTransactionById(String id) throws Exception;
	LibraryTransactions updateLibraryTransaction(String id, LibraryTransactionsSaveParam updateLibraryTransaction) throws Exception;
	Page<LibraryTransactions> findPaging(int page) throws Exception;
}
