package com.book.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Books implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;
	private String bookName;
	private String bookImage;
	private Double bookPrice;
	private String bookPublisher;
	private String bookCategory;
	private boolean bookStatus;
	private LocalDate bookPublishedDate;
	
	private Integer authorId;
	private String authorName;


	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public boolean isBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(boolean bookStatus) {
		this.bookStatus = bookStatus;
	}
	public LocalDate getBookPublishedDate() {
		return bookPublishedDate;
	}
	public void setBookPublishedDate(LocalDate bookPublishedDate) {
		this.bookPublishedDate = bookPublishedDate;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Books(Integer bookId, String bookName, String bookImage, Double bookPrice, String bookPublisher,
			String bookCategory, boolean bookStatus, LocalDate bookPublishedDate, Integer authorId,
			String authorName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookImage = bookImage;
		this.bookPrice = bookPrice;
		this.bookPublisher = bookPublisher;
		this.bookCategory = bookCategory;
		this.bookStatus = bookStatus;
		this.bookPublishedDate = bookPublishedDate;
		this.authorId = authorId;
		this.authorName = authorName;
	}
	public Books() {
		super();
	}
	
	
}
