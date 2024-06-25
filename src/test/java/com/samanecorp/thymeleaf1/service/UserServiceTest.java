package com.samanecorp.thymeleaf1.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Locale;
import java.util.Optional;

import com.samanecorp.thymeleaf1.dao.IUserDao;
import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.entity.UserEntity;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import com.samanecorp.thymeleaf1.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService accountService;
	@Mock
	private IUserDao accountDao;
	@Mock
	private UserMapper userMapper;
	@Mock
	private MessageSource messageSource;


	@BeforeEach
	void init() {
		accountDao = mock(IUserDao.class);
		userMapper = mock(UserMapper.class);
		messageSource = mock(MessageSource.class);
		accountService.setUserDao(accountDao);
		accountService.setMessageSource(messageSource);
		accountService.setUserMapper(userMapper);
	}
	
	@Test
	void loginSuccess() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Ngor");
		userEntity.setLastName("SECK");
		userEntity.setEmail("seck@samanecorp.com");
		userEntity.setPassword("123456");

		UserDto userDto = new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("Ngor");
		userDto.setLastName("SECK");
		userDto.setEmail("seck@samanecorp.com");
		userDto.setPassword("123456");
		
		when(accountDao.findByEmailAndPassword(anyString(), anyString()))
						.thenReturn(Optional.of(userEntity));

		when(userMapper.toUserDto(any())).thenReturn(userDto);

		Optional<UserDto> user = accountService.login("seck@samanecorp.com", "passer");
		Assertions.assertTrue(user.isPresent());
	}
	
	@Test
	void loginFailed() {
		when(accountDao.findByEmailAndPassword(anyString(), anyString()))
				.thenReturn(Optional.empty());

		when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("user with email seck@samanecorp.com and password passer not found");

		EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.login("seck@samanecorp.com", "passer"));

		Assertions.assertEquals("user with email seck@samanecorp.com and password passer not found", exception.getMessage());
	}
}
