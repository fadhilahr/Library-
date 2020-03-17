package com.sti.bootcamp.exerciselibrary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sti.bootcamp.exerciselibrary.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, String> {

	Books findByTitle(String title);

	Books findByPublisher(String publisher);

	List<Books> findByTitleLike(String title);

	List<Books> findByPublisherLike(String publisher);

	@Query(value = "SELECT u FROM Books u", countQuery = "SELECT COUNT(u) FROM Books u")
	Page<Books> findPage(Pageable pageable);
}
