package com.sti.bootcamp.exerciselibrary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.exerciselibrary.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, String> {

	Students findByName(String name);

	List<Students> findByNameLike(String name);

	@Query(value = "select u from Students u", countQuery = "select count(u) from Students u")
	Page<Students> findPage(Pageable pageable);
}
