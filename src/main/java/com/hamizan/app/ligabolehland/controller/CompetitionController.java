/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.controller;

import com.hamizan.app.ligabolehland.LigaBolehlandController;
import com.hamizan.app.ligabolehland.request.CompetitionAddTeamRequest;
import com.hamizan.app.ligabolehland.request.CompetitionRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hamizan
 */
@RestController
public class CompetitionController extends LigaBolehlandController {
    
    @Autowired
    private CompetitionService service;
    
    @PostMapping("/competition/create")
    public ResponseEntity<BasicResponse> create (
            @RequestBody CompetitionRequest request){
        return service.createCompetition(request);
    }
    
    @GetMapping("/competition/{competition-id}")
    public ResponseEntity<BasicResponse> view (
            @PathVariable("competition-id") String competitionId){
        return service.viewCompetition(competitionId);
    }
    
    @PostMapping("/competition/{competition-id}/addTeam")
    public ResponseEntity<BasicResponse> addTeam (
            @PathVariable("competition-id") String competitionId,
            @RequestBody CompetitionAddTeamRequest request){
        return service.addTeamToCompetition(competitionId, request);
    }
    
    @PostMapping("/competition/{competition-id}/removeTeam")
    public ResponseEntity<BasicResponse> removeTeam (
            @PathVariable("competition-id") String competitionId,
            @RequestBody CompetitionAddTeamRequest request){
        return service.removeTeamFromCompetition(competitionId, request);
    }
    
    @GetMapping("/competition/{competition-id}/fixture")
    public ResponseEntity<BasicResponse> getFixture (
            @PathVariable("competition-id") String competitionId) {
        return service.getCompetitionFixture(competitionId);
    }
    
}
