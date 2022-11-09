package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.user.entity.SubscribedBooks;
import com.user.jwt.util.JwtUtils;
import com.user.service.ISubscribedBooksService;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RequestMapping("/author/{id}")
public class AuthorController {
	
	
	@Autowired
	RestTemplate restTemp;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	
	@PostMapping("/add/book")
	public Object addBook() {
		Object newBook = restTemp.getForObject("http://localhost:9092/digitalbooks/addbook", Object.class);
		return newBook;
	}
	
	@GetMapping("/mybooks")
	public Object getAllBooks() {
		Object myBooks = restTemp.getForObject("http://localhost:9092/digitalbooks/allbooks", Object.class);
		return myBooks;
	}
	
	@PutMapping("/books/{book-id}")
	public Object changeBook(@PathVariable("id") Integer bookId) {
		Object modifiedBook = restTemp.getForObject("http://localhost:9092/digitalbooks/" + bookId, Object.class);
		return modifiedBook;
	}
	
	@DeleteMapping("/delete/{book-id}")
	public Object removeBook(@PathVariable("id") Integer bookId) {
		Object deleteBook = restTemp.getForObject("http://localhost:9092/digitalbooks/remove" + bookId, Object.class);
		return deleteBook;
	}
	
	
}
