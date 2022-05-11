/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.service;

import com.hamizan.app.ligabolehland.database.Competition;
import com.hamizan.app.ligabolehland.database.Team;
import com.hamizan.app.ligabolehland.repository.CompetitionRepository;
import com.hamizan.app.ligabolehland.repository.TeamRepository;
import com.hamizan.app.ligabolehland.request.CompetitionAddTeamRequest;
import com.hamizan.app.ligabolehland.request.CompetitionRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.util.DateFormatter;
import com.hamizan.app.ligabolehland.util.ResponseHandler;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author hamizan
 */
@Service
public class CompetitionService {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    @Autowired
    private CompetitionRepository competitionRepository;
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private DateFormatter dateFormatter;
    
    @Autowired
    private ResponseHandler responseHandler;
    
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
}
