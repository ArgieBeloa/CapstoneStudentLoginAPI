package com.example.demo.db;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentModel, String> {

    Optional<StudentModel> getByUsername(String username);

}
