package com.quinnox.example.datafetcher;

import com.quinnox.example.model.Student;
import com.quinnox.example.repository.StudentRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDataFetcher implements DataFetcher<Student> {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Student get(DataFetchingEnvironment dataFetchingEnvironment) {

		String roll = dataFetchingEnvironment.getArgument("rollNumber");

		return studentRepository.findOne(roll);
	}
}
