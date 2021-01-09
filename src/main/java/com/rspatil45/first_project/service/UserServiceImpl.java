package com.rspatil45.first_project.service;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rspatil45.first_project.entity.UserEntity;
import com.rspatil45.first_project.entity.UserRepository;
import com.rspatil45.first_project.shared.Utils;
import com.rspatil45.first_project.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils util;
	
	
	
	@Override
	public UserDto createUser(UserDto user) 
	{
		
		//if(userRepository.findByEmail(user.getEmail()) != null) throw new MyException("Record already exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		
		String userId = util.generateUserId(15);
		
		userEntity.setUserId(user.getEmail()+userId);
		
		UserEntity storedUserDetails = userRepository.save(userEntity);	
		
		UserDto returnValue =new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

	



}