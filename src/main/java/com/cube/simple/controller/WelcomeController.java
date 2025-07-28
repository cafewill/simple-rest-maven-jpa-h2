package com.cube.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.simple.entity.Product;
import com.demo.simple.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WelcomeController {
	
	@GetMapping ("/")
	public RedirectView home () {
		return new RedirectView ("/welcome");
	}

	@GetMapping ("/welcome")
	public String welcome (Model model) {
		String response = "welcome";
		
		try {
			model.addAttribute ("title", "Welcome");
			model.addAttribute ("copyright", "&copy; CafeWill All rights reserved.");
			log.info ("Welcome : {}", "products");
		} catch (Exception e) {
			log.info ("Welcome error : {}", e.getMessage ());
		}
		
		return response;
	}
}
