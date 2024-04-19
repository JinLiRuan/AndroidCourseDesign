package com.sise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.dao.StudentDao;
import com.sise.dao.UserDao;
import com.sise.pojo.Student;
import com.sise.pojo.User;


@Service("loginService")
public class LoginServiceImpl implements LoginAndRegisterService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao;
	
	public User judgeLogin(String name,String pass) {
		// TODO Auto-generated method stub
		
		User user = userDao.selectUserByNameAndPass(name, pass);
		if (user != null) {
			return user;
		}else {
			return null;
		}
	}


	@Override
	public Student judgeRegister(String sno) {
		
		Student student = studentDao.selectStudent(sno);
		if (student != null) {
			User user = new User();
			user.setUsername(student.getSno());
			user.setPassword("test");
			userDao.addUser(user);
		}else {
			System.out.println("没有查询到该学生！！");
		}
		
		return student;
	}
	
	
}
