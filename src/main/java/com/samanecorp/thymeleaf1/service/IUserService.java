package com.samanecorp.thymeleaf1.service;


import com.samanecorp.thymeleaf1.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

	List<UserDto> getAll();
	UserDto save(UserDto userDto);
	Optional<UserDto> login (String email, String password);
}
