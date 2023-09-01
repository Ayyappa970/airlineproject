package com.jsp.airlines.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.airlines.dto.UserDTO;
import com.jsp.airlines.entity.User;
import com.jsp.airlines.service.UserService;
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/add")
	public ResponseEntity<String> addingUser(@RequestBody UserDTO dto) {
		int ad = userService.registerUser(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body("User id : "+ad);
	}
	@GetMapping("/get/{name}/{pass}")
	public ResponseEntity getUserDetails(@PathVariable("name") String userName,@PathVariable("pass") String password) {
		UserDTO dto = userService.loginUser(userName, password);
		if (dto!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No details found");
		}	
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity getAllUsers() {
		 List<UserDTO> list = userService.getAllDetails();
		 if (list!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(list);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users found");
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteRecord(@PathVariable("id") int id) {
		int user = userService.deleteUser(id);
		if (user>0) {
			return ResponseEntity.status(HttpStatus.FOUND).body("Record Deleted");
		}else {
			return ResponseEntity.status(HttpStatus.FOUND).body("No record Record");
		}
	}
	@PutMapping("/update/{name}/{pass}")
	public ResponseEntity updateUser(@PathVariable("name") String userName,@PathVariable("pass") String password,@RequestBody UserDTO dto) {
		User user = userService.updateUser(userName, password, dto);
		System.out.println(user);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data Found by user id");
		}
	}
}
