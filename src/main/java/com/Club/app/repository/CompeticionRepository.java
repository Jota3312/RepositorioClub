package com.Club.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Club.app.document.Competicion;

public interface CompeticionRepository extends MongoRepository<Competicion, String>{

}
