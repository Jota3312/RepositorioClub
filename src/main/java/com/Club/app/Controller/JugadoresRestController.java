package com.Club.app.Controller;

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

import com.Club.app.Exception.NotFoundException;
import com.Club.app.document.Jugador;
import com.Club.app.repository.JugadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/jugadores")
public class JugadoresRestController {

	@Autowired
	JugadorRepository repositoryJug;
	
	@GetMapping("/")
	public List<Jugador> getAllJugadores(){
		return repositoryJug.findAll();
	}
	
	@GetMapping("/{id}")
	public Jugador getJugadorById(@PathVariable String id) {
		return repositoryJug.findById(id).orElseThrow(() -> new NotFoundException("Jugador No encontrado"));
	}
	
	@PostMapping("/")
	public Jugador saveJugador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Jugador jugador = mapper.convertValue(body, Jugador.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		jugador.setNombre(nombre);
		
		String apellido = (String) body.get("apellido");
		if (apellido == null || apellido.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'apellido' es obligatorio y no puede estar vacío.");
	    }
		
		jugador.setApellido(apellido);
		
		int numero = (int) body.get("numero");
		if (numero < 0) {
	        throw new IllegalArgumentException("El campo 'numero' debe tener un valor valido.");
	    }
		
		jugador.setNumero(numero);
		
		String posicion = (String) body.get("posicion");
		if (posicion == null || posicion.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'posicion' es obligatorio y no puede estar vacío.");
	    }
		
		jugador.setPosicion(posicion);
		
		return repositoryJug.save(jugador);
	}
	
	@PutMapping("/{id}")
	public Jugador updateJugador(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Jugador jugador = mapper.convertValue(body, Jugador.class);
		jugador.setId(id);
		return repositoryJug.save(jugador);
	}
	
	@DeleteMapping("/{id}")
	public Jugador deleteJugador(@PathVariable String id) {
		Jugador jugador = repositoryJug.findById(id).orElseThrow(() -> new NotFoundException("Jugador no encontrado"));
		repositoryJug.deleteById(id);
		return jugador;
	}
}