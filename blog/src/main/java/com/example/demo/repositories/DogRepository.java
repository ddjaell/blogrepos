package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Dog;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long>{
	Dog findByName(String name);

}
