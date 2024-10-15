package com.Club.app.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jugadores")
public class Jugador {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private int numero;
    private String posicion;
    
    public Jugador() {
	}
	public Jugador(String id, String nombre, String apellido, int numero, String posicion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero = numero;
		this.posicion = posicion;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	@Override
	public String toString() {
		return "Jugadores [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", numero=" + numero
				+ ", posicion=" + posicion + "]";
	}
	
}

