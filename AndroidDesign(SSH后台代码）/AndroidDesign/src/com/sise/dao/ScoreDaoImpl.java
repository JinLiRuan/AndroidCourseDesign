package com.sise.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sise.pojo.Score;
import com.sise.util.HibernateUtil;

@Repository
public class ScoreDaoImpl implements ScoreDao {

	@Override
	public void addScore(Score score) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.persist(score);
			ts.commit();
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}

	}

	@Override
	public Score selectScore(String sno) {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		Score score = null;
		try {
			String hql ="from Score where sno=?1";
			Query query = session.createQuery(hql);
			query.setParameter(1, sno);
			score   =  (Score) query.list().get(0);
			ts.commit();
			if (score == null) {
				System.out.println("查询报错，未查询到该学生成绩信息！！！！");
			}
		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();
		}
		return score;
	}

}
