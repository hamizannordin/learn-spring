/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.service;

import com.hamizan.app.ligabolehland.database.Player;
import com.hamizan.app.ligabolehland.repository.PlayerRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Player> get() {
        return playerRepo.findAll();
    }
    
}
