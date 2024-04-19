package com.sise.dao;

import org.springframework.stereotype.Repository;

import com.sise.pojo.Score;

@Repository
public interface ScoreDao {

	void addScore(Score score);
	Score selectScore(String name);
}
