package com.Club.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping
	public String inicio(Model model) {
		String titulo = "Pagina de inicio";
		model.addAttribute("titulo", titulo);
		return "index";
	}
}
