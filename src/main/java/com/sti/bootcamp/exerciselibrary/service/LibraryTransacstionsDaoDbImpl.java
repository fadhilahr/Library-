package com.sti.bootcamp.exerciselibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.sti.bootcamp.exerciselibrary.exception.StockBukuHabisException;
import com.sti.bootcamp.exerciselibrary.model.Books;
import com.sti.bootcamp.exerciselibrary.model.LibraryTransactions;
import com.sti.bootcamp.exerciselibrary.model.Students;
import com.sti.bootcamp.exerciselibrary.repository.LibraryTransactionsRepository;
import com.sti.bootcamp.exerciselibrary.saveparam.LibraryTransactionsSaveParam;

public class LibraryTransacstionsDaoDbImpl implements LibraryTransactionsDao {

	@Autowired
	private LibraryTransactionsRepository libraryTransactionsRepository;

	@Autowired
	private BooksDao booksDao;

	@Autowired
	private StudentsDao studentsDao;

	@Override
	public List<LibraryTransactions> getLibraryTransactionsByStudentId(String id) throws Exception {
		List<LibraryTransactions> listLibraryTransactions = new ArrayList<LibraryTransactions>();
		try {
			listLibraryTransactions = libraryTransactionsRepository.findByStudentId(id);
			if (listLibraryTransactions.isEmpty()) {
				throw new Exception("No Library Transactions Found by Student ID : " + id);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLibraryTransactions;
	}

	@Override
	public List<LibraryTransactions> getLibraryTransactionsByBookId(String id) throws Exception {
		List<LibraryTransactions> listLibraryTransactions = new ArrayList<LibraryTransactions>();
		try {
			listLibraryTransactions = libraryTransactionsRepository.findByBookId(id);
			if (listLibraryTransactions.isEmpty()) {
				throw new Exception("No Library Transactions Found by Book ID : " + id);
			}
		} catch (Exception e) {
			throw e;
		}
		return listLibraryTransactions;
	}

	@Override
	public List<LibraryTransactions> getAllLibraryTransactions() throws Exception {
		List<LibraryTransactions> libraryTransactionsList = new ArrayList<LibraryTransactions>();
		try {
			libraryTransactionsList = libraryTransactionsRepository.findAll();
			if (libraryTransactionsList.isEmpty()) {
				throw new Exception("Library Transactions List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return libraryTransactionsList;
	}

	@Transactional
	@Override
	public LibraryTransactions addLibraryTransactions(LibraryTransactionsSaveParam addLibraryTransactions)
			throws StockBukuHabisException, Exception {
		Books book = new Books();
		Students student = new Students();
		LibraryTransactions libraryTransaction = new LibraryTransactions();
		try {
			book = booksDao.getBookByTitle(addLibraryTransactions.getTitle());
			if(book == null) {
				throw new Exception("No Book by " + addLibraryTransactions.getTitle() + "Found");
			}
			student = studentsDao.findStudentByName(addLibraryTransactions.getName());
			if(student == null) {
				throw new Exception("No Student Found by Name "+ addLibraryTransactions.getName());
			}
			if (book.getStock() < 1) {
				throw new StockBukuHabisException();
			}
				libraryTransaction.setStudent(student);
				libraryTransaction.setLendDate(addLibraryTransactions.getLendDate());
				libraryTransaction.setDueDate(addLibraryTransactions.getDueDate());
				libraryTransaction.setPrice(book.getPrice());
				libraryTransaction.setStatus(addLibraryTransactions.getStatus());
				book.setStock(book.getStock()-1);
				booksDao.updateBook(book.getId(), book);
				//booksDao.updateBookStock(book.getId());
				libraryTransaction.setBook(book);
				libraryTransactionsRepository.save(libraryTransaction);
				return libraryTransaction;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteLibraryTransactionById(String id) throws Exception {
		Optional<LibraryTransactions> libraryTransactionsOpt;
		try {
			libraryTransactionsOpt = libraryTransactionsRepository.findById(id);
			if (!libraryTransactionsOpt.isPresent()) {
				throw new Exception("No Library Transactions List Found By ID :" + id);
			} 
			libraryTransactionsRepository.delete(libraryTransactionsOpt.get());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public LibraryTransactions updateLibraryTransaction(String id,LibraryTransactionsSaveParam updateLibraryTransaction) throws Exception {
		Optional<LibraryTransactions> libraryTransactionOpt;
		Students student = new Students();
		Books book = new Books();
		try {
			libraryTransactionOpt = libraryTransactionsRepository.findById(id);
			student = studentsDao.findStudentByName(updateLibraryTransaction.getName());
			book = booksDao.getBookByTitle(updateLibraryTransaction.getTitle());
			if(!libraryTransactionOpt.isPresent()) {
				throw new Exception("Library Transactions by ID : "+id+" is Not Found");
			}
			if(student == null) {
				throw new Exception("Students by "+updateLibraryTransaction.getName()+" is Not Found");
			}
			if(book == null) {
				throw new Exception("Books by "+updateLibraryTransaction.getTitle()+" is Not Found");
			}
			LibraryTransactions libraryTransaction = libraryTransactionOpt.get();
			libraryTransaction.setStudent(student);
			libraryTransaction.setBook(book);
			libraryTransaction.setLendDate(updateLibraryTransaction.getLendDate());
			libraryTransaction.setDueDate(updateLibraryTransaction.getDueDate());
			libraryTransaction.setPrice(book.getPrice());
			libraryTransaction.setStatus(updateLibraryTransaction.getStatus());
			return libraryTransactionsRepository.save(libraryTransaction);	
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<LibraryTransactions> findPaging(int page) throws Exception {
		Pageable pageable = new PageRequest(page,5);
		Page<LibraryTransactions> paging = libraryTransactionsRepository.findPage(pageable);
		return paging;
	}

}
