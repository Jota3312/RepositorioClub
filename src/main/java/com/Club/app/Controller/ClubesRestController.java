package com.Club.app.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Club.app.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Club.app.Exception.*;
import com.Club.app.document.*;

@RestController
@RequestMapping(value = "/api/clubes")
public class ClubesRestController {
	
	@Autowired
	private ClubRepository repositoryClub;
	@Autowired
	private AsociacionRepository repositoryAso;
	@Autowired
	private CompeticionRepository repositoryCom;
	@Autowired
	private EntrenadorRepository repositoryEnt;
	@Autowired
	private JugadorRepository repositoryJug;
	
	@GetMapping("/")
	public List<Club> getAllClubes(){
		return repositoryClub.findAll();
	}
	
	@GetMapping("/{id}")
	public Club getClubById(@PathVariable String id) {
		return repositoryClub.findById(id).orElseThrow(() -> new NotFoundException("Club no encontrado"));
	}
	
	@PostMapping("/")
	public Club saveClub(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Club club = mapper.convertValue(body, Club.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		club.setNombre(nombre);
		
		Object entrenadorObject = body.get("entrenador");
		
		if(entrenadorObject instanceof String) {
			String entrenadorId = (String) entrenadorObject;
			Entrenador entrenador = repositoryEnt.findById(entrenadorId).orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
			club.setEntrenador(entrenador);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'entrenador' debe ser una cadena");
		}
		
		Object jugadoresObject = body.get("jugadores");
		
		if(jugadoresObject instanceof List<?>) {
			List<?> jugadorlds = (List<?>) jugadoresObject;
			List<String> jugadorldsString = new ArrayList();
			
			for(Object id : jugadorlds) {
				if(id instanceof String) {
					jugadorldsString.add((String) id);
				}
				else {
					throw new IllegalArgumentException("Los Ids de jugadores no son cadenas validas");
				}
			}
			List<Jugador> jugadores = repositoryJug.findAllById(jugadorldsString);
			club.setJugadores(jugadores);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'jugadores' debe ser una lista");
		}
		Object asociacionObject = body.get("asociacion");
		
		if(asociacionObject instanceof String) {
			String asociacionId = (String) asociacionObject;
			Asociacion asociacion = repositoryAso.findById(asociacionId).orElseThrow(() -> new NotFoundException("Asociacion no encontrada"));
			club.setAsociacion(asociacion);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'asociacion' debe ser una cadena");
		}
		Object competicionesObject = body.get("competiciones");
		
		if(competicionesObject instanceof List<?>) {
			List<?> competicionlds = (List<?>) competicionesObject;
			List<String> competicionldsString = new ArrayList<>();
			
			for(Object id: competicionlds) {
				if(id instanceof String) {
					competicionldsString.add((String) id);
				}
				else {
					throw new IllegalArgumentException("Los Ids de competiciones no son cadenas validas");
				}
			}
			
			List<Competicion> competiciones = repositoryCom.findAllById(competicionldsString);
			club.setCompeticiones(competiciones);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'competiciones' debe ser una lista");
		}
		
		return repositoryClub.save(club);
	}
	
	@PutMapping("/{id}")
	public Club updateClub(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Club club = mapper.convertValue(body, Club.class);
		club.setId(id);
		return repositoryClub.save(club);
	}
	
	@DeleteMapping("/{id}")
	public Club deleteClub(@PathVariable String id) {
		Club club = repositoryClub.findById(id).orElseThrow(() -> new NotFoundException("Club no encontado"));
		repositoryClub.deleteById(id);
		return club;
	}
}
