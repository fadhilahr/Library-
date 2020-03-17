package com.sti.bootcamp.exerciselibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.bootcamp.exerciselibrary.model.Books;

public interface BooksDao {
	List<Books> getAllBooks() throws Exception;
	Books addBook(Books book) throws Exception;
	Books getBookByTitle(String title) throws Exception;
	Books getBookByPublisher(String publisher) throws Exception;
	List<Books> findBooksByTitleLike(String title) throws Exception;
	List<Books> findBooksByPublisherLike(String publisher) throws Exception;
	Books updateBook(String id,Books updateBook) throws Exception;
	Books getBookById(String id)throws Exception;
	//Books updateBookStock(String id);
	String deleteBookById(String id) throws Exception;
	Page<Books> findPaging(int page) throws Exception;
}
