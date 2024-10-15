package com.Club.app.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

import com.Club.app.Exception.*;
import com.Club.app.document.Competicion;
import com.Club.app.repository.CompeticionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/competiciones")
public class CompeticionesRestController {

	@Autowired
	CompeticionRepository repositoryCom;
	
	@GetMapping("/")
	public List<Competicion> getAllCompeticiones(){
		return repositoryCom.findAll();
	}
	
	@GetMapping("/{id}")
	public Competicion getCompeticionById(@PathVariable String id) {
		return repositoryCom.findById(id).orElseThrow(() -> new NotFoundException("Competición No encontrada"));
	}
	
	@PostMapping("/")
	public Competicion saveCompeticion(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Competicion competicion = mapper.convertValue(body, Competicion.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		competicion.setNombre(nombre);
		
		int montoPremio = (int) body.get("montoPremio");
		if (montoPremio < 0) {
	        throw new IllegalArgumentException("El campo 'montoPremio' debe tener un valor valido.");
	    }
		
		competicion.setMontoPremio(montoPremio);
		
	    if (!body.containsKey("fechaInicio")) {
	        throw new IllegalArgumentException("El campo 'fechaInicio' es obligatorio.");
	    }
	    Object fechaInicioObject = body.get("fechaInicio");
	    if (fechaInicioObject == null) {
	        throw new IllegalArgumentException("El campo 'fechaInicio' no puede estar vacío.");
	    }
	    
	    if (!(fechaInicioObject instanceof String)) {
	        throw new IllegalArgumentException("El campo 'fechaInicio' debe ser una cadena en formato ISO de fecha (YYYY-MM-DD).");
	    }
	    try {
	        LocalDate fechaInicio = LocalDate.parse((String) fechaInicioObject);
	        competicion.setFechaInicio(fechaInicio);
	    } catch (DateTimeParseException e) {
	        throw new IllegalArgumentException("El campo 'fechaInicio' debe estar en formato ISO de fecha (YYYY-MM-DD).");
	    }
		
	    if (!body.containsKey("fechaFin")) {
	        throw new IllegalArgumentException("El campo 'fechaFin' es obligatorio.");
	    }
	    Object fechaFinObject = body.get("fechaFin");
	    if (fechaFinObject == null) {
	        throw new IllegalArgumentException("El campo 'fechaFin' no puede estar vacío.");
	    }
	    if (!(fechaFinObject instanceof String)) {
	        throw new IllegalArgumentException("El campo 'fechaFin' debe ser una cadena en formato ISO de fecha (YYYY-MM-DD).");
	    }
	    try {
	        LocalDate fechaFin = LocalDate.parse((String) fechaFinObject);
	        competicion.setFechaFin(fechaFin);
	    } catch (DateTimeParseException e) {
	        throw new IllegalArgumentException("El campo 'fechaFin' debe estar en formato ISO de fecha (YYYY-MM-DD).");
	    }
		
		return repositoryCom.save(competicion);
	}
	
	@PutMapping("/{id}")
	public Competicion updateCompeticion(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Competicion competicion = mapper.convertValue(body, Competicion.class);
		competicion.setId(id);
		return repositoryCom.save(competicion);
	}
	
	@DeleteMapping("/{id}")
	public Competicion deletecompeticion(@PathVariable String id) {
		Competicion competicion = repositoryCom.findById(id).orElseThrow(() -> new NotFoundException("Competición no encontrada"));
		repositoryCom.deleteById(id);
		return competicion;
	}
}
