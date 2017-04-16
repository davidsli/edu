package com.hp.webedu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hp.webedu.entity.Collection;

public interface CollectionDao extends JpaRepository<Collection, String>{

}
