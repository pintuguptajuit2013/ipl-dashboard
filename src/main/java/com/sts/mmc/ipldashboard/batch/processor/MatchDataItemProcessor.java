package com.sts.mmc.ipldashboard.batch.processor;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.sts.mmc.ipldashboard.batch.input.MatchDataInput;
import com.sts.mmc.ipldashboard.entity.Match;

public class MatchDataItemProcessor implements ItemProcessor<MatchDataInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataItemProcessor.class);

  @Override
  public Match process(final MatchDataInput matchDataInput) throws Exception {

    var transformedMatchData = new Match();
    transformedMatchData.setId(Long.valueOf(matchDataInput.getId()));
    transformedMatchData.setCity(matchDataInput.getCity());
    transformedMatchData.setDate(LocalDate.parse(matchDataInput.getDate()));
    transformedMatchData.setMatchWinner(matchDataInput.getWinner());
    transformedMatchData.setPlayerOfMatch(matchDataInput.getPlayer_of_match());
    transformedMatchData.setResult(matchDataInput.getResult());
    transformedMatchData.setTeam1(matchDataInput.getTeam1());
    transformedMatchData.setTeam2(matchDataInput.getTeam2());
    transformedMatchData.setResultMargin(matchDataInput.getResult_margin());
    transformedMatchData.setTossDecision(matchDataInput.getToss_decision());
    transformedMatchData.setTossWinner(matchDataInput.getToss_winning_team());
    transformedMatchData.setVenue(matchDataInput.getVenue());
    transformedMatchData.setUmpire1(matchDataInput.getUmpire1());
    transformedMatchData.setUmpire2(matchDataInput.getUmpire2());
    String firstInningTeam;
    String secondInningTeam;
    if ("bat".equals(matchDataInput.getToss_decision())) {
      firstInningTeam = matchDataInput.getToss_winning_team();
      secondInningTeam = matchDataInput.getToss_winning_team().equals(matchDataInput.getToss_winning_team())
          ? matchDataInput.getTeam2()
          : matchDataInput.getTeam1();
    } else {
      secondInningTeam = matchDataInput.getToss_winning_team();
      firstInningTeam = matchDataInput.getToss_winning_team().equals(matchDataInput.getToss_winning_team())
          ? matchDataInput.getTeam2()
          : matchDataInput.getTeam1();
    }

    transformedMatchData.setTeam1(firstInningTeam); // here Team1 is Toss Winner
    transformedMatchData.setTeam2(secondInningTeam); // here Team2 is Toss looser
    
    log.info("Converting (" + matchDataInput + ") into (" + transformedMatchData + ")");
    return transformedMatchData;
  }

}
