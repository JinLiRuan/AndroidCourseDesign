package com.sise.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sise.pojo.Course;
import com.sise.util.HibernateUtil;

@Repository
public class CourseDaoImpl implements CourseDao {

	public void addCourse(Course course) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.persist(course);
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}


	}

	
	public Course selectCourse(String major) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		Course course= null;
		try {
			course = session.get(Course.class, major);
			ts.commit();
			if (course == null) {
				System.out.println("��Ǹ�������ˣ��α���Ϣû�����������");
			}
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}

		return course;
	}

}
