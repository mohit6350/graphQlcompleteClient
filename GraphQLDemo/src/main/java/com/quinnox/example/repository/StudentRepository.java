package com.quinnox.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quinnox.example.model.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
