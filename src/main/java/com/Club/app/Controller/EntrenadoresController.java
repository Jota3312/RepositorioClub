package com.Club.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Club.app.Exception.NotFoundException;
import com.Club.app.document.Entrenador;
import com.Club.app.repository.EntrenadorRepository;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadoresController {

	@Autowired
	EntrenadorRepository repositoryEnt;
	
	@RequestMapping
	public String entrenadoresListTemplate(Model model) {
		model.addAttribute("entrenadores", repositoryEnt.findAll());
		return "list-entrenadores";
	}
	
	@GetMapping("/new")
	public String entrenadoresNewTemplate(Model model) {
		model.addAttribute("entrenador", new Entrenador());
		return "e-form";
	}
	
	@GetMapping("/edit/{id}")
	public String entrenadoresEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("entrenador", repositoryEnt.findById(id).orElseThrow(() -> new NotFoundException("Entrenador no encontrado")));
		return "e-form";
	}
	
	@PostMapping("/save")
	public String entrenadoresSaveProcess(@ModelAttribute("entrenadores") Entrenador entrenador) {
		if(entrenador.getId().isEmpty()) {
			entrenador.setId(null);
		}
		repositoryEnt.save(entrenador);
		return "redirect:/entrenadores";
	}
	
	@GetMapping("/delete/{id}")
	public String entrenadorDeleteProcess(@PathVariable("id") String id) {
		repositoryEnt.deleteById(id);
		return "redirect:/entrenadores";
	}
}