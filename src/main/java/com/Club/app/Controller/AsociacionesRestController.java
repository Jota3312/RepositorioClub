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

import com.Club.app.document.Asociacion;
import com.Club.app.repository.AsociacionRepository;
import com.Club.app.Exception.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value ="/api/asociaciones")
public class AsociacionesRestController {

	@Autowired
	AsociacionRepository repositoryAso;
	
	@GetMapping("/")
	public List<Asociacion> getAllAsociaciones(){
		return repositoryAso.findAll();
	}
	
	@GetMapping("/{id}")
	public Asociacion getAsociacionById(@PathVariable String id) {
		return repositoryAso.findById(id).orElseThrow(() -> new NotFoundException("Asociacion No encontrado"));
	}
	
	@PostMapping("/")
	public Asociacion saveAsociacion(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		asociacion.setNombre(nombre);
		
		String pais = (String) body.get("pais");
		if (pais == null || pais.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'pais' es obligatorio y no puede estar vacío.");
	    }
		
		asociacion.setPais(pais);
		
		String presidente = (String) body.get("presidente");
		if (presidente == null || presidente.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'presidente' es obligatorio y no puede estar vacío.");
	    }
		
		asociacion.setPresidente(presidente);
		
		return repositoryAso.save(asociacion);
	}
	
	@PutMapping("/{id}")
	public Asociacion updateAsociacion(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Asociacion asociacion = mapper.convertValue(body, Asociacion.class);
		asociacion.setId(id);
		return repositoryAso.save(asociacion);
	}
	
	@DeleteMapping("/{id}")
	public Asociacion deleteAsociacion(@PathVariable String id) {
		Asociacion asociacion = repositoryAso.findById(id).orElseThrow(() -> new NotFoundException("Asociacion no encontrada"));
		repositoryAso.deleteById(id);
		return asociacion;
	}
}
