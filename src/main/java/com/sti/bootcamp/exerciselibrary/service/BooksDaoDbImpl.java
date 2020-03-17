package com.sti.bootcamp.exerciselibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.bootcamp.exerciselibrary.model.Books;
import com.sti.bootcamp.exerciselibrary.repository.BooksRepository;

public class BooksDaoDbImpl implements BooksDao {

	@Autowired
	private BooksRepository booksRepository;


	@Override
	public List<Books> getAllBooks() throws Exception {
		List<Books> bookList = new ArrayList<Books>();			
		try {
			bookList = booksRepository.findAll();
			if (bookList.isEmpty()) {
				throw new Exception("Books List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return bookList;
	}

	@Override
	public Books addBook(Books book) throws Exception{
		try {
			Optional<Books> exist = booksRepository.findById(book.getId());
			if(exist.isPresent()) {
				throw new Exception("Book Already Exist");
			}			
		} catch (Exception e) {
			throw e;
		}
		return booksRepository.save(book);
	}

	@Override
	public Books getBookByTitle(String title) throws Exception {
		Books book = new Books();
		try {
			book = booksRepository.findByTitle(title);
			if (book == null) {
				throw new Exception("No Book by " + title + "Found");
			}
		} catch (Exception e) {
			throw e;
		}
		return book;
	}

	@Override
	public Books getBookByPublisher(String publisher) throws Exception {
		Books book = new Books();
		try {
			book = booksRepository.findByPublisher(publisher);
			if (book == null) {
				throw new Exception("No Book by " + publisher + "Found");
			}
		} catch (Exception e) {
			throw e;
		}
		return book;
	}

	@Override
	public List<Books> findBooksByTitleLike(String title) throws Exception {
		List<Books> booksList = new ArrayList<Books>();
		try {
			booksList = booksRepository.findByTitleLike("%" + title + "%");
			if (booksList == null) {
				throw new Exception("No Book Found Contain " + title + " as Title");
			}
		} catch (Exception e) {
			throw e;
		}
		return booksList;
	}

	@Override
	public List<Books> findBooksByPublisherLike(String publisher) throws Exception {
		List<Books> booksList = new ArrayList<Books>();
		try {
			booksList = booksRepository.findByPublisherLike("%" + publisher + "%");
			if (booksList == null) {
				throw new Exception("No Book Found Contain " + publisher + " as Publisher");
			}
		} catch (Exception e) {
			throw e;
		}
		return booksList;
	}

	@Override
	public Books updateBook(String id, Books updateBook) throws Exception {
		Optional<Books> bookOpt;
		Books book = new Books();
		try {
			bookOpt = booksRepository.findById(id);
			if (!bookOpt.isPresent()) {
				throw new Exception("No Books Found by ID =" + id );			
			}
			book = bookOpt.get();
			book.setTitle(updateBook.getTitle());
			book.setPublisher(updateBook.getPublisher());
			book.setPrice(updateBook.getPrice());
			book.setStock(updateBook.getStock());			
		} catch (Exception e) {
			throw e;
		}
		return booksRepository.save(book);
	}


	@Override
	public String deleteBookById(String id) throws Exception {
		Optional<Books> bookOpt;
		String response = "Book Deleted";
		try {
			bookOpt = booksRepository.findById(id);
			if (bookOpt.isPresent()) {
				Books book = bookOpt.get();
				booksRepository.delete(book);
			} else {
				throw new Exception("No Books Found by " + id + "ID");
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Page<Books> findPaging(int page) throws Exception {
		Pageable pageable = new PageRequest(page, 5);
		Page<Books> paging = booksRepository.findPage(pageable);
		return paging;
	}

	@Override
	public Books getBookById(String id) throws Exception {
		Optional<Books> bookOpt;
		Books book = new Books();
		try {
			bookOpt = booksRepository.findById(id);
			if (!bookOpt.isPresent()) {
				throw new Exception ("No Books Found By ID:" + id);
			}
			
			book = bookOpt.get();
			
		} catch (Exception e) {
			throw e;
		}
		return book;
	}

}
