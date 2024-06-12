package com.samanecorp.thymeleaf1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
