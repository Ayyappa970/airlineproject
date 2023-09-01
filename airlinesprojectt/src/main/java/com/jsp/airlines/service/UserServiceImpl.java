package com.jsp.airlines.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.airlines.dto.UserDTO;
import com.jsp.airlines.entity.User;
import com.jsp.airlines.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int registerUser(UserDTO dto) {
		User use = userRepository.save(User.builder().firstName(dto.getFirstName()).gender(dto.getGender()).lastName(dto.getLastName()).moileNum(dto.getMoileNum())
				.password(dto.getPassword()).userName(dto.getUserName()).build());
		return use.getUserId();
	}

	@Override
	public UserDTO loginUser(String userName, String password) {
		User user = userRepository.findByuserNameAndpassword(userName, password);
		if (user!=null) {
			UserDTO dto = UserDTO.builder().firstName(user.getFirstName()).lastName(user.getLastName()).gender(user.getGender())
			.moileNum(user.getMoileNum()).userName(user.getUserName()).password(user.getPassword()).build();
			return dto;
		} else {
			return null;
		}
		
	}

	@Override
	public List<UserDTO> getAllDetails() {
		List<User> findAll = userRepository.findAll();
		if (findAll.size()>0) {
			List<UserDTO> list = findAll.stream().map(t-> UserDTO.builder().firstName(t.getFirstName()).lastName(t.getLastName()).gender(t.getGender())
					.moileNum(t.getMoileNum()).userName(t.getUserName()).password(t.getPassword()).build()).collect(Collectors.toList());
			return list;
		} else {
			return null;
		}
		
	}

	@Override
	public int deleteUser(int id) {
		try {
			userRepository.deleteById(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public User updateUser(String userName,String password,UserDTO dto) {
		User user = userRepository.findByuserNameAndpassword(userName, password);
		if (user!=null) {
			User user2 = user;
			user2.setFirstName(dto.getFirstName());
			user2.setLastName(dto.getLastName());
			user2.setGender(dto.getGender());
			user2.setMoileNum(dto.getMoileNum());
			user2.setUserName(dto.getUserName());
			user2.setPassword(dto.getPassword());
			return userRepository.save(user2);
		} else {
			return null;
		}
		
	}	
}
