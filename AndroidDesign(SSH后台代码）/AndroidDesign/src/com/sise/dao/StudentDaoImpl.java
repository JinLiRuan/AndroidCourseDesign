package com.sise.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sise.pojo.Student;
import com.sise.util.HibernateUtil;

@Repository
public class StudentDaoImpl implements StudentDao {

	public void addStudent(Student student) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.persist(student);
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
	}

	public Student selectStudent(String sno) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		Student student = null;
		try {
			String hql = "from Student where sno = ?1";
			Query<Student> query = session.createQuery(hql);
			query.setParameter(1, sno);
			student = query.uniqueResult();
			ts.commit();
			if (student == null) {
				System.out.println("查询报错，未查询到该学生信息！！！！");
			}
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public List<Student> selectStudent() {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		List<Student> students = null;
		try {
			String hql = "from Student";
			Query<Student> query = session.createQuery(hql);
			students = query.getResultList();
			ts.commit();
			if (students == null) {
				System.out.println("查询报错，未查询到所有学生信息！！！！");
			}
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
		return students;

	}

	
	
	
}
