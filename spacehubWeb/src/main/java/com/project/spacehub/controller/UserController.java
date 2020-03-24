/**
 * 
 */
package com.project.spacehub.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.spacehub.config.JwtRequestFilter;
import com.project.spacehub.config.JwtTokenUtil;
import com.project.spacehub.entity.Booking;
import com.project.spacehub.entity.JwtRequest;
import com.project.spacehub.entity.JwtResponse;
import com.project.spacehub.entity.Product;
import com.project.spacehub.entity.SpaceHubUser;
import com.project.spacehub.service.AdminService;
import com.project.spacehub.service.UserDetailsServiceImpl;
import com.project.spacehub.service.UserService;

/**
 * @author gbemisola
 *
 */
@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsServiceImpl jwtUserService;
	
	
	
	@GetMapping("/home")
	public String home(Model theModel) {
		List<Product> theProducts = adminService.getProducts();
		
		theModel.addAttribute("products", theProducts);
		
		return "home";
	}
	
	
	@CrossOrigin
	@PostMapping("/register") 
	public SpaceHubUser registerUser(@RequestBody SpaceHubUser user) {
				
		userService.saveSpaceHubUser(user);

		return user;
	}
	
	
	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("spacehubuser") SpaceHubUser user,
			BindingResult result, Model model) {
		
		if (result.hasErrors()) {
            return "register";
        }
		
		System.out.println("user: " +user.toString());

	
		userService.saveSpaceHubUser(user);
		
		System.out.println("user: " +user.getFirstName() + " " + user.getLastName());

		
		return "confirmation";
	}
	
	
	@RequestMapping("/showLogin")
	public String showLogin() {
		
		return "login";

	}
	
	
	@CrossOrigin
	@PostMapping("/book")
	public Booking bookProduct(@RequestBody Booking book) {
		
		userService.bookProduct(book);
		
		
		return book;
	}
		
	
	
	
	
	@GetMapping("/")
	public String showHome() {
		
		return "home";	
		
		
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
		
		JwtRequestFilter.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = jwtUserService.loadUserByUsername(authenticationRequest.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	
	
	
	
}
