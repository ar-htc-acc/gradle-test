package com.ar.services;

import java.util.List;

import com.ar.models.Book;

public interface TestService {

	public List<Book> getAll(Boolean criteria);

	public void saveBook(String isbn, String title);
}
