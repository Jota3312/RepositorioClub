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
import com.Club.app.document.Entrenador;
import com.Club.app.repository.EntrenadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/entrenadores")
public class EntrenadorRestController {

	@Autowired
	EntrenadorRepository repositoryEnt;
	
	@GetMapping("/")
	public List<Entrenador> getAllEntrenadores(){
		return repositoryEnt.findAll();
	}
	
	@GetMapping("/{id}")
	public Entrenador getEntrenadorById(@PathVariable String id) {
		return repositoryEnt.findById(id).orElseThrow(() -> new NotFoundException("Entrenador No encontrado"));
	}
	
	@PostMapping("/")
	public Entrenador saveEntrenador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Entrenador entrenador = mapper.convertValue(body, Entrenador.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		entrenador.setNombre(nombre);
		
		String apellido = (String) body.get("apellido");
		if (apellido == null || apellido.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'apellido' es obligatorio y no puede estar vacío.");
	    }
		
		entrenador.setApellido(apellido);
		
		int edad = (int) body.get("edad");
		if (edad < 0) {
	        throw new IllegalArgumentException("El campo 'edad' debe tener un valor valido.");
	    }
		
		entrenador.setEdad(edad);
		
		String nacionalidad = (String) body.get("nacionalidad");
		if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nacionalidad' es obligatorio y no puede estar vacío.");
	    }
		
		entrenador.setNacionalidad(nacionalidad);
		
		return repositoryEnt.save(entrenador);
	}
	
	@PutMapping("/{id}")
	public Entrenador updateEntrenador(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Entrenador entrenador = mapper.convertValue(body, Entrenador.class);
		entrenador.setId(id);
		return repositoryEnt.save(entrenador);
	}
	
	@DeleteMapping("/{id}")
	public Entrenador deleteEntrenador(@PathVariable String id) {
		Entrenador entrenador = repositoryEnt.findById(id).orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
		repositoryEnt.deleteById(id);
		return entrenador;
	}
}
