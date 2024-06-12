package com.samanecorp.thymeleaf1.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/welcome")
	public String logout() {
		return "welcome";
	}
}
