/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.controller;

import com.hamizan.app.ligabolehland.LigaBolehlandController;
import com.hamizan.app.ligabolehland.request.CompetitionRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<BasicResponse> create (@RequestBody CompetitionRequest request){
        return service.createCompetition(request);
    }
    
    @GetMapping("/competition/{competition-id}")
    public ResponseEntity<BasicResponse> view (){
        return null;
    }
    
}
