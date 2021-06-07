package com.sts.mmc.ipldashboard.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String teamName;
	private Long totalMatchWin;
	private Long totalMatchPalyed;
	private Long totalMatchLoosed;
	private Long totalMatchDraw;
	@Transient
	private List<Match> latestMatches;
	private String lastMatchWin;
	private String lastMatchLoose;
	
	public Team(String teamName, Long totalMatchPalyed) {
		super();
		this.teamName = teamName;
		this.totalMatchPalyed = totalMatchPalyed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getTotalMatchWin() {
		return totalMatchWin;
	}

	public void setTotalMatchWin(Long totalMatchWin) {
		this.totalMatchWin = totalMatchWin;
	}

	public Long getTotalMatchPalyed() {
		return totalMatchPalyed;
	}

	public void setTotalMatchPalyed(Long totalMatchPalyed) {
		this.totalMatchPalyed = totalMatchPalyed;
	}

	public Long getTotalMatchLoosed() {
		return totalMatchLoosed;
	}

	public void setTotalMatchLoosed(Long totalMatchLoosed) {
		this.totalMatchLoosed = totalMatchLoosed;
	}

	public Long getTotalMatchDraw() {
		return totalMatchDraw;
	}

	public void setTotalMatchDraw(Long totalMatchDraw) {
		this.totalMatchDraw = totalMatchDraw;
	}

	public List<Match> getLatestMatches() {
		return latestMatches;
	}

	public void setLatestMatches(List<Match> latestMatches) {
		this.latestMatches = latestMatches;
	}

	public String getLastMatchWin() {
		return lastMatchWin;
	}

	public void setLastMatchWin(String lastMatchWin) {
		this.lastMatchWin = lastMatchWin;
	}

	public String getLastMatchLoose() {
		return lastMatchLoose;
	}

	public void setLastMatchLoose(String lastMatchLoose) {
		this.lastMatchLoose = lastMatchLoose;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Team(Long id, String teamName, Long totalMatchWin, Long totalMatchPalyed, Long totalMatchLoosed,
			Long totalMatchDraw, List<Match> latestMatches, String lastMatchWin, String lastMatchLoose) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.totalMatchWin = totalMatchWin;
		this.totalMatchPalyed = totalMatchPalyed;
		this.totalMatchLoosed = totalMatchLoosed;
		this.totalMatchDraw = totalMatchDraw;
		this.latestMatches = latestMatches;
		this.lastMatchWin = lastMatchWin;
		this.lastMatchLoose = lastMatchLoose;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", totalMatchWin=" + totalMatchWin + ", totalMatchPalyed="
				+ totalMatchPalyed + ", totalMatchLoosed=" + totalMatchLoosed + ", totalMatchDraw=" + totalMatchDraw
				+ ", latestMatches=" + latestMatches + ", lastMatchWin=" + lastMatchWin + ", lastMatchLoose="
				+ lastMatchLoose + "]";
	}
	
	
	
	

}
