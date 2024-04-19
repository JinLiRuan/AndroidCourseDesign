package com.sise.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sise.pojo.Student;

@Repository
public interface StudentDao {
	void addStudent(Student student);
	Student selectStudent(String sno);
	List<Student> selectStudent();
	

}
