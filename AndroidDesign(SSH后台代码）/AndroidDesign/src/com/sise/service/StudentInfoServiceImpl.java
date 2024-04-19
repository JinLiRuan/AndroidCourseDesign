package com.sise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sise.dao.StudentDao;
import com.sise.pojo.Student;

@Service("studentInfoServie")
public class StudentInfoServiceImpl implements StudentInfoService {

	@Autowired
	private StudentDao studentDao;
	
	
	@Override
	public List<Student> allStudent() {

		List<Student> students = studentDao.selectStudent();
		if (students != null) {
			System.out.println("学生信息已查到！");
		}else {
			System.out.println("学生信息没查到！！");
		}
		
		return students;
	}

}
