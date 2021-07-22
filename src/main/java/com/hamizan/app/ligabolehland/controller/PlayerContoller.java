/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.controller;

import com.hamizan.app.ligabolehland.LigaBolehlandController;
import com.hamizan.app.ligabolehland.request.PlayerRequest;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import com.hamizan.app.ligabolehland.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hamizan
 */
@RestController
public class PlayerContoller extends LigaBolehlandController {
    
    @Autowired
    PlayerService service;
    
    @PostMapping("/player/create")
    public ResponseEntity<BasicResponse> create (@RequestBody PlayerRequest request){
        return service.createPlayer(request);
    }
    
    @GetMapping("/player/view")
    public ResponseEntity<BasicResponse> view (@RequestParam("player-id") String playerId){
        return service.viewPlayer(playerId);
    }
    
    @PutMapping("/player/update")
    public ResponseEntity<BasicResponse> update (@RequestBody PlayerRequest request){
        return service.updatePlayer(request);
    }
    
    @GetMapping("/player/find")
    public ResponseEntity<BasicResponse> find (
            @RequestParam(value = "name", required = false) String playerName,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "team-id", required = false) String teamId,
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "transfer-status", required = false) String transferStatus,
            @RequestParam(value = "contract", required = false) String contract,
            @RequestParam(value = "wage", required = false) String wage){
        return service.findPlayer(playerName, position, year, teamId,
                nationality, transferStatus, contract, wage);
    }
}
