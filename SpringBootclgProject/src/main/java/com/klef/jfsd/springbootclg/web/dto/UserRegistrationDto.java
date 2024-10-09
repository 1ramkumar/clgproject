package com.klef.jfsd.springbootclg.web.dto;

public class UserRegistrationDto {
	public UserRegistrationDto(){
		
	}
	
 public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
 
//field name of firstName is equal to the field of firstName in the registration in html page-registration.html page.
// th:field="*{firstName}" == field of firstname 
private String firstName;
 private String lastName;
 private String email;
 private String password;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
 
	
	
}
