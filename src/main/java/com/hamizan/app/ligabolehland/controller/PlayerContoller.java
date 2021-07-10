/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.controller;

import com.hamizan.app.ligabolehland.LigaBolehlandController;
import com.hamizan.app.ligabolehland.database.Player;
import com.hamizan.app.ligabolehland.service.PlayerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hamizan
 */
@RestController
public class PlayerContoller extends LigaBolehlandController {
    
    @Autowired
    PlayerService service;
    
    @GetMapping("/player")
    public List<Player> get () {
        return service.get();
    }
    
}
