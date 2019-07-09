package com.quinnox.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.quinnox.example.model.Student;

public interface StudentRepository extends CrudRepository<Student, String>{

}
