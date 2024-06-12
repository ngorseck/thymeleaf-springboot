package com.samanecorp.thymeleaf1.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
