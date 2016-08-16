package com.ar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book getByIsbn(String isbn);

}
