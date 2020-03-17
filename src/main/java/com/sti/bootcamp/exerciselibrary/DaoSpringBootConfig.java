package com.sti.bootcamp.exerciselibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sti.bootcamp.exerciselibrary.service.BooksDao;
import com.sti.bootcamp.exerciselibrary.service.BooksDaoDbImpl;
import com.sti.bootcamp.exerciselibrary.service.LibraryTransacstionsDaoDbImpl;
import com.sti.bootcamp.exerciselibrary.service.LibraryTransactionsDao;
import com.sti.bootcamp.exerciselibrary.service.StudentsDao;
import com.sti.bootcamp.exerciselibrary.service.StudentsDaoDbImpl;
import com.sti.bootcamp.exerciselibrary.util.CommonResponseGenerator;
import com.sti.bootcamp.exerciselibrary.util.CommonStatus;

@Configuration
public class DaoSpringBootConfig {

	@Bean
	public BooksDao booksDao() {
		return new BooksDaoDbImpl();
	}
	
	@Bean
	public StudentsDao studentsDao() {
		return new StudentsDaoDbImpl();
	}
	
	@Bean
	public LibraryTransactionsDao libraryTransactionsDao() {
		return new LibraryTransacstionsDaoDbImpl();
	}
	
	@Bean
	public CommonResponseGenerator commonResponseGenerator() {
		return new CommonResponseGenerator();
	}
	
	@Bean
	public CommonStatus commonStatus() {
		return new CommonStatus();
	}
	
	
}
