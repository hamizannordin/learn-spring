/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.controller;

import com.hamizan.app.ligabolehland.LigaBolehlandController;
import com.hamizan.app.ligabolehland.request.TeamRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hamizan
 */
@RestController
public class TeamController extends LigaBolehlandController {
    
    @Autowired
    TeamService service;
    
    @PostMapping("/team/create")
    public ResponseEntity<BasicResponse> create (@RequestBody TeamRequest request){
        return service.createTeam(request);
    }
    
    @GetMapping("/team/view")
    public ResponseEntity<BasicResponse> view (@RequestParam("team-id") String teamId){
        return service.viewTeam(teamId);
    }
}
