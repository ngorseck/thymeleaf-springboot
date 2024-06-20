package com.samanecorp.thymeleaf1;

import com.samanecorp.thymeleaf1.dao.UserDao;
import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.entity.UserEntity;
import com.samanecorp.thymeleaf1.exception.EntityNotFoundException;
import com.samanecorp.thymeleaf1.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class Thymeleaf1Application implements CommandLineRunner {

	private IUserService userService;
	private UserDao userDao;
	public static void main(String[] args) {
		SpringApplication.run(Thymeleaf1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.save(new UserDto(1L, "Ngor","SECK","seck@samanecorporation.com", new BCryptPasswordEncoder().encode("passer")));
		userService.save(new UserDto(2L, "Abdou","SENE","sene@samanecorporation.com", new BCryptPasswordEncoder().encode("passer")));
		userService.save(new UserDto(3L, "Oumar","DIOUF","diouf@samanecorporation.com", new BCryptPasswordEncoder().encode("passer")));
		userService.save(new UserDto(4L, "Moussa","DIATTA","diatta@samanecorporation.com", new BCryptPasswordEncoder().encode("passer")));

		System.out.println("=========================ORDER BY================================");
		userDao.allUserOrderByLastName()
				.map(userEntities -> {
					System.out.println("User: " + userEntities.size() + " The first : " + userEntities.get(0).getLastName());
					return userEntities.get(0);
				}).orElseThrow(() -> new EntityNotFoundException("no user found"));

		System.out.println("==========================COUNT===============================");
		userDao.countAllUsers()
				.map(number -> {
					System.out.println("User count: " + number);
					return number;
				}).orElseThrow();

		System.out.println("==========================Projections===============================");
		userDao.allLastNameAndFirstName()
				.map(data -> {
					data.forEach(user -> System.out.println("FistName : " + user.get(0) + ", LastName : " + user.get(1)));
					return data;
				}).orElseThrow();

		System.out.println("==========================IN Projections===============================");
		userDao.allUserInALastName()
				.map(userEntities -> {
					System.out.println(userEntities.size());
					return userEntities.get(0);
				}).orElseThrow();
	}
}
