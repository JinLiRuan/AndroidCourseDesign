package com.sise.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sise.dao.UserDao;
import com.sise.pojo.User;

public class TestAddData {

	public static void main(String[] args) {
		String config = "beans-config.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(config);
		User user= (User) ac.getBean("user");
		UserDao userDao = (UserDao) ac.getBean("userDaoImpl");
		
		user.setUsername("1940131266");
		user.setPassword("test");
		userDao.addUser(user);
		

	}
	
	
	

}
