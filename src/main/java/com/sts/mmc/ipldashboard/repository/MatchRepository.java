package com.sts.mmc.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sts.mmc.ipldashboard.entity.Match;

/**
 * Match Repository 
 * @author pintug
 *
 */
@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{
	
	public List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1,String team2, Pageable pageable);
	
	default List<Match> getLatestMatchesByTeam(String teamName, int n) {
		Pageable pageable = PageRequest.of(0, n);
		return getByTeam1OrTeam2OrderByDateDesc(teamName,teamName,pageable);
	}
}
