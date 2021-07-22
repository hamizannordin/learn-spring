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
    TeamRepository teamRepository;
    
    @Autowired
    ResponseHandler responseHandler;

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
        
        team.setTeamId("testing");
            
        teamRepository.save(team);
        
        log.info("Success create player");
        return responseHandler.ok("Success", team);
    }
    
}
