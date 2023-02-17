package com.rspatil45.first_project.shared.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping(value = "/redirect", method= RequestMethod.GET)
	public ModelAndView processForm()
	{
		String redirectUrl = "http://localhost:4200/products/3";
		return new ModelAndView("redirect:"+redirectUrl);
	}
}
