package com.book.service;

import java.util.List;
import java.util.Optional;

import com.book.entity.Books;

public interface IBookService {
	
	public Iterable<Books> searchBooks();
	
	Integer addBook(Books book);
	
	Optional<Books> getBooks(Integer id);
	
	public List<Books> getAllBooks();
	
	public void deleteBook(Integer id);
	
	Books updateBook(Books book, Integer id);
	
//	public List<Books> subscribedBooks(Integer id);
	
	
}
