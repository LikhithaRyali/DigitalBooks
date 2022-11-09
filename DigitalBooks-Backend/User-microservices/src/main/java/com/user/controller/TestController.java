package com.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('GUEST') or hasRole('READER') or hasRole('AUTHOR')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/reader")
	@PreAuthorize("hasRole('READER')")
	public String readerAccess() {
		return "Reader Board.";
	}

	@GetMapping("/author")
	@PreAuthorize("hasRole('AUTHOR')")
	public String authorAccess() {
		return "Author Board.";
	}
}
