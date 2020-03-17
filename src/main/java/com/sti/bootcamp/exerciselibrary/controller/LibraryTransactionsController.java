package com.sti.bootcamp.exerciselibrary.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.sti.bootcamp.exerciselibrary.dto.LibraryTransactionsDto;
import com.sti.bootcamp.exerciselibrary.model.LibraryTransactions;
import com.sti.bootcamp.exerciselibrary.saveparam.LibraryTransactionsSaveParam;
import com.sti.bootcamp.exerciselibrary.service.LibraryTransactionsDao;
import com.sti.bootcamp.exerciselibrary.util.CommonResponse;
import com.sti.bootcamp.exerciselibrary.util.CommonResponseGenerator;
import com.sti.bootcamp.exerciselibrary.util.JsonUtil;

@RestController
@RequestMapping(path="",produces = "application/json; charset=UTF-8")
public class LibraryTransactionsController {
	
	@Autowired
	LibraryTransactionsDao libraryTransactionsDao;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private CommonResponseGenerator comGen;
	
	private static final String LIBRARY_TRANSACTIONS_ADDR = "/librarytransactions";
	private static final String LIBRARY_TRANSACTIONS_PATH_VARIABLE_ID = "/{id}";
	private static final String LIBRARY_TRANSACTIONS_BY_ID_ADDR = LIBRARY_TRANSACTIONS_ADDR+LIBRARY_TRANSACTIONS_PATH_VARIABLE_ID;
	private static final String LIBRARY_TRANSACTIONS_BY_BOOKS_ID_ADDR = LIBRARY_TRANSACTIONS_ADDR+"/books"+LIBRARY_TRANSACTIONS_PATH_VARIABLE_ID;
	private static final String LIBRARY_TRANSACTIONS_BY_STUDENTS_ID_ADDR = LIBRARY_TRANSACTIONS_ADDR+"/students"+LIBRARY_TRANSACTIONS_PATH_VARIABLE_ID;
	private static final String LIBRARY_TRANSACTIONS_PAGE_ADDR = LIBRARY_TRANSACTIONS_ADDR+"/page";
	
	@GetMapping(LIBRARY_TRANSACTIONS_ADDR)
	public String getAllLibraryTransactions() throws Exception{
		List<LibraryTransactionsDto> listLibraryTrnsactionsDto = libraryTransactionsDao.getAllLibraryTransactions().stream().map(l->modelMapper.map(
				l, LibraryTransactionsDto.class)).collect(Collectors.toList());
		CommonResponse<List<LibraryTransactionsDto>> response = comGen.generateCommonResponse(listLibraryTrnsactionsDto);
		return JsonUtil.generateJson(response);
	}
	
	@PostMapping(LIBRARY_TRANSACTIONS_ADDR)
	public String addLibraryTransactions(@RequestBody LibraryTransactionsSaveParam addLibrarytransaction) throws Exception{
		LibraryTransactionsDto libraryTransactionsDto = modelMapper.map(libraryTransactionsDao.addLibraryTransactions(addLibrarytransaction), LibraryTransactionsDto.class);
		CommonResponse<LibraryTransactionsDto> response = comGen.generateCommonResponse(libraryTransactionsDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(LIBRARY_TRANSACTIONS_BY_STUDENTS_ID_ADDR)
	public String getLibraryTransactionsByStudentId(@PathVariable("id") String id) throws Exception{
		List<LibraryTransactionsDto> listLibrartTransactionsDto = libraryTransactionsDao.getLibraryTransactionsByStudentId(id).stream().map(l->modelMapper.map(l, LibraryTransactionsDto.class)).collect(Collectors.toList());
		CommonResponse<List<LibraryTransactionsDto>> response = comGen.generateCommonResponse(listLibrartTransactionsDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(LIBRARY_TRANSACTIONS_BY_BOOKS_ID_ADDR)
	public String getLibraryTransactionsByBookId(@PathVariable("id") String id) throws Exception{
		List<LibraryTransactionsDto> listLibraryTransactionsDto = libraryTransactionsDao.getLibraryTransactionsByBookId(id).stream().map(l->modelMapper.map(l, LibraryTransactionsDto.class)).collect(Collectors.toList());
		CommonResponse<List<LibraryTransactionsDto>> response = comGen.generateCommonResponse(listLibraryTransactionsDto);
		return JsonUtil.generateJson(response);
	}
	
	@DeleteMapping(LIBRARY_TRANSACTIONS_BY_ID_ADDR)
	public String deleteLibraryTransaction(@PathVariable("id") String id) throws Exception{
		libraryTransactionsDao.deleteLibraryTransactionById(id);
		return "Success";
	}
	
	@PutMapping(LIBRARY_TRANSACTIONS_BY_ID_ADDR)
	public String updateLibraryTransaction(@PathVariable(value="id") String id,@RequestBody LibraryTransactionsSaveParam updateLibraryTransaction) throws Exception{
		LibraryTransactionsDto libraryTransactionsDto = modelMapper.map(libraryTransactionsDao.updateLibraryTransaction(id, updateLibraryTransaction), LibraryTransactionsDto.class);
		CommonResponse<LibraryTransactionsDto> response = comGen.generateCommonResponse(libraryTransactionsDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(LIBRARY_TRANSACTIONS_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0")int page) throws Exception{
		Page<LibraryTransactions> pageLibraryTransactions = libraryTransactionsDao.findPaging(page);
		CommonResponse<Page<LibraryTransactions>> response = comGen.generateCommonResponse(pageLibraryTransactions);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
	
}
