package com.quinnox.example.controller;

import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinnox.example.service.GraphQLService;

@RequestMapping("/rest/student")
@RestController
public class ManagedStudentController {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping(value="/getAllStudent")
    public ResponseEntity<Object> getAllStudents(@RequestBody String query) {
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
    
}