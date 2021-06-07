package com.sts.mmc.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sts.mmc.ipldashboard.entity.Team;

@Repository
public interface TeamReposeitory extends CrudRepository<Team, Long>{
	
	public Team findByTeamName(String teamName);

}
