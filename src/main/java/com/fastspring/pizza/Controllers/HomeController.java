package com.fastspring.pizza.Controllers;

import com.fastspring.pizza.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}