package com.example.demo.repository;

import com.example.demo.repository.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<Persona, Long> {

}
