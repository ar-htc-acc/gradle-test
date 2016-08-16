package com.ar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.models.Book;
import com.ar.repositories.BookRepository;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAll(Boolean criteria) {
		
		if (criteria) {			
			System.out.println("TRUE is triggered!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		} else {
			System.out.println("FALSE is triggered!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		
		
		return bookRepository.findAll();
	}

	@Override
	public void saveBook(String isbn, String title) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		
		bookRepository.save(book);
	}

}
