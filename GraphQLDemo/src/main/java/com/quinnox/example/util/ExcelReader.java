package com.quinnox.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.quinnox.example.model.Student;


public class ExcelReader {

	private static List<Student> list = new ArrayList<>();
	
	public static void main(String[] args) {
		//readExcelUsingXSSF();
	}
	
	public static List<Student> readExcelUsingXSSF(File f) {
		try {
			//File f = new File("D:\\quinnox projects\\cyc\\git\\GraphQLDemo\\src\\main\\resources\\student.xlsx");
			FileInputStream file = new FileInputStream(f);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				if(row.getRowNum() != 0) {
					assignStudent(row);
				}
			}
			//list.stream().forEach(s -> System.err.println(s));
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void assignStudent(Row row){
		Student s = new Student();
		s.setRollNumber(row.getCell(0).toString());
		s.setName(row.getCell(1).toString());
		s.setEmail(row.getCell(2).toString());
		s.setAddress(row.getCell(3).toString());
		list.add(s);
	}
}
