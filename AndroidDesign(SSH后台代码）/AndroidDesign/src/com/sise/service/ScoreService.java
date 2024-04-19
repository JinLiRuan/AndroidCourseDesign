package com.sise.service;

import com.sise.pojo.Score;

public interface ScoreService {

	void insertScore(Score score);
	Score getScore(String sno);
}
