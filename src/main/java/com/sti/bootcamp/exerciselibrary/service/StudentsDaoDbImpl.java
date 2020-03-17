package com.sti.bootcamp.exerciselibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.sti.bootcamp.exerciselibrary.model.Students;
import com.sti.bootcamp.exerciselibrary.repository.StudentsRepository;

public class StudentsDaoDbImpl implements StudentsDao{

	@Autowired
	private StudentsRepository studentsRepository;
	
	@Override
	public List<Students> getAllStudents() throws Exception {
		List<Students> listStudent = new ArrayList<Students>();
		try {
			listStudent = studentsRepository.findAll();
			if(listStudent.isEmpty()) {
				throw new Exception("Students List is Empty");
			}
		} catch (Exception e) {
			throw e;
		}
		return listStudent;
	}

	@Override
	public Students addStudent(Students student) throws Exception{
		Optional<Students> studentOpt;
		try {
			studentOpt = studentsRepository.findById(student.getId());
			if(!studentOpt.isPresent()) {
				return studentsRepository.save(student);	
			}else {
				throw new Exception("Data Already Exist ");
			}
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public List<Students> findStudentsByNameLike(String name) throws Exception{
		List<Students> listStudents = new ArrayList<Students>();
		try {
			listStudents = studentsRepository.findByNameLike("%"+name+"%");
			if(listStudents.isEmpty()) {
				throw new Exception("No Student Found By Name : "+name);
			}
		} catch (Exception e) {
			throw e;
		}
		return listStudents;
	}

	@Override
	public Students updateStudent(String id, Students updateStudent) throws Exception {
		Optional<Students> studentOpt;
		Students student = new Students();
		try {
			studentOpt = studentsRepository.findById(id);
			if(studentOpt.isPresent()) {
				student = studentOpt.get();
				student.setName(updateStudent.getName());
				student.setNik(updateStudent.getNik());
				return studentsRepository.save(student);
			}else {
				throw new Exception("No Students Found By ID : "+id);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Students findStudentByName(String name) throws Exception {
		Students student = new Students();
		try {
			student = studentsRepository.findByName(name);
			if(student==null) {
				throw new Exception("No Student Found by Name "+ name);
			}
		} catch (Exception e) {
			throw e;
		}
		return student;
	}

	@Override
	public void deleteStudentById(String id) throws Exception{
		Optional<Students> studentOpt;
		try {
			studentOpt = studentsRepository.findById(id);
			if(!studentOpt.isPresent()) {
				throw new Exception("No Student Found by ID : "+id);
			}
			studentsRepository.delete(studentOpt.get());
		} catch (Exception e) {
			throw e;
		}	
	}

	@Override
	public Page<Students> findPaging(int page) throws Exception {
		Pageable pageable = new PageRequest(page,5);
		Page<Students> paging = studentsRepository.findPage(pageable);
		return paging;
	}

	
}
