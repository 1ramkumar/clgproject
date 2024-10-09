package com.klef.jfsd.springbootclg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klef.jfsd.springbootclg.service.UserService;
import com.klef.jfsd.springbootclg.web.dto.UserRegistrationDto;

//we goona  make it as a controller layer , which is recognized by spring and it can handles all the hhtp request.
//this is backend feature of registartion page.
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
// when we click "/registartion" - we need a method which will return the registartion.html page. 
	// method-handler- this goona work as http-getMethod.
	@GetMapping
	public String ShowRegistrationForm() {
	
		return "registration";
	}
	
	// here we goona we use modelattribute annotation to annotate UserRegistrationDto method and this method return new object of UserRegistrartionDto  
	// user form - available to this form.
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();	
	}
	
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;  }
	
	// handle-line method , which handle http-post request
	// when user submit the form , that will be the post-request.
	// this post-request is handled by controller , for that we creating registerUserAccount method.
	// basically this @MOdelAttribute("user")  the form data is in user -object. 
	
	// http-post request
		@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		// 
		return "redirect:/registration?Success";
		// here we return the direct to registration page with success message.
		
	}
}
