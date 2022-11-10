package com.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Books;
import com.user.entity.SubscribedBooks;
import com.user.service.ISubscribedBooksService;

@RestController
@CrossOrigin(origins= {"*"})
@RequestMapping("/reader")
public class ReaderController {
	
	@Autowired
	RestTemplate restTemp;

	
	@Autowired
	ISubscribedBooksService subscribedBooksService;
	
	@GetMapping("/books")
	public Object getMyBooks() {
		Object myBooks = restTemp.getForObject("http://localhost:9092/digitalbooks/allbooks", Object.class);
		return myBooks;
	}
	
	@PostMapping("{bookId}/subscribe")
	public SubscribedBooks subscribingBook(@PathVariable("id") Integer readerId, @PathVariable("bookId") Integer bookId, @RequestBody SubscribedBooks books) {
		Books myBooks = restTemp.getForObject("http://localhost:9092/digitalbooks/read/" + bookId, Books.class);
		SubscribedBooks newBook = new SubscribedBooks();
		newBook.setUserId(readerId);
		newBook.setBookId(bookId);
		java.time.LocalDate date = java.time.LocalDate.now();
		newBook.setSubscribedDate(date);
		return subscribedBooksService.addSubscribedBook(newBook);
		
	}
	
	@GetMapping("/books/{subscription-id}/read")
	public SubscribedBooks myBookById(@PathVariable Integer id) {
		SubscribedBooks bookById = subscribedBooksService.subscribedId(id);
		return bookById;
	}
	
	
	@PostMapping("/books/{subscription-id}/cancel-subscription")
	public ResponseEntity<SubscribedBooks> cancelingBook(@PathVariable Integer id) {
		ResponseEntity<SubscribedBooks> bookCancel = new ResponseEntity<>(HttpStatus.OK);
		try {
			subscribedBooksService.cancelSubscription(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			bookCancel = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return bookCancel;
	}
	
	@GetMapping("/search") 
	public Object searchBookByAll(@PathVariable Integer id,@RequestParam("category")String category, @RequestParam("authorName") String authorName, @RequestParam("price") Double price, @RequestParam("publisher")String publisher) {
		String url = "http://localhost:9092/digitalbooks" + id + "/search?category={category}&authorName={authorName}&price={price}&publisher={publisher}";
		Object searchHistory = restTemp.getForObject(url,Object.class);
		return searchHistory;
	}
}
