package com.quinnox.example.datafetcher;


import com.quinnox.example.model.Student;
import com.quinnox.example.repository.StudentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllStudentDataFetcher implements DataFetcher<List<Student>>{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> get(DataFetchingEnvironment dataFetchingEnvironment) {
        List<Student> list = new ArrayList<>();
        studentRepository.findAll().forEach(student ->{
        	list.add(student);
        });
        return list;
    }
}