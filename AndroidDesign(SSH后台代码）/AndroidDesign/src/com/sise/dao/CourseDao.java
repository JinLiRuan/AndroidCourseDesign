package com.sise.dao;

import org.springframework.stereotype.Repository;

import com.sise.pojo.Course;
@Repository
public interface CourseDao {

	void addCourse(Course course);
	Course selectCourse(String major);
}
