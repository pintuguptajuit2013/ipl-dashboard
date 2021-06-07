package com.sts.mmc.ipldashboard.batch.listener;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sts.mmc.ipldashboard.entity.Team;

@Component
public class MatchJobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(MatchJobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;
	
	private final EntityManager entityManager;

	@Autowired
	public MatchJobCompletionNotificationListener(EntityManager entityManager,JdbcTemplate jdbcTemplate) {
		this.entityManager = entityManager;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			
			
			jdbcTemplate.query("SELECT team1, team2, date FROM MATCH",
					(rs, row) -> "Team1 " + rs.getString(1) + " Team2 " + rs.getString(2) + " Date: " + rs.getDate(3))
					.forEach(str -> {
						System.out.println(str);
					});
					
			
			Map<String , Team> teamData = new HashMap<String, Team>();
			// Get Team 1 Unique List
			entityManager.createQuery("select distinct m.team1 , count(*) from Match m group by m.team1", Object[].class)
			.getResultList()
			.stream()
			.map(e -> new Team((String)e[0], (Long)e[1])).forEach(team -> teamData.put(team.getTeamName(), team));
			// Get Team 2 Unique List
			entityManager.createQuery("select distinct m.team2 , count(*) from Match m group by m.team2", Object[].class)
			.getResultList()
			.stream()
			.forEach( record -> {
				var team = teamData.get((String)record[0]);
				if(team != null) {
					team.setTotalMatchPalyed(team.getTotalMatchPalyed()+(Long)record[1]);
				}else {
					var newTeam = new Team((String)record[0], (Long)record[1]);
					teamData.put(newTeam.getTeamName(), newTeam);
				}
			});
			
			entityManager.createQuery("select m.matchWinner , count(*) from Match m group by m.matchWinner", Object[].class)
			.getResultList()
			.stream()
			.forEach(e -> {
				var team = teamData.get((String)e[0]);
				if(team!= null) {
					if(e[1]!=null)
						team.setTotalMatchWin((Long)e[1]);
					else
						team.setTotalMatchWin(0L);
				}
			});
			
			teamData.values().forEach(team -> entityManager.persist(team));
			teamData.values().forEach(team -> System.out.println(team));
		}
	}
}
