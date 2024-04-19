package com.sise.service;

import com.sise.pojo.Student;
import com.sise.pojo.User;

public interface LoginAndRegisterService {

	User judgeLogin(String name,String pass);
	Student judgeRegister(String sno);
	
}
