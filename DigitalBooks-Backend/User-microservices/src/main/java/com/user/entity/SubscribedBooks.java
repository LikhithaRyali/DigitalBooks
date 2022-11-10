package com.user.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubscribedBooks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer subscriptionId;
	
	Integer userId;
	Integer bookId;
	LocalDate subscribedDate ;
	
	
	public Integer getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public LocalDate getSubscribedDate() {
		return subscribedDate;
	}
	public void setSubscribedDate(LocalDate subscribedDate) {
		this.subscribedDate = subscribedDate;
	}
	public SubscribedBooks(Integer subscriptionId, Integer userId, Integer bookId, LocalDate subscribedDate) {
		super();
		this.subscriptionId = subscriptionId;
		this.userId = userId;
		this.bookId = bookId;
		this.subscribedDate = subscribedDate;
	}
	public SubscribedBooks() {
		super();
	}
	
}
