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
import com.Club.app.document.Jugador;
import com.Club.app.repository.JugadorRepository;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

	@Autowired
	JugadorRepository repositoryJug;
	
	@RequestMapping
	public String jugadoresListTemplate(Model model) {
		model.addAttribute("jugadores", repositoryJug.findAll());
		return "list-jugadores";
	}
	
	@GetMapping("/new")
	public String jugadoresNewTemplate(Model model) {
		model.addAttribute("jugador", new Jugador());
		return "j-form";
	}
	
	@GetMapping("/edit/{id}")
	public String jugadoresEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("jugador", repositoryJug.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado")));
		return "j-form";
	}
	
	@PostMapping("/save")
	public String jugadoresSaveProcess(@ModelAttribute("entrenadores") Jugador jugador) {
		if(jugador.getId().isEmpty()) {
			jugador.setId(null);
		}
		repositoryJug.save(jugador);
		return "redirect:/jugadores";
	}
	
	@GetMapping("/delete/{id}")
	public String jugadorDeleteProcess(@PathVariable("id") String id) {
		repositoryJug.deleteById(id);
		return "redirect:/jugadores";
	}
}
