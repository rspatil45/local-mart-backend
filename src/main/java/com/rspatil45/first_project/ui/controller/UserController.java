package com.rspatil45.first_project.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.rspatil45.first_project.shared.MyException;
import com.rspatil45.first_project.shared.dto.UserDto;
import com.rspatil45.first_project.ui.model.request.UserDetailRequestModel;
import com.rspatil45.first_project.ui.model.response.UserLoginResponseModel;
import com.rspatil45.first_project.ui.model.response.UserResponseModel;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	private UserRepository urd;

	// @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping
	public String getUser() {
		return "get User was called";
	}

	@PostMapping("/login")
	public UserLoginResponseModel loginUser(@RequestBody UserDetailRequestModel udetail)  {
		String email = udetail.getEmail();
		UserEntity user = urd.findByEmail(email);
		UserLoginResponseModel returnValue = new UserLoginResponseModel();
		
		if(user != null)
		{
			if(user.getPassword().equals(udetail.getPassword()))
			{
						
				BeanUtils.copyProperties(user, returnValue);
				return returnValue;
			}
		}
		
		return null;
		
	}

	@PostMapping("/signup")
	public UserResponseModel createUser(@RequestBody UserDetailRequestModel userDetails) throws MyException {

		UserResponseModel returnValue = new UserResponseModel();
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(userDetails, userDto);

		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);

		returnValue.setMessage("Successful");
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
