package com.samanecorp.thymeleaf1.dto;

import com.samanecorp.thymeleaf1.entity.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private RoleEnum role;
}
