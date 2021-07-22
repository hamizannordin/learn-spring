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
import com.hamizan.app.ligabolehland.response.PlayerViewResponse;
import com.hamizan.app.ligabolehland.util.DateFormatter;
import com.hamizan.app.ligabolehland.util.ResponseHandler;
import java.text.ParseException;
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
    
    @Autowired
    PlayerRepository playerRepo;
    
    @Autowired
    DateFormatter dateFormatter;
    
    @Autowired
    ResponseHandler responseHandler;
    
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
            player.setDateOfBirth(dateFormatter.date(request.getDateOfBirth()));
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new BasicResponse("Invalid birth date"));
        }
        //player.setTeamId(request.getTeamId());
        player.setNationality(request.getNationality());
        player.setContract(request.getContract());
        player.setWage(request.getWage());
        
        log.info("Saving player.... " + playerId);
        //Player newPlayer = playerRepo.save(player);
        playerRepo.save(player);
        
        //if(newPlayer != null){
            log.info("Success create player");
            return ResponseEntity.status(HttpStatus.OK).body(
                    new BasicResponse("Success create player"));
        //} 
        //else {
        //    log.info("Fail to create player");
        //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        //            new BasicResponse("Fail to create player"));
        //}
    }

    /**
     * View detailed info of player
     * @param playerId
     * @return BasicResponse - player info
     */
    public ResponseEntity<BasicResponse> viewPlayer(String playerId) {
        
        if(playerId == null || playerId.isEmpty()){
            log.info("Player id is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse("Player id is null or empty"));
        }
        
        Player player = playerRepo.findPlayerByPlayerId(playerId);
        
        if(player != null){
            log.info("Found player: " + player.getPlayerId());
            
            PlayerViewResponse playerViewResponse = new PlayerViewResponse();
            playerViewResponse.setPlayerId(player.getPlayerId());
            playerViewResponse.setPlayerName(player.getPlayerName());
            playerViewResponse.setContract(player.getContract());
            playerViewResponse.setDateOfBirth(dateFormatter.dateString(player.getDateOfBirth()));
            playerViewResponse.setNationality(player.getNationality());
            playerViewResponse.setPosition(player.getPosition());
            playerViewResponse.setTeam(player.getTeamId().getTeamId());
            playerViewResponse.setTransferStatus(player.getTransferStatus());
            playerViewResponse.setWage(player.getWage());
            
            BasicResponse basicResponse = new BasicResponse("Success");
            basicResponse.setBody(playerViewResponse);
            
            return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
        }
        else {
            log.info("Player not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new BasicResponse("Player not found"));
        }
    }

    /**
     * Update player detail
     * @param request
     * @return 
     */
    public ResponseEntity<BasicResponse> updatePlayer(PlayerRequest request) {
        
        if(request.getPlayerId() == null || request.getPlayerId().isEmpty()){
            log.info("Player id is null or empty");
            return responseHandler.badRequest("Player id is null or empty", null);
        }
        
        String playerId = request.getPlayerId();
        log.info("Finding player with id: " + playerId);
        
        Player player = playerRepo.findPlayerByPlayerId(playerId);
        
        if(player == null){
            log.info("Player not found");
            return responseHandler.badRequest("Player not found", null);
        }
        
        if(request.getContract() != null){
            player.setContract(request.getContract());
        }
        if(request.getNationality() != null && !request.getNationality().isEmpty()){
            player.setNationality(request.getNationality());
        }
        if(request.getPlayerName() != null && !request.getPlayerName().isEmpty()){
            player.setPlayerName(request.getPlayerName());
        }
        if(request.getPosition() != null && !request.getPosition().isEmpty()){
            player.setPosition(request.getPosition());
        }
        //if(request.getTeamId() != null){
        //    player.setTeamId(request.getTeamId());
        //}
        if(request.getTransferStatus() != null){
            player.setTransferStatus(request.getTransferStatus());
        }
        if(request.getWage() != null){
            player.setWage(request.getWage());
        }
        
        log.info("Updating player detail...");
        //Player updatedPlayer = playerRepo.save(player);
        playerRepo.save(player);
        
        //if(updatedPlayer != null){
            log.info("Success update player " + playerId);
            return responseHandler.ok("Update success", null);
        //}
        //else {
        //    log.info("Fail to update player");
        //    return responseHandler.serverError("Fail to update player", null);
        //}
    }

    /**
     * Filter list of player by specific @param
     * @param playerName
     * @param position
     * @param year
     * @param teamId
     * @param nationality
     * @param transferStatus
     * @param contract
     * @param wage
     * @return list of filtered players
     */
    public ResponseEntity<BasicResponse> findPlayer(String playerName, 
            String position, String year, String teamId, String nationality, 
            String transferStatus, String contract, String wage) {
        
        List<Player> listPlayer = playerRepo.findPlayerWithParam(playerName,
                position, year, teamId, nationality, transferStatus, contract, wage);
        
        if(listPlayer != null){
            log.info("Total player(s) found: " + listPlayer.size());
            return responseHandler.ok("Total found: " + listPlayer.size(), listPlayer);
        } else {
            return responseHandler.serverError("Please retry again", null);
        }
    }
}
