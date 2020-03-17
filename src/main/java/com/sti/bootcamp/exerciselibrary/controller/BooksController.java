package com.sti.bootcamp.exerciselibrary.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.exerciselibrary.dto.BooksDto;
import com.sti.bootcamp.exerciselibrary.model.Books;
import com.sti.bootcamp.exerciselibrary.service.BooksDao;
import com.sti.bootcamp.exerciselibrary.util.CommonResponse;
import com.sti.bootcamp.exerciselibrary.util.CommonResponseGenerator;
import com.sti.bootcamp.exerciselibrary.util.JsonUtil;

@RestController
@RequestMapping(path="",produces = "application/json; charset=UTF-8")
public class BooksController {
	
	@Autowired
	BooksDao booksDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;

	private static final String BOOKS_PATH_VARIABLE_ID = "id";
	private static final String BOOKS_PATH_VARIABLE_TITLE = "title";
	private static final String BOOKS_PATH_VARIABLE_PUBLISHER = "publisher";
	private static final String BOOKS_ADDR = "/books";
	private static final String LIKE = "/like";
	private static final String BOOKS_BY_ID_ADDR = BOOKS_ADDR + "/{" + BOOKS_PATH_VARIABLE_ID + "}";
	private static final String BOOKS_PAGE_ADDR = BOOKS_ADDR + "/page";
	private static final String BOOKS_BY_TITLE_ADDR = BOOKS_ADDR+"/title/{"+BOOKS_PATH_VARIABLE_TITLE+"}";
	private static final String BOOKS_BY_PUBLISHER_ADDR = BOOKS_ADDR+"/publisher/{"+BOOKS_PATH_VARIABLE_PUBLISHER+"}";
	private static final String BOOKS_BY_TITLE_LIKE_ADDR = BOOKS_ADDR+LIKE+"{"+BOOKS_PATH_VARIABLE_TITLE+"}";
	private static final String BOOKS_PUBLISHER_LIKE_ADDR = BOOKS_ADDR+LIKE+"/publisher/{"+BOOKS_PATH_VARIABLE_PUBLISHER+"}";
	
	
	
	
	@GetMapping(BOOKS_ADDR)
	@CrossOrigin(origins = "*")
	public String getAllBooks() throws Exception{
		List<BooksDto> listAllBooksDto = booksDao.getAllBooks().stream().map(b->modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
		CommonResponse<List<BooksDto>> response = comGen.generateCommonResponse(listAllBooksDto);
		return JsonUtil.generateJson(response);
	}
	
	@PostMapping(BOOKS_ADDR)
	@CrossOrigin(origins = "*")
	public String addBook(@RequestBody Books book) throws Exception{
		BooksDto booksDto = modelMapper.map(booksDao.addBook(book), BooksDto.class);
		CommonResponse<BooksDto> response = comGen.generateCommonResponse(booksDto);
		return JsonUtil.generateJson(response);
	}
		
	@GetMapping(BOOKS_BY_TITLE_ADDR)
	public String getBookByTitle(@PathVariable("title") String title) throws Exception{
		BooksDto booksDto = modelMapper.map(booksDao.getBookByTitle(title), BooksDto.class);
		CommonResponse<BooksDto> response = comGen.generateCommonResponse(booksDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(BOOKS_BY_PUBLISHER_ADDR)
	public String getBookByPublisher(@PathVariable("publisher") String publisher) throws Exception{
		BooksDto booksDto = modelMapper.map(booksDao.getBookByPublisher(publisher), BooksDto.class);
		CommonResponse<BooksDto> response = comGen.generateCommonResponse(booksDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(BOOKS_BY_TITLE_LIKE_ADDR)
	public String getBooksByTitleLike(@PathVariable("title") String title) throws Exception{
		List<BooksDto> listBooksDto = booksDao.findBooksByTitleLike(title).stream().map(b->modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
		CommonResponse<List<BooksDto>> response = comGen.generateCommonResponse(listBooksDto);
		return JsonUtil.generateJson(response);
	}

	@GetMapping(BOOKS_PUBLISHER_LIKE_ADDR)
	public String getBooksByPublisherLike(@PathVariable("publisher") String publisher) throws Exception{
		List<BooksDto> listBooksDto = booksDao.findBooksByPublisherLike(publisher).stream().map(b->modelMapper.map(b, BooksDto.class)).collect(Collectors.toList());
		CommonResponse<List<BooksDto>> response = comGen.generateCommonResponse(listBooksDto);
		return JsonUtil.generateJson(response);
	}
	
	@PutMapping(BOOKS_BY_ID_ADDR)
	@CrossOrigin(origins = "*")
	public String updateBook(@PathVariable(value="id") String id, @RequestBody Books updateBook) throws Exception{
		BooksDto booksDto = modelMapper.map(booksDao.updateBook(id, updateBook), BooksDto.class);
		CommonResponse<BooksDto> response = comGen.generateCommonResponse(booksDto);
		return JsonUtil.generateJson(response);
	}

	
	@DeleteMapping(BOOKS_BY_ID_ADDR)
	@CrossOrigin(origins = "*")
	public String deleteBooksById(@PathVariable("id") String id) throws Exception{
		String resp = booksDao.deleteBookById(id);
		CommonResponse<String> response = comGen.generateCommonResponse(resp);
		return JsonUtil.generateJson(response);	
		
	}
	
	@GetMapping(BOOKS_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<Books> pageBooks = booksDao.findPaging(page);
		CommonResponse<Page<Books>> response = comGen.generateCommonResponse(pageBooks);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(BOOKS_BY_ID_ADDR)
	@CrossOrigin(origins = "*")
	public String getBookById(@PathVariable("id") String id) throws Exception{
		BooksDto booksDto = modelMapper.map(booksDao.getBookById(id), BooksDto.class);
		CommonResponse<BooksDto> response = comGen.generateCommonResponse(booksDto);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}

}
