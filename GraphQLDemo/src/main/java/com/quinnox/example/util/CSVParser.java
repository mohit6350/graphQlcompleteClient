package com.quinnox.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.quinnox.example.model.Student;

public class CSVParser {

	public static List<Student> parseCSV(File file) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		List<Student> studentList = new ArrayList<Student>();
		while(line != null){
			String[] token = line.split(",");
			Student s = mapStudent(new Student(), token);
			studentList.add(s);
			 line = br.readLine();
		}
		return studentList;
	}
	
	public static Student mapStudent(Student s , String[] token){
		s.setRollNumber(token[0]);
		s.setName(token[1]);
		s.setEmail(token[2]);
		s.setAddress(token[3]);
		return s;
		
	}
	 
}
