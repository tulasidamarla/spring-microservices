package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Team;

public interface TeamDAO extends CrudRepository<Team, Long> {

	Team findByName(String name);
	
}
