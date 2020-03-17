package com.sti.bootcamp.exerciselibrary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.exerciselibrary.model.LibraryTransactions;

@Repository
public interface LibraryTransactionsRepository extends JpaRepository<LibraryTransactions, String> {

	List<LibraryTransactions> findByStudentId(String id);

	List<LibraryTransactions> findByBookId(String id);

	@Query(value = "select u from LibraryTransactions u", countQuery = "select count(u) from LibraryTransactions u")
	Page<LibraryTransactions> findPage(Pageable pageable);
}
