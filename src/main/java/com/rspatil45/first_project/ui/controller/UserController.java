package com.rspatil45.first_project.ui.controller;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rspatil45.first_project.entity.UserEntity;
import com.rspatil45.first_project.entity.UserRepository;
import com.rspatil45.first_project.service.UserService;

import com.rspatil45.first_project.shared.dto.UserDto;
import com.rspatil45.first_project.ui.model.request.UserDetailRequestModel;
import com.rspatil45.first_project.ui.model.response.UserLoginResponseModel;
import com.rspatil45.first_project.ui.model.response.UserResponseModel;
import com.rspatil45.first_project.util.AESAlgorithm;
import com.rspatil45.first_project.util.JwtUtils;
import com.rspatil45.first_project.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	private UserRepository urd;
	
	JwtUtils jwt = new JwtUtils();
	// @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping()
	public String getUser(@RequestBody UserDetailRequestModel udetails) {
		String token = udetails.getToken();
		jwt.validateToken(token);
		
		return "get User was called";
	}

	@PostMapping(path = "/login", 
			consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	public UserLoginResponseModel loginUser(@RequestBody UserDetailRequestModel udetail) throws GeneralSecurityException, IOException {
		String email = udetail.getEmail();
		UserEntity user = urd.findByEmail(email);
		UserLoginResponseModel returnValue = new UserLoginResponseModel();

		if (user == null)
			throw new RuntimeException("User not exists!");
		String enCryptPassword = AESAlgorithm.en(udetail.getPassword());
		if (user.getPassword().equals(enCryptPassword)) {
			//generating jwt token
			String token = jwt.createToken(udetail.getFirstname());
			BeanUtils.copyProperties(user, returnValue);
			returnValue.setToken(token);
			return returnValue;
		} else {
			throw new RuntimeException("Invalid credentials!");
		}

	}

	@PostMapping("/signup")
	public UserResponseModel createUser(@RequestBody UserDetailRequestModel userDetails)
			throws GeneralSecurityException, IOException {

		// check for already existed user
		UserEntity checkUser = urd.findByEmail(userDetails.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exists!");

		// encrypting password before storing into database
		// AESAlgorithm o = new AESAlgorithm();
		String password = AESAlgorithm.en(userDetails.getPassword());
		userDetails.setPassword(password);

		// saving user
		UserResponseModel returnValue = new UserResponseModel();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);

		returnValue.setMessage("User Added Successful");
		return returnValue;
	}

	@PutMapping
	public String updateUser() {
		return "update user was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
	
	

}
