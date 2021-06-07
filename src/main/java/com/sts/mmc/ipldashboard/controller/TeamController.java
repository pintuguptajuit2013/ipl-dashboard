package com.sts.mmc.ipldashboard.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sts.mmc.ipldashboard.entity.Team;
import com.sts.mmc.ipldashboard.repository.MatchRepository;
import com.sts.mmc.ipldashboard.repository.TeamReposeitory;
/**
 * The team controller 
 * @author pintug
 * 
 * API info : 
 * http://localhost:9091/team/Mumbai%20Indians
 * 
 *
 */
@RestController
public class TeamController {
	@Autowired
	private TeamReposeitory teamRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable("teamName") String teamName) {
		var team = this.teamRepository.findByTeamName(teamName);
		team.setLatestMatches(this.matchRepository.getLatestMatchesByTeam(teamName,4));
		return team;
	}
}
