package com.Club.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Club.app.Exception.*;
import com.Club.app.document.Competicion;
import com.Club.app.repository.CompeticionRepository;

@Controller
@RequestMapping("/competiciones")
public class CompeticionesController {

	@Autowired
	CompeticionRepository repositoryCom;
	
	@RequestMapping
	public String competicionesesListTemplate(Model model) {
		model.addAttribute("competiciones", repositoryCom.findAll());
		return "list-competiciones";
	}
	
	@GetMapping("/new")
	public String competicionesesNewTemplate(Model model) {
		model.addAttribute("competicion", new Competicion());
		return "com-form";
	}
	
	@GetMapping("/edit/{id}")
	public String competicionesesEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("competicion", repositoryCom.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada")));
		return "com-form";
	}
	
	@PostMapping("/save")
	public String competicionesSaveProcess(@ModelAttribute("competiciones") Competicion competicion) {
		if(competicion.getId().isEmpty()) {
			competicion.setId(null);
		}
		repositoryCom.save(competicion);
		return "redirect:/competiciones";
	}
	
	@GetMapping("/delete/{id}")
	public String competicionDeleteProcess(@PathVariable("id") String id) {
		repositoryCom.deleteById(id);
		return "redirect:/competiciones";
	}
}
