package com.jsp.airlines.dto;

import com.jsp.airlines.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDTO {
	private String firstName;
	private String lastName;
	private String moileNum;
	private String gender;
	private String userName;
	private String password;
}
