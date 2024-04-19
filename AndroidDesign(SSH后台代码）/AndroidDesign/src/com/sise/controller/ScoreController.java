package com.sise.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.sise.pojo.Score;
import com.sise.service.ScoreService;

@Controller
@RequestMapping("scoreController")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;
	
	@RequestMapping("/insertScore")
	public void score(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("接收到成绩页面发送过来的请求！！");
		String sno = request.getParameter("no");
		String name = request.getParameter("sname");
		String major = request.getParameter("major");
		String score1 = request.getParameter("sc1");
		String score2 = request.getParameter("sc2");
		String score3 = request.getParameter("sc3");
		Score score = new Score();
		score.setSno(sno);
		score.setName(name);
		score.setMajor(major);
		score.setScore1(score1);
		score.setScore2(score2);
		score.setScore3(score3);
		scoreService.insertScore(score);
		PrintWriter out = null;
		out= response.getWriter();
		out.print("成绩添加成功！");
	
	}
	
	@RequestMapping("getScore")
	public void getScore(String sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		Score score = scoreService.getScore(sno);
		PrintWriter out = null;
		if (score != null) {
			out= response.getWriter();
			out.print(JSON.toJSON(score));
		}else {
			out= response.getWriter();
			out.print("no");
		}
	}
	
	
	
	
	
}
