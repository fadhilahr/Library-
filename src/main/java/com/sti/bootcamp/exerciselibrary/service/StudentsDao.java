package com.sti.bootcamp.exerciselibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sti.bootcamp.exerciselibrary.model.Students;

public interface StudentsDao {
	List<Students> getAllStudents() throws Exception;
	Students addStudent(Students student) throws Exception;
	Students findStudentByName(String name) throws Exception;
	List<Students> findStudentsByNameLike(String name) throws Exception;
	Students updateStudent(String id,Students updateStudent) throws Exception;
	void deleteStudentById(String id) throws Exception;
	Page<Students> findPaging(int page) throws Exception;
}
