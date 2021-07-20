/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Player;
import java.util.List;

/**
 *
 * @author hamizan
 */
public interface PlayerCustomRepository {
    
    public List<Player> findPlayerWithParam (String playerName, 
            String position, String year, String teamId, String nationality, 
            String transferStatus, String contract, String wage);
    
}
