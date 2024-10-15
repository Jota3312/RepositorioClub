package com.Club.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Club.app.document.Asociacion;

public interface AsociacionRepository extends MongoRepository<Asociacion, String>{

}
