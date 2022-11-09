package com.book.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Books;
import com.book.service.IBookService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/digitalbooks")
public class BookController {

	@Autowired
	private IBookService bookService;

	@RequestMapping("/greetings") 
	@ResponseBody
	public String hello() { 
		return "Hello World This is my Digital Books Spring MicroServices application";
	}
	
	
	@PostMapping("/addbook")
	Integer addingBook(@RequestBody Books book) {
		Integer bId = bookService.addBook(book);
		return bId;
	}
	
	@GetMapping("/read/{book-id}")
	public Optional<Books> getBookById(@PathVariable("book-id") Integer id) {
		Optional<Books> book = bookService.getBooks(id);
		return book;
	}
	
	@GetMapping("/allbooks")
	public List<Books> getBooks() {
		return bookService.getAllBooks();
	}
	
	@DeleteMapping("/remove/{book-id}")
	public ResponseEntity<Books> deletingBook(@PathVariable("book-id") Integer id) {
		ResponseEntity<Books> bookEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			bookService.deleteBook(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			bookEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return bookEntity;
	}
	
	@PutMapping("/{book-id}")
	public ResponseEntity<Books> updatingBook(@PathVariable("book-id") Integer id, @RequestBody Books book) {
		return new ResponseEntity<Books>(bookService.updateBook(book, id), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public List<Books> searchBooksBy(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "author", required = false) String authorName,
			@RequestParam(value = "price", required = false) Double price,
			@RequestParam(value = "publisher", required = false) String publisher) {
		List<Books> bookList = Streamable.of(bookService.searchBooks()).toList();
		List<Books> sortedList = null;
		if (null != bookList) {
			sortedList = bookList.stream().filter(b -> {
				if (category == null)
					return true;
				return b.getBookCategory().toString().equalsIgnoreCase(category);
			}).filter(b -> {
				if (authorName == null)
					return true;
				return b.getAuthorName().toString().equalsIgnoreCase(authorName);
			}).filter(b -> {
				if (publisher == null)
					return true;
				return b.getBookPublisher().toString().equalsIgnoreCase(publisher);
			}).filter(b -> {
				if (price == null)
					return true;
				return b.getBookPrice() == price;
			}).collect(Collectors.toList());
		}

		return sortedList;
	}
	
}
