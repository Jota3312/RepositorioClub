package com.Club.app.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "clubes")
public class Club {
    @Id
    private String id;
	
    private String nombre;

    @DocumentReference
    private Entrenador entrenador;
    @DocumentReference
    private List<Jugador> jugadores;
    @DocumentReference
    private Asociacion asociacion;
    @DocumentReference
    private List<Competicion> competiciones;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Entrenador getEntrenador() {
		return entrenador;
	}
	
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public Asociacion getAsociacion() {
		return asociacion;
	}
	
	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}
	
	public List<Competicion> getCompeticiones() {
		return competiciones;
	}
	
	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}
}
