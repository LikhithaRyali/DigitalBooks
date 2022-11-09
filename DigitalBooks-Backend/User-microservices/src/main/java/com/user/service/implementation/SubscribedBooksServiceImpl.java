package com.user.service.implementation;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.SubscribedBooks;
import com.user.repository.ISubscribedBookRepo;
import com.user.service.ISubscribedBooksService;


@Service
public class SubscribedBooksServiceImpl implements ISubscribedBooksService {

	
	@Autowired
	ISubscribedBookRepo subscribedBooksRepo;

	@Override
	public List<SubscribedBooks> addSubscribedBook(List<SubscribedBooks> subscribedBook) {
		List<SubscribedBooks> book = subscribedBooksRepo.saveAll(subscribedBook);
		return book;
	}

	@Override
	public Optional<SubscribedBooks> getSubcribedBookById(Integer id) {
		return subscribedBooksRepo.findById(id);
	}

	@Override
	public void cancelSubscription(Integer id) {
		subscribedBooksRepo.deleteById(id);
		
	}
	
	
	
	
}
