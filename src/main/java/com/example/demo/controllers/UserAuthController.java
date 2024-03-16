package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Users;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/v0/api/auth")
public class UserAuthController {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@CrossOrigin
	@PostMapping("/signup")
	public ResponseEntity<Users> registerUser(@RequestBody Users user) {
		System.out.println("[RECEIVED]  " + user);
		if(user != null) {
			try {
				// ENCODING THE PASSWORD BEFORE SAVING.
				user.setPassword(encoder.encode(user.getPassword()));

				var res = this.repository.save(user);
				return new ResponseEntity<Users>(res, HttpStatus.CREATED);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<Users>(new Users(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<Users>(new Users(), HttpStatus.NOT_FOUND);
		}

	}

	@CrossOrigin
	@PostMapping("/signin")
	public ResponseEntity<Users> signinUser(@RequestParam String username, @RequestParam String password){
		System.out.println("username: " + username + " password: " + password);
		var res = repository.findUserByName(username);
		System.out.println(res);

		if(res != null){
			if(encoder.matches(password, res.getPassword())){
				return new ResponseEntity<Users>(res, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
