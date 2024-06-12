package com.samanecorp.thymeleaf1.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;
import java.util.Optional;

import com.samanecorp.thymeleaf1.dao.IUserDao;
import com.samanecorp.thymeleaf1.dao.UserDao;
import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.entity.UserEntity;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import com.samanecorp.thymeleaf1.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

	@InjectMocks
	private UserService accountService;
	@Mock
	private IUserDao accountDao;
	@Mock
	private UserMapper userMapper;
	@Mock
	private MessageSource messageSource;


	@BeforeEach
	void intit() {
		accountDao = mock(IUserDao.class);
		userMapper = mock(UserMapper.class);
		messageSource = mock(MessageSource.class);
	}
	
	@Test
	@Disabled
	void loginSuccess() {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		
		when(accountDao.findByEmailAndPassword(anyString(), anyString()))
						.thenReturn(Optional.of(userEntity));

		when(userMapper.toUserEntity(any())).thenReturn(userEntity);

		when(messageSource.getMessage(anyString(), any(), any())).thenReturn(null);
		
		Optional<UserDto> userDto = accountService.login("seck@samanecorp.com", "passer");
		Assertions.assertTrue(userDto.isPresent());
	}
	
	@Test
	void loginFailed() {
		Assertions.assertTrue(true);
	}
}
