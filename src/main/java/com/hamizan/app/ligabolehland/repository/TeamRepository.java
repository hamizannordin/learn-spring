/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Team;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hamizan
 */
@Repository
public class TeamRepository extends RepositoryFacade {
    
    public long count () {
        return count(Team.class);
    }
    
}
