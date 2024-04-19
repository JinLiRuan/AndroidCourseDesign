package com.sise.service;

import java.util.List;

import com.sise.pojo.Student;
import com.sise.pojo.User;

public interface UserService {

	List<User> allUser();
	Student selectUser(String sno);
	void updateUserService(User user);
}
