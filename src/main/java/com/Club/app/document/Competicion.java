package com.Club.app.document;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "competiciones")
public class Competicion {
    @Id
    private String id;
    private String nombre;
    private int montoPremio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    public Competicion() {
	}
	public Competicion(String id, String nombre, int montoPremio, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.montoPremio = montoPremio;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
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
	public int getMontoPremio() {
		return montoPremio;
	}
	public void setMontoPremio(int montoPremio) {
		this.montoPremio = montoPremio;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "Competiciones [id=" + id + ", nombre=" + nombre + ", montoPremio=" + montoPremio + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
	
}

