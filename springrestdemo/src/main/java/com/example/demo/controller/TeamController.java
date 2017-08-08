package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Player;
import com.example.demo.domain.Team;

@RestController
public class TeamController {
	
	private Team team;
	
	@PostConstruct
	public void init(){
		Set<Player> players = new HashSet<>();
		
		players.add(new Player("Dravid","Rajastan"));
		players.add(new Player("Sachin","Mumbai"));
		team = new Team("India", "IPL", players);
	}
	

	@RequestMapping("/hi")
	public Team greeting(){
		return team;
	}

}
