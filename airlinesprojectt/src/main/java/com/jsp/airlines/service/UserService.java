package com.jsp.airlines.service;

import java.util.List;

import com.jsp.airlines.dto.UserDTO;
import com.jsp.airlines.entity.User;

public interface UserService {
	int registerUser(UserDTO dto);
	UserDTO loginUser(String userName,String password);
	List<UserDTO> getAllDetails();
	int deleteUser(int id);
	User updateUser(String userName,String password,UserDTO dto);
}
