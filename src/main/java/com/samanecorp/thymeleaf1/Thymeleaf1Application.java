package com.samanecorp.thymeleaf1;

import com.samanecorp.thymeleaf1.dao.UserDao;
import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.entity.RoleEnum;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import com.samanecorp.thymeleaf1.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class Thymeleaf1Application implements CommandLineRunner {

	private final IUserService userService;
	private final UserDao userDao;
	private final Logger logger = LoggerFactory.getLogger(Thymeleaf1Application.class);

    public Thymeleaf1Application(IUserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    public static void main(String[] args) {
		SpringApplication.run(Thymeleaf1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        String PASSWORD = "passer";
        userService.save(new UserDto(1L, "Ngor","SECK","seck@samanecorporation.com", new BCryptPasswordEncoder().encode(PASSWORD), RoleEnum.IT));
		userService.save(new UserDto(2L, "Abdou","SENE","sene@samanecorporation.com", new BCryptPasswordEncoder().encode(PASSWORD), RoleEnum.ADMIN));
		userService.save(new UserDto(3L, "Oumar","DIOUF","diouf@samanecorporation.com", new BCryptPasswordEncoder().encode(PASSWORD), RoleEnum.FINANCE));
		userService.save(new UserDto(4L, "Moussa","DIATTA","diatta@samanecorporation.com", new BCryptPasswordEncoder().encode(PASSWORD), RoleEnum.ADMIN));

		logger.info("=========================ORDER BY================================");
		userDao.allUserOrderByLastName()
				.map(userEntities -> {
					logger.info("User: {} The first : {}", userEntities.size(), userEntities.get(0).getLastName());
					return userEntities.get(0);
				}).orElseThrow(() -> new EntityNotFoundException("no user found"));

		logger.info("==========================COUNT===============================");
		userDao.countAllUsers()
				.map(number -> {
					logger.info("User count: {}", number);
					return number;
				}).orElseThrow();

		logger.info("==========================Projections===============================");
		userDao.allLastNameAndFirstName()
				.map(data -> {
					data.forEach(user -> logger.info("FistName : {}, LastName : {}", user.get(0), user.get(1)));
					return data;
				}).orElseThrow();


		logger.info("==========================IN Projections===============================");
		userDao.allUserInALastName()
				.map(userEntities -> {
					logger.info("{}",userEntities.size());
					return userEntities.get(0);
				}).orElseThrow();
	}
}
