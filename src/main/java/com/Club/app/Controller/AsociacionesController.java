package com.Club.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Club.app.document.Asociacion;
import com.Club.app.repository.AsociacionRepository;
import com.Club.app.Exception.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionesController {

	@Autowired
	AsociacionRepository repositoryAso;
	
	@RequestMapping
	public String asociacionesListTemplate(Model model) {
		model.addAttribute("asociaciones", repositoryAso.findAll());
		return "list-asociaciones";
	}
	
	@GetMapping("/new")
	public String asociacionesNewTemplate(Model model) {
		model.addAttribute("asociacion", new Asociacion());
		return "a-form";
	}
	
	@GetMapping("/edit/{id}")
	public String asociacionesEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("asociacion", repositoryAso.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrado")));
		return "a-form";
	}
	
	@PostMapping("/save")
	public String asociacionesSaveProcess(@ModelAttribute("asociaciones") Asociacion asociacion) {
		if(asociacion.getId().isEmpty()) {
			asociacion.setId(null);
		}
		repositoryAso.save(asociacion);
		return "redirect:/asociaciones";
	}
	
	@GetMapping("/delete/{id}")
	public String asociacionDeleteProcess(@PathVariable("id") String id) {
		repositoryAso.deleteById(id);
		return "redirect:/asociaciones";
	}
}
