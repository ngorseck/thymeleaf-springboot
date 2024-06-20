package com.samanecorp.thymeleaf1.contoller;

import com.samanecorp.thymeleaf1.dto.UserDto;
import com.samanecorp.thymeleaf1.mapper.UserMapper;
import com.samanecorp.thymeleaf1.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {

	private IUserService userService;

	public AdminController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/admin")
	public String admin(Model model) {

		model.addAttribute("usersList", userService.getAll());
		model.addAttribute("user", new UserDto());
		return "users/list";
	}

	@PostMapping("/admin")
	public String admin(@ModelAttribute("user") UserDto user) {

		userService.save(user);
		return "redirect:/admin";
	}
}
