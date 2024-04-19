package com.sise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sise.pojo.Student;
import com.sise.pojo.User;
import com.sise.service.UserService;

@Controller
@RequestMapping("userController")
public class UserController {

	@Autowired
	private UserService userService;
	
	@SuppressWarnings("null")
	@RequestMapping("/allUser")
	public void UserList(String id,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("���յ���ѯ�����û���Ϣҳ�淢�͹��������󣡣�"+id);

		List<User> users = userService.allUser();

		PrintWriter out;
	if (users != null) {
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(users));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		out = response.getWriter();
		out.print("��Ǹ����ѯ�����û���Ϣʧ�ܣ�");
	}
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping("/user")
	public void userInfo(String sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("���յ���ѯ�����û���Ϣҳ�淢�͹��������󣡣�"+sno);

		Student student = userService.selectUser(sno);

		PrintWriter out;
	if (student != null) {
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(student));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		out = response.getWriter();
		out.print("��Ǹ����ѯ�����û���Ϣʧ�ܣ�");
	}
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping("/updateUser")
	public void updateUser(String account,String password,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("���յ������û���Ϣҳ�淢�͹��������󣡣�"+account);

		User user = new User();
		user.setUsername(account);
		user.setPassword(password);
		userService.updateUserService(user);
		PrintWriter out;
		out = response.getWriter();
		out.print("ok");
	}
	
}
