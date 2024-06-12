package com.samanecorp.thymeleaf1;

import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class Thymeleaf1Application implements CommandLineRunner {

	private IUserService userService;
	public static void main(String[] args) {
		SpringApplication.run(Thymeleaf1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.save(new UserDto(1L, "Ngor","SECK","seck@samanecorporation.com","passer"));
	}
}
