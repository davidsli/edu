package com.hp.webedu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hp.webedu.entity.Admin;
import com.hp.webedu.entity.Score;

public interface ScoreDao extends JpaRepository<Score, String>{

}
