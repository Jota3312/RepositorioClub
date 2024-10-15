package com.Club.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Club.app.Exception.*;
import com.Club.app.document.Asociacion;
import com.Club.app.document.Club;
import com.Club.app.document.Competicion;
import com.Club.app.document.Entrenador;
import com.Club.app.document.Jugador;
import com.Club.app.repository.*;

@Controller
@RequestMapping("/clubes")
public class ClubesController {
	
	@Autowired
	private ClubRepository repositoryClub;
	@Autowired
	private EntrenadorRepository repositoryEnt;
	@Autowired
	private AsociacionRepository repositoryAso;
	@Autowired
	private CompeticionRepository repositoryCom;
	@Autowired
	private JugadorRepository repositoryJug;
	
	@RequestMapping
	public String clubesListTemplate(Model model) {
		model.addAttribute("clubes" , repositoryClub.findAll());
		return "list-clubes";
	}
	
	@GetMapping("/new")
	public String clubesNewTemplate(Model model) {
		model.addAttribute("club", new Club());
		List<Entrenador> entrenadoresList = repositoryEnt.findAll();
		model.addAttribute("entrenadoresList", entrenadoresList);
		List<Jugador> jugadoresList = repositoryJug.findAll();
		model.addAttribute("jugadoresList", jugadoresList);
		List<Asociacion> asociacionesList = repositoryAso.findAll();
		model.addAttribute("asociacionesList", asociacionesList);
		List<Competicion> competicionesList = repositoryCom.findAll();
		model.addAttribute("competicionesList", competicionesList);
		return "clubes-form";
	}
	
	@GetMapping("/edit/{id}")
	public String clubesEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("club", repositoryClub.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado")));
		List<Entrenador> entrenadoresList = repositoryEnt.findAll();
		model.addAttribute("entrenadoresList", entrenadoresList);
		List<Jugador> jugadoresList = repositoryJug.findAll();
		model.addAttribute("jugadoresList", jugadoresList);
		List<Asociacion> asociacionesList = repositoryAso.findAll();
		model.addAttribute("asociacionesList", asociacionesList);
		List<Competicion> competicionesList = repositoryCom.findAll();
		model.addAttribute("competicionesList", competicionesList);
		return "clubes-form";
	}
	
	@PostMapping("/save")
	public String clubesSaveProcess(@ModelAttribute("clubes") Club club) {
		if(club.getId().isEmpty()) {
			club.setId(null);
		}
		repositoryClub.save(club);
		return "redirect:/clubes";
	}
	
	@GetMapping("/delete/{id}")
	public String clubesDeleteProcess(@PathVariable("id") String id) {
		repositoryClub.deleteById(id);
		return "redirect:/clubes";
	}	
}
