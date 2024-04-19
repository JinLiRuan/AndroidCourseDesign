package com.sise.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sise.pojo.Score;
import com.sise.pojo.Student;
import com.sise.pojo.User;
import com.sise.service.LoginAndRegisterService;
import com.sise.service.ScoreService;


@Controller
@RequestMapping("/controller")
public class MyController {

	@Autowired
	private LoginAndRegisterService loginService;

	
	@SuppressWarnings("null")
	@PostMapping("/requestLogin")
	public void loginModelAndView(String name,String pass,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		System.out.println("接收到登陆页面发送过来的请求！！"+name+pass);

		User user = loginService.judgeLogin(name,pass);
		PrintWriter out;
	if (user != null) {
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(user));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		out = response.getWriter();
		out.print("抱歉，用户名或密码错误！");
	}
	}
	
	@SuppressWarnings("null")
	@RequestMapping("/requestRegister")
	public void register(String name,String sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("接收到注册页面发送过来的请求！！");
		Student student = loginService.judgeRegister(sno);
		PrintWriter out = null;
	if (student != null) {
			out = response.getWriter();
			out.print(JSON.toJSONString(student));
	}else {
		out = response.getWriter();
		out.print("no");
	}
	}
	
	


	
	
	
}
