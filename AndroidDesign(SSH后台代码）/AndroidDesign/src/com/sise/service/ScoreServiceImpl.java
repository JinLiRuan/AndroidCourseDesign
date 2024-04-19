package com.sise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sise.dao.ScoreDao;
import com.sise.pojo.Score;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public void insertScore(Score score) {
		scoreDao.addScore(score);
		System.out.println("�ɼ�����ɹ�������");
	}

	@Override
	public Score getScore(String sno) {
		Score score = scoreDao.selectScore(sno);
		if (score != null) {
			System.out.println("�Ѳ�ѯ����ѧ���ɼ�������");
		}
		return score;

	}

}
