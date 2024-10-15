package com.Club.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Club.app.document.Jugador;

public interface JugadorRepository extends MongoRepository<Jugador, String>{

}
