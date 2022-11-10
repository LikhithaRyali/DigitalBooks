package com.user.service;

import java.util.Optional;

import com.user.entity.SubscribedBooks;

public interface ISubscribedBooksService {

	public SubscribedBooks addSubscribedBook(SubscribedBooks subscribedBook);
	
	
	//public SubscribedBooks subscribedUserId(Integer uid);
	Optional<SubscribedBooks> getSubcribedUserId(Integer id);
	
	public SubscribedBooks subscribedId(Integer sid);
	
	public void cancelSubscription(Integer id);
}
