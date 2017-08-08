package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.TeamDAO;
import com.example.demo.domain.Player;
import com.example.demo.domain.Team;

@SpringBootApplication
public class SpringbootdemoApplication {
	
	@Autowired
	TeamDAO teamDAO;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@PostConstruct
	public void init(){
		Set<Player> players = new HashSet<>();
		
		players.add(new Player("Dravid","Rajastan"));
		players.add(new Player("Sachin","Mumbai"));
		players.add(new Player("Kohli","Bangalore"));
		Team team = new Team("India", "IPL", players);
		
		teamDAO.save(team);
	}
	
	
}
