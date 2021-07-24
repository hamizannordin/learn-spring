/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.service;

import com.hamizan.app.ligabolehland.database.Team;
import com.hamizan.app.ligabolehland.repository.TeamRepository;
import com.hamizan.app.ligabolehland.request.TeamRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.util.ResponseHandler;
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
public class TeamService {
    
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    TeamRepository teamRepo;
    
    @Autowired
    ResponseHandler responseHandler;

    /**
     * Create new team
     * @param request
     * @return success, fail
     */
    public ResponseEntity<BasicResponse> createTeam(TeamRequest request) {
        
        if(request.getTeamName() == null || request.getTeamName().isEmpty()){
            log.info("Team name is null or empty");
            return responseHandler.badRequest("Team name is null or empty", null);
        }
        
        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setStadiumId(request.getStadiumId());
        team.setLeagueId(request.getLeagueId());
        team.setCupId(request.getCupId());
        team.setManagerId(request.getManagerId());
        team.setHomeKit(request.getHomeKit());
        team.setAwayKit(request.getAwayKit());
        
        String teamCount = Long.toString(teamRepo.count() + 1);
        String teamId = "T-";
        
        int idStopper = 5  - teamCount.length();
        
        for(int i=0; i < idStopper; i++){
            if(i == idStopper - 1){
                teamId += teamCount;
                break;
            }
            teamId += "0";
        }
        
        team.setTeamId(teamId);
        
        long count = teamRepo.count();
        log.info(Long.toString(count));
            
        teamRepo.save(team);
        
        log.info("Success create team: " + team.getTeamId());
        return responseHandler.ok("Success", team);
    }

    /**
     * View team details
     * @param teamId
     * @return team details
     */
    public ResponseEntity<BasicResponse> viewTeam(String teamId) {
        
        if(teamId == null || teamId.isEmpty()){
            log.info("Team id is null or empty");
            return responseHandler.badRequest("Team id is null or empty", null);
        }
            
        Team team = teamRepo.findTeamById(teamId);
        
        if(team != null){
            log.info("Team found: " + team.getTeamId());
            return responseHandler.ok("Success", team);
        } 
        else {
            log.info("Team not found");
            return responseHandler.notFound("Team not found", null);
        }
    }

    /**
     * Update team details
     * @param teamId
     * @param request
     * @return 
     */
    public ResponseEntity<BasicResponse> updateTeam(String teamId, TeamRequest request) {
         
        if(teamId == null || teamId.isEmpty()){
            log.info("Team id is null or empty");
            return responseHandler.badRequest("Team id is null or empty", null);
        }
        
        Team team = teamRepo.findTeamById(teamId);
        
        if(team != null){
            if(request.getTeamName() != null && !request.getTeamName().isEmpty()){
                team.setTeamName(request.getTeamName());
            }
            if(request.getLeagueId() != null){
                team.setLeagueId(request.getLeagueId());
            }
            if(request.getCupId() != null){
                team.setCupId(request.getCupId());
            }
            if(request.getStadiumId() != null){
                team.setStadiumId(request.getStadiumId());
            }
            if(request.getHomeKit() != null){
                team.setHomeKit(request.getHomeKit());
            }
            if(request.getAwayKit() != null){
                team.setAwayKit(request.getAwayKit());
            }
            if(request.getManagerId() != null){
                team.setManagerId(request.getManagerId());
            }
            
            log.info("Updating team: " + team.getTeamId());
            teamRepo.save(team);
            return responseHandler.ok("Success", team);
        }
        else {
            log.info("Team not found");
            return responseHandler.notFound("Team not found", null);
        }
    }
    
}
