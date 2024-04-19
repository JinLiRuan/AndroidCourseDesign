package com.sise.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sise.pojo.User;
import com.sise.util.HibernateUtil;

@Repository
public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.persist(user);
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}

	}


	public List<User> selectUser() {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		List<User> users = null;
		try {
			Query<User> query = session.createQuery("from User");
			users = query.getResultList();
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
		return users;
	}


	@SuppressWarnings("deprecation")
	public User selectUserByNameAndPass(String name,String pass) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		User user = null;
		try {
			String hql = "from User where username = ?1 and password = ?2";
			Query<User> query = session.createQuery(hql);
			query.setParameter(1, name);
			query.setParameter(2, pass);
			user =  query.uniqueResult();
			if (user == null) {
				System.out.println("用户查询报错，请查看！！！！");
			}
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}


	@SuppressWarnings("deprecation")
	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			String hql = "update User u set u.password=?1 where username=?2";
			Query<User> query = session.createQuery(hql);
			query.setParameter(1, user.getPassword());
			query.setParameter(2, user.getUsername());
			query.executeUpdate();
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
	}


	

}
