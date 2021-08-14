/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Match;
import java.util.List;
import javax.persistence.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hamizan
 */
@Repository
public class MatchRepository extends RepositoryFacade {
    
    public MatchRepository (){
        this.log = LoggerFactory.getLogger(getClass().getName());
    }
    
    public List<Match> getMatchListByCompetitionId (String competitionId){
        String sql = "SELECT * FROM `MATCH` m WHERE m.COMPETITION_ID =:competitionId";
        try {
            Query q = entityManager.createNativeQuery(sql, Match.class);
            q.setParameter("competitionId", competitionId);
            return (List<Match>) q.getResultList();
        }
        catch (Exception e){
            log.debug(e.getMessage());
            return null;
        }
    }
}
