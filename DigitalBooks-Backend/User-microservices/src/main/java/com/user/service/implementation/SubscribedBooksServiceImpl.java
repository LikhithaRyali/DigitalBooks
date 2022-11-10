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
	public void cancelSubscription(Integer id) {
		subscribedBooksRepo.deleteById(id);
		
	}

	@Override
	public SubscribedBooks addSubscribedBook(SubscribedBooks subscribedBook) {
		SubscribedBooks book = subscribedBooksRepo.save(subscribedBook);
		return book;
	}

	@Override
	public Optional<SubscribedBooks> getSubcribedUserId(Integer uid) {
		return subscribedBooksRepo.findById(uid);
	}

	@Override
	public SubscribedBooks subscribedId(Integer sId) {
		SubscribedBooks subscriptionId = subscribedBooksRepo.findBySubscriptionId(sId);
		return subscriptionId;
	}
	
	
	
	
}
