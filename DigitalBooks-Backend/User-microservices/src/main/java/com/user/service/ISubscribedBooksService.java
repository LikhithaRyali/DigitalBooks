package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.entity.SubscribedBooks;

public interface ISubscribedBooksService {

	public List<SubscribedBooks> addSubscribedBook(List <SubscribedBooks> subscribedBook);
	
	Optional<SubscribedBooks> getSubcribedBookById(Integer id);
	
	public void cancelSubscription(Integer id);
}
