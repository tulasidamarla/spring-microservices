package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TeamDAO;
import com.example.demo.domain.Team;

@RestController
public class TeamController {
	
	@Autowired
	private TeamDAO teamDAO;
	
	

	@RequestMapping("/teams/{name}")
	public Team greeting(@PathVariable String name){
		return teamDAO.findByName(name);
	}

}
