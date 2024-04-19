package com.sise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.dao.StudentDao;
import com.sise.dao.UserDao;
import com.sise.pojo.Student;
import com.sise.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao;
	
	
	public List<User> allUser() {

		List<User> users = userDao.selectUser();
		if (users != null) {
			System.out.println("�û���Ϣ�Ѳ鵽��");
		}else {
		}
		
		return users;
	}


	public Student selectUser(String sno) {
		
		Student student = studentDao.selectStudent(sno);
		if (student != null) {
			System.out.println("�����û���Ϣ�Ѳ鵽��");
		}else {
			System.out.println("�����û���Ϣû�鵽����");
		}
		
		return student;
	}


	@Override
	public void updateUserService(User user) {
		
		userDao.updateUser(user);
		System.out.println("�û���Ϣ���³ɹ���");
		
	}

	
	
	
	
}
