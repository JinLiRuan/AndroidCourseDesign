package com.sise.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sise.pojo.User;

@Repository
public interface UserDao {

	void addUser(User user);
	List<User> selectUser();
	User selectUserByNameAndPass(String name,String pass);
	void updateUser(User user);
}
