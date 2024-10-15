package com.Club.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Club.app.document.Club;

public interface ClubRepository extends MongoRepository<Club, String>{

}
