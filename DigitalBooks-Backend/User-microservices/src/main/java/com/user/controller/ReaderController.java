package com.user.controller;

import java.util.List;
import java.util.Optional;

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
import com.user.entity.SubscribedBooks;
import com.user.service.ISubscribedBooksService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/reader/{id}")
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
	public List<SubscribedBooks> subscribingBook(@PathVariable Integer id, @RequestBody List<SubscribedBooks> books) {
		List<SubscribedBooks> myBooks = restTemp.getForObject("http://localhost:9092/digitalbooks/read/" + id, List.class);
		return subscribedBooksService.addSubscribedBook(myBooks);
		
	}
	
	@GetMapping("/books/{subscription-id}/read")
	public Optional<SubscribedBooks> myBookById(@PathVariable Integer id) {
		Optional<SubscribedBooks> bookById = subscribedBooksService.getSubcribedBookById(id);
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
