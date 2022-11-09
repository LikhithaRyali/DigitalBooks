package com.book.exception;

public class BookNotFoundException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private Integer fieldValue;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Integer getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Integer fieldValue) {
		this.fieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BookNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s is not found in %s: %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public BookNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	
	
}