package com.Club.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Club.app.document.Entrenador;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String>{

}
