package com.samanecorp.thymeleaf1.service;

import com.samanecorp.thymeleaf1.dao.IUserDao;
import com.samanecorp.thymeleaf1.dao.UserDao;
import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import com.samanecorp.thymeleaf1.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService implements IUserService {

	private IUserDao userDao ;
	private UserMapper userMapper;
	private MessageSource messageSource;
	
	@Override
	public List<UserDto> getAll() {
	
		return userMapper.listUserEntityToListUserDto(userDao.findAll());
	}

	@Override
	public UserDto save(UserDto userDto) {
		
		return userMapper.toUserDto(userDao.save(userMapper.toUserEntity(userDto)));
	}

	@Override
	public Optional<UserDto> login(String email, String password) {
		return userDao.findByEmailAndPassword(email, password)
				.map(userEntity -> {
					return Optional.of(userMapper.toUserDto(userEntity));
				})
				.orElseThrow(() -> new EntityNotFoundException(
						messageSource.getMessage("user.notfound",
								new Object[]{email, password},
								Locale.getDefault())
				));
	}
}
