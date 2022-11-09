package com.book.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.entity.Books;
import com.book.exception.BookNotFoundException;
import com.book.repository.IBookRepo;
import com.book.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	
	@Autowired
	IBookRepo bookRepo;

	
	@Override
	public Integer addBook(Books book) {
		Books bookToAdd = bookRepo.save(book);
		return bookToAdd.getBookId();
	}

	@Override
	public Optional<Books> getBooks(Integer id) {
		return bookRepo.findById(id);
	}

	@Override
	public List<Books> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public void deleteBook(Integer id) {
		bookRepo.deleteById(id);
		
	}

	@Override
	public Books updateBook(Books book, Integer id) {
		Books existedBook = bookRepo.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Books", "id", id));
		
		existedBook.setBookName(book.getBookName());
		existedBook.setBookImage(book.getBookImage());
		existedBook.setBookCategory(book.getBookCategory());
		existedBook.setBookPrice(book.getBookPrice());
		existedBook.setBookPublishedDate(book.getBookPublishedDate());
		existedBook.setBookPublisher(book.getBookPublisher());
		existedBook.setBookStatus(book.isBookStatus());
		
		bookRepo.save(existedBook);
		return existedBook;
	}


	@Override
	public Iterable<Books> searchBooks() {
		Iterable<Books> books = bookRepo.findAll();
		if (null != books) {
			return books;

		} else {
			throw new IllegalArgumentException("No book found!");
		}
	}

//	@Override
//	public List<Books> subscribedBooks(Integer id) {
//		List<Integer> bid = bookRepo.findByAuthorId(id);
//		List<Books> books = new ArrayList<Books>();
//		for(int i = 0 ; i < bid.size(); i++) {
//			Optional<Books> book = bookRepo.findById(bid.get(i));
//			if(book.isPresent()) {
//				books.add(book.get());
//			}
//		}
//		return books;
//	}
}
	
	
