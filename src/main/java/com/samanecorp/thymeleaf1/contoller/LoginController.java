package com.samanecorp.thymeleaf1.contoller;

import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.service.IUserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Optional;

@Controller
public class LoginController  {

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	private IUserService userService;

	public LoginController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String login() {
		return "index";
	}

	@PostMapping("/login")
	public String logon(String email, String password) {
		log.info("L'email envoyé est {} ", email);
		try {
			Optional<UserDto> user = userService.login(email, password);
			return user.isPresent() ? "redirect:welcome" : "redirect:index";

		} catch (Exception e) {
			log.error("Erreur", e);
			return "redirect:/";
		}
	}

	@GetMapping("/error")
	public String error (HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.FORBIDDEN.value()) {
				return "error/403";
			}else if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error/500";
			}
		}
		return "error";
	}
}
