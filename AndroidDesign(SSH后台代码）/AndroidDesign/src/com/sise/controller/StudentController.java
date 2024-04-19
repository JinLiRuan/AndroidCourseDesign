package com.sise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sise.pojo.Student;
import com.sise.service.StudentInfoService;

@Controller
@RequestMapping("/studentController")
public class StudentController {


	@Autowired
	private StudentInfoService studentInfoService;

	
	@SuppressWarnings("null")
	@RequestMapping("/allStudent")
	public void StudentList(String id,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("接收到查询所有学生信息页面发送过来的请求！！"+id);

		List<Student> students = studentInfoService.allStudent();

		PrintWriter out;
	if (students != null) {
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(students));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		out = response.getWriter();
		out.print("抱歉，查询学生信息失败！");
	}
	}
	
	
	
	
}
