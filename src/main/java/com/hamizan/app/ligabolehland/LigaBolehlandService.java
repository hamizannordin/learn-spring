/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland;

import com.hamizan.app.ligabolehland.repository.CompetitionRepository;
import com.hamizan.app.ligabolehland.repository.MatchRepository;
import com.hamizan.app.ligabolehland.repository.PlayerRepository;
import com.hamizan.app.ligabolehland.repository.TeamRepository;
import com.hamizan.app.ligabolehland.util.DateFormatter;
import com.hamizan.app.ligabolehland.util.ResponseHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hamizan
 */
public class LigaBolehlandService {
    
    public Logger log;
    
    @Autowired
    public CompetitionRepository competitionRepository;
    
    @Autowired
    public MatchRepository matchRepository;
    
    @Autowired
    public PlayerRepository playerRepository;
    
    @Autowired
    public TeamRepository teamRepository;
    
    @Autowired
    public DateFormatter dateFormatter;
    
    @Autowired
    public ResponseHandler responseHandler;
}
