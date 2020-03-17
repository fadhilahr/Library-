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

import com.sti.bootcamp.exerciselibrary.dto.StudentsDto;
import com.sti.bootcamp.exerciselibrary.model.Students;
import com.sti.bootcamp.exerciselibrary.service.StudentsDao;
import com.sti.bootcamp.exerciselibrary.util.CommonResponse;
import com.sti.bootcamp.exerciselibrary.util.CommonResponseGenerator;
import com.sti.bootcamp.exerciselibrary.util.JsonUtil;

@RestController
@RequestMapping(path="",produces = "application/json; charset=UTF-8")
public class StudentsController {

	@Autowired
	private StudentsDao studentsDao;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private CommonResponseGenerator comGen;
	
	
	private static final String STUDENTS_PATH_VARIABLE_ID = "id";
	private static final String STUDENTS_PATH_VARIABLE_NAME = "name";
	private static final String STUDENTS_ADDR ="/students";
	private static final String STUDENTS_PAGE_ADDR = STUDENTS_ADDR + "/page";
	private static final String STUDENTS_BY_ID_ADDR = STUDENTS_ADDR + "/{" + STUDENTS_PATH_VARIABLE_ID + "}";
	private static final String STUDENTS_BY_NAME_ADDR =STUDENTS_ADDR+STUDENTS_PATH_VARIABLE_NAME+ "/{" + STUDENTS_PATH_VARIABLE_NAME + "}";
	
	
	@GetMapping(STUDENTS_ADDR)
	public String getAllStudents() throws Exception{
		List<StudentsDto> listStudentsDto = studentsDao.getAllStudents().stream().map(s->modelMapper.map(s, StudentsDto.class)).collect(Collectors.toList());
		CommonResponse<List<StudentsDto>> response = comGen.generateCommonResponse(listStudentsDto);
		return JsonUtil.generateJson(response);
	}
	
	@PostMapping(STUDENTS_ADDR)
	public String addStudents(@RequestBody Students newstudent) throws Exception{
		StudentsDto studentsDto =  modelMapper.map(studentsDao.addStudent(newstudent), StudentsDto.class);
		CommonResponse<StudentsDto> response = comGen.generateCommonResponse(studentsDto);
		return JsonUtil.generateJson(response);
	}
	
	@GetMapping(STUDENTS_BY_NAME_ADDR)
	public String getStudentsByNameLike(@PathVariable(value="name") String name) throws Exception{
		List<StudentsDto> listStudentsDto = studentsDao.findStudentsByNameLike(name).stream().map(s->modelMapper.map(s, StudentsDto.class)).collect(Collectors.toList());
		CommonResponse<List<StudentsDto>> response = comGen.generateCommonResponse(listStudentsDto);
		return JsonUtil.generateJson(response);
	}
	
	@PutMapping(STUDENTS_BY_ID_ADDR)
	public String updateStudent(@PathVariable(value="id") String id,@RequestBody Students updateStudent) throws Exception{
		StudentsDto studentsDto = modelMapper.map(studentsDao.updateStudent(id, updateStudent), StudentsDto.class);
		CommonResponse<StudentsDto> response = comGen.generateCommonResponse(studentsDto);
		return JsonUtil.generateJson(response);
	}

	@DeleteMapping(STUDENTS_BY_ID_ADDR)
	public String deleteStudentsById(@PathVariable("id") String id) throws Exception{
		studentsDao.deleteStudentById(id);
		return "Success";
	}
	
	@GetMapping(STUDENTS_PAGE_ADDR)
	public String getPage(@RequestParam(name="page",defaultValue = "0") int page) throws Exception{
		Page<Students> pageStudents = studentsDao.findPaging(page);
		CommonResponse<Page<Students>> response = comGen.generateCommonResponse(pageStudents);
		return JsonUtil.generateJson(response);
	}
	
	@ExceptionHandler
	public String exception(Exception e) throws Exception{
		CommonResponse<String> resp = comGen.generateCommonResponse("X01", e.getMessage(), String.class);
		return JsonUtil.generateJson(resp);
	}
}
