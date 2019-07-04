package com.quinnox.example.service;

import com.quinnox.example.datafetcher.AllStudentDataFetcher;
import com.quinnox.example.datafetcher.StudentDataFetcher;
import com.quinnox.example.model.Student;
import com.quinnox.example.repository.StudentRepository;
import com.quinnox.example.util.CSVParser;
import com.quinnox.example.util.ExcelReader;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class GraphQLService {

	@Autowired
	StudentRepository studentRepository;

	@Value("classpath:student.graphql")
	Resource resource;

	private GraphQL graphQL;
	@Autowired
	private AllStudentDataFetcher allStudentDataFetcher;
	@Autowired
	private StudentDataFetcher studentDataFetcher;

	@PostConstruct
	private void loadSchema() throws Exception {

		// Load Books into the Book Repository
		loadDataIntoHSQL();

		// get the schema
		File schemaFile = resource.getFile();
		// parse schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	/*private void loadDataIntoHSQL() {

		Stream.of(new Student().setRollNumber("1").setName("mohit").setEmail("mohit@gmail.com").setAddress("New York"),
				new Student().setRollNumber("2").setName("poonam").setEmail("poonam@gmail.com").setAddress("New York"),
				new Student().setRollNumber("3").setName("ankit").setEmail("ankit@gmail.com").setAddress("New York"),
				new Student().setRollNumber("4").setName("adi").setEmail("adi@gmail.com").setAddress("New York"))
				.forEach(student -> {
					studentRepository.save(student);
				});
	}*/
	
	/*private void loadDataIntoHSQL() throws Exception{
		CSVParser parser = new CSVParser();
		List<Student> list = parser.parseCSV(new File("D:\\quinnox projects\\cyc\\git\\GraphQL\\GraphQLDemo\\src\\main\\resources\\student.csv2"));
		list.stream().forEach(student ->{
			studentRepository.save(student);
		});
		
	}*/
	
	private void loadDataIntoHSQL() throws Exception{
		ExcelReader reader = new ExcelReader();
		File f = new File("D:\\quinnox projects\\cyc\\git\\GraphQLDemo\\src\\main\\resources\\student.xlsx");
		List<Student> list = reader.readExcelUsingXSSF(f);
		list.stream().forEach(student ->{
			studentRepository.save(student);
		});
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring
				.newRuntimeWiring().type("Query", typeWiring -> typeWiring
						.dataFetcher("allStudent", allStudentDataFetcher).dataFetcher("student", studentDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
