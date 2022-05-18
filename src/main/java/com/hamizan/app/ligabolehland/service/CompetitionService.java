/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.service;

import com.hamizan.app.ligabolehland.LigaBolehlandService;
import com.hamizan.app.ligabolehland.database.Competition;
import com.hamizan.app.ligabolehland.database.Match;
import com.hamizan.app.ligabolehland.database.Team;
import com.hamizan.app.ligabolehland.request.CompetitionAddTeamRequest;
import com.hamizan.app.ligabolehland.request.CompetitionRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author hamizan
 */
@Service
public class CompetitionService extends LigaBolehlandService {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    /**
     * Create new competition
     * @param request
     * @return competition
     */
    public ResponseEntity<BasicResponse> createCompetition (CompetitionRequest request){
        
        if(request.getCompetitionName() == null || request.getCompetitionName().isEmpty()){
            log.info("Competition name is null or empty");
            return responseHandler.badRequest("Competition name is null or empty", null);
        }
        
        Date dateStart;
        Date dateEnd;
        
        try {
            dateStart = dateFormatter.date(request.getDateStart());
            dateEnd = dateFormatter.date(request.getDateEnd());
        }
        catch (ParseException e){
            log.error(e.getMessage());
            return responseHandler.badRequest("Invalid date start or end", null);
        }
        
        String competitionId = "";
        
        if(request.getType() != null && !request.getType().isEmpty()){
            if(request.getType().equalsIgnoreCase("LEAGUE")){
                competitionId = generateCompetitionId("L-");
            }
            if(request.getType().equalsIgnoreCase("CUP")){
                competitionId = generateCompetitionId("C-");
            }
        } 
        else {
            log.info("Invalid competition type");
            return responseHandler.badRequest("Invalid competition type", null);
        }
        
        if(request.getTotalTeam() == null || request.getTotalTeam().isEmpty() ||
                !request.getTotalTeam().matches("[0-9]*2")){
            log.info("Invalid total team");
            return responseHandler.badRequest("Invalid total team", null);
        }
        
        Competition competition = new Competition();
        competition.setCompetitionId(competitionId);
        competition.setCompetitionName(request.getCompetitionName());
        competition.setType(request.getType());
        competition.setCompetitionStart(dateStart);
        competition.setCompetitionEnd(dateEnd);
        competition.setStatus("NOT STARTED");
        competition.setTotalTeam(Integer.parseInt(request.getTotalTeam()));
        
        competitionRepository.save(competition);
        
        log.info("Competition created " + request.getCompetitionName() + ", " 
                + competitionId);
        return responseHandler.ok("Success", competition);
    }
    
    /**
     * View a competition detail
     * @param competitionId
     * @return competition
     */
    public ResponseEntity<BasicResponse> viewCompetition (String competitionId){
        
        if(competitionId == null || competitionId.isEmpty()){
            log.info("Invalid competition id");
            return responseHandler.badRequest("Invalid competition id", null);
        }
        
        Competition competition = competitionRepository.findByCompetitionId(competitionId);
        
        if(competition != null){
            log.info("Competition found: " + competition.getCompetitionName());
            return responseHandler.ok("Success", competition);
        }
        else {
            log.info("Competition not found");
            return responseHandler.notFound("Competition not found", null);
        }
    }
    
    /**
     * Add team(s) to a competition
     * @param competitionId
     * @param request
     * @return success or fail
     */
    public ResponseEntity<BasicResponse> addTeamToCompetition (String competitionId,
            CompetitionAddTeamRequest request){
        
        if(competitionId.isEmpty()){
            log.info("Invalid competition id");
            return responseHandler.badRequest("Invalid competition id", null);
        }
        
        if(request.getTeamList() == null || request.getTeamList().isEmpty()){
            log.info("No team to add");
            return responseHandler.badRequest("No team to add", null);
        }
        
        Competition competition = competitionRepository.findByCompetitionId(competitionId);
        
        if(competition != null){
            
            //boolean leagueFlag = false;
            //if(competition.getType().equalsIgnoreCase("LEAGUE")){
            //    leagueFlag = true;
            //}
            
            List<String> successUpdateTeam = new ArrayList<>();
            List<String> failUpdateTeam = new ArrayList<>();
            
            List<String> teamList = request.getTeamList();
            
            for(String teamId : teamList){
                Team team = teamRepository.findTeamById(teamId);
                if(team != null){
                    team.setCompetitionId(competition);
                    log.info("Updating team info: " + team.getTeamId());
                    teamRepository.save(team);
                    successUpdateTeam.add(teamId);
                }
                else {
                    log.info("Team not found: " + teamId);
                    failUpdateTeam.add(teamId);
                }
            }
            
            //List<List> teamSummary = new ArrayList<>();
            //teamSummary.add(successUpdateTeam);
            //teamSummary.add(failUpdateTeam);
            return responseHandler.ok("Success: " + successUpdateTeam.size() +
                    ", Fail: " + failUpdateTeam.size(), failUpdateTeam);
        }
        else {
            log.info("Competition not found");
            return responseHandler.notFound("Competition not found", null);
        }
    }
    
    /**
     * Remove team(s) from a competition
     * @param competitionId
     * @param request
     * @return 
     */
    public ResponseEntity<BasicResponse> removeTeamFromCompetition (String competitionId,
            CompetitionAddTeamRequest request){
        
        if(competitionId == null || competitionId.isEmpty()){
            log.info("Invalid competition id");
            return responseHandler.badRequest("Invalid competition id", null);
        }
        
        if(request.getTeamList() == null || request.getTeamList().isEmpty()){
            log.info("No team to remove");
            return responseHandler.badRequest("No team to remove", null);
        }
        
        Competition competition = competitionRepository.findByCompetitionId(competitionId);
        
        if(competition != null){
            
            List<String> successUpdateTeam = new ArrayList<>();
            List<String> failUpdateTeam = new ArrayList<>();
            
            for(String teamId : request.getTeamList()){
                
                if(teamId != null && !teamId.isEmpty()){
                    
                    Team team = teamRepository.findTeamById(teamId);
                    
                    if(team != null){
                        team.setCompetitionId(null);
                        teamRepository.save(team);
                        log.info("Updating team info: " + team.getTeamId());
                        successUpdateTeam.add(team.getTeamId());
                    }
                    else {
                        log.info("Team not found: " + teamId);
                        failUpdateTeam.add(teamId);
                    }
                }
            }
            
            return responseHandler.ok("Success: " + successUpdateTeam.size() +
                    ", Fail: " + failUpdateTeam.size(), failUpdateTeam);  
        }
        else {
            log.info("Competition not found");
            return responseHandler.notFound("Competition not found", null);
        }
    }
    
    /**
     * Get competition fixtures
     * @param competitionId
     * @return 
     */
    public ResponseEntity<BasicResponse> getCompetitionFixture(String competitionId) {
    
        if(competitionId == null || competitionId.isEmpty()){
            log.info("Invalid competition id");
            return responseHandler.badRequest("Invalid competition id", null);
        }
        
//        Competition competition = competitionRepository.findByCompetitionId(competitionId);
//        
//        if(competition == null){
//            log.info("Competition not found");
//            return responseHandler.notFound("Competition not found", null);
//        }
        
        List<Match> listMatch = matchRepository.getMatchListByCompetitionId(competitionId);
        
        if(listMatch != null && !listMatch.isEmpty()){
            log.info("Fixture found: " + listMatch.size());
        }
        else {
            log.info("Fixture not schedule yet. Generating...");
            listMatch = generateFixture(competitionId);
            if(listMatch.isEmpty()){
                return responseHandler.notFound("Fixture not found", null);
            }
        }
        return responseHandler.ok("success", listReadableFixture(listMatch));
    }
    
    /**
     * Generate competition id
     * @param head
     * @return 
     */
    private String generateCompetitionId (String head){
        String count = Long.toString(competitionRepository.count() + 1);
        
        int idStopper = 4  - count.length();
        
        for(int i=0; i < idStopper; i++){
            if(i == idStopper - 1){
                head += count;
                break;
            }
            head += "0";
        }
        
        return head;
    }

    /**
     * Generate fixture of a competition
     * @param competitionId
     * @return 
     */
    private List<Match> generateFixture(String competitionId) {
    
        List<Match> listMatch = new ArrayList<>();
        Competition competition = competitionRepository.findByCompetitionId(competitionId);
        
        if(competition != null){
            log.info("Competition found: " + competition.getCompetitionId());
            List<Team> listTeam = teamRepository.findTeamByCompetitionId(competitionId);
            
            if(listTeam != null && !listTeam.isEmpty() && listTeam.size() > 1){
                
                log.info("Team found: " + listTeam.size());
                int countTeam = listTeam.size();
                Map teamVsTeamMap = new HashMap<>();
                Map unavailableTeam = new HashMap<>();
                int countMatch = 2 * (countTeam - 1);
                int halfSeason = countTeam - 1;
                int matchPerRound = countTeam/2;
                Random random = new Random();
                Date date = competition.getCompetitionStart();
                
                //preserve half of team for zigzag home away pattern
                List<Integer> reserveTeamZigzag = new ArrayList<>();
                while(reserveTeamZigzag.size() < 3){
                    int r = random.nextInt(countTeam);
                    if(!reserveTeamZigzag.contains(r)){
                        log.info("adding: " + r);
                        reserveTeamZigzag.add(r);
                    }
                }
                
                //per round
                for(int i = 1; i <= countMatch; i++){
                    
                    String homeAway;
                    String awayHome;
                    Team home = null;
                    Team away = null;
                    
                    //per team head to head
                    for(int j = 1; j <= matchPerRound; j++){
                        
                        int homeTeam = random.nextInt(countTeam);
                        while(unavailableTeam.get(homeTeam) != null){
                            homeTeam = random.nextInt(countTeam);
                        }
                        unavailableTeam.put(homeTeam, new Object());
                        
                        int awayTeam = random.nextInt(countTeam);
                        while(unavailableTeam.get(awayTeam) != null){
                            awayTeam = random.nextInt(countTeam);
                        }
                        unavailableTeam.put(awayTeam, new Object());
                        
                        homeAway = homeTeam + "-" + awayTeam;
                        awayHome = awayTeam + "-" + homeTeam;
                        home = listTeam.get(homeTeam);
                        away = listTeam.get(awayTeam);
                        if(teamVsTeamMap.get(homeAway) == null){
                            //prevent more than two consecutive home/away match
                            /*if(i % 2 > 0 && teamVsTeamMap.size() > 1 
                                    && reserveTeamZigzag.contains(homeTeam)){
                                boolean restart = false;
                                int breakerHome = 0;
                                //int breakerAway = 0;
                                
                                Iterator iterator = teamVsTeamMap.entrySet().iterator();
                                while(iterator.hasNext()){
                                    Map.Entry entry = (Map.Entry) iterator.next();
                                    String teamMatch = (String) entry.getKey();
                                    //log.info("test: " + teamMatch + ", I: " + i + ", J: " + j);
                                    if(teamMatch.startsWith(homeTeam + "")){
                                        breakerHome ++;
                                    }
                                    //if(teamMatch.endsWith(homeTeam + "")){
                                    //    breakerAway ++;
                                    //}
                                    if(breakerHome >= (i/2)+1){ //|| breakerAway >= (i/2)+1){
                                        j--;
                                        unavailableTeam.remove(homeTeam);
                                        unavailableTeam.remove(awayTeam);
                                        restart = true;
                                        break;
                                    }
                                }
                                if(restart){
                                    continue;
                                }
                            }*/
                            //prevent meeting same team again before half season
                            if(i <= halfSeason && teamVsTeamMap.get(awayHome) == null){
                                teamVsTeamMap.put(homeAway, new Object());
                                Match match = new Match();
                                match.setMatchId(competition.getCompetitionId() 
                                        + "-" + (i < 10 ? "0" + i : i) 
                                        + (j < 10 ? "0" + j : j));
                                match.setCompetitionId(competition);
                                match.setHomeTeamId(home);
                                match.setAwayTeamId(away);
                                match.setMatchStatus("SCHEDULED");
                                match.setMatchDatetime(date);
                                match.setRound(Integer.toString(i));

                                matchRepository.save(match);

                                listMatch.add(match);
                            }
                            else if(i > halfSeason){
                                teamVsTeamMap.put(homeAway, new Object());
                                Match match = new Match();
                                match.setMatchId(competition.getCompetitionId() 
                                        + "-" + (i < 10 ? "0" + i : i) 
                                        + (j < 10 ? "0" + j : j));
                                match.setCompetitionId(competition);
                                match.setHomeTeamId(home);
                                match.setAwayTeamId(away);
                                match.setMatchStatus("SCHEDULED");
                                match.setMatchDatetime(date);
                                match.setRound(Integer.toString(i));

                                matchRepository.save(match);

                                listMatch.add(match);
                            }
                            else {
                                j--;
                                unavailableTeam.remove(homeTeam);
                                unavailableTeam.remove(awayTeam);
                            }
                        }
                        else {
                            j--;
                            unavailableTeam.remove(homeTeam);
                            unavailableTeam.remove(awayTeam);
                        }
                    }
                    
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, 7);
                    date = calendar.getTime();
                    unavailableTeam = new HashMap<>();
                }
            }
        }
        
        return listMatch;
    }
    
    /**
     * Make a readable fixture list
     * @param listMatch
     * @return 
     */
    private List<Map> listReadableFixture (List<Match> listMatch) {
        
        List<Map> listRound = new ArrayList<>();
        List<String> listHeadToHead = new ArrayList<>();
        Map mapRound = new HashMap<>();
        
        String round = "";
        Date date = null;
        
        for(Match match : listMatch) {
            if(round == null || round.isEmpty())
                round = match.getRound();
            if(!round.equalsIgnoreCase(match.getRound())){
                mapRound.put("date", dateFormatter.dateString(date));
                mapRound.put("week", round);
                mapRound.put("fixture", listHeadToHead);
                listRound.add(mapRound);
                
                mapRound = new HashMap<>();
                listHeadToHead = new ArrayList<>();
            }
            
            listHeadToHead.add(match.getHomeTeamId().getTeamName() + " - " 
                    + match.getAwayTeamId().getTeamName());
            
            round = match.getRound();
            date = match.getMatchDatetime();
        }
        mapRound.put("date", date);
        mapRound.put("week", round);
        mapRound.put("fixture", listHeadToHead);
        listRound.add(mapRound);
        
        return listRound;
    }
}
