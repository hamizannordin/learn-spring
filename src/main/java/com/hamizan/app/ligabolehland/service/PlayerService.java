/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.service;

import com.hamizan.app.ligabolehland.database.Player;
import com.hamizan.app.ligabolehland.request.PlayerRequest;
import com.hamizan.app.ligabolehland.repository.PlayerRepository;
import com.hamizan.app.ligabolehland.response.BasicResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author hamizan
 */
@Service
public class PlayerService {
    
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
    @Autowired
    PlayerRepository playerRepo;
    
    /**
     * Create new player
     * @param request
     * @return BasicResponse - success, fail
     */
    public ResponseEntity<BasicResponse> createPlayer (PlayerRequest request){
        
        if(request.getPlayerName() == null || request.getPlayerName().isEmpty()){
            log.info("Player name is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new BasicResponse("Player name is null"));
        }
        
        String playerCount = Long.toString(playerRepo.count() + 1);
        String playerId = "P-";
        
        int idStopper = 10  - playerCount.length();
        
        for(int i=0; i < idStopper; i++){
            if(i == idStopper - 1){
                playerId += playerCount;
                break;
            }
            playerId += "0";
        }
        
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerName(request.getPlayerName());
        player.setPosition(request.getPosition());
        try {
            log.info(request.getDateOfBirth());
            player.setDateOfBirth(sdf.parse(request.getDateOfBirth()));
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new BasicResponse("Invalid birth date"));
        }
        player.setTeamId(request.getTeamId());
        player.setNationality(request.getNationality());
        player.setContract(request.getContract());
        player.setWage(request.getWage());
        
        log.info("Saving player.... " + playerId);
        Player newPlayer = playerRepo.save(player);
        
        if(newPlayer != null){
            log.info("Success create player");
            return ResponseEntity.status(HttpStatus.OK).body(
                    new BasicResponse("Success create player"));
        } 
        else {
            log.info("Fail to create player");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new BasicResponse("Fail to create player"));
        }
    }
}
