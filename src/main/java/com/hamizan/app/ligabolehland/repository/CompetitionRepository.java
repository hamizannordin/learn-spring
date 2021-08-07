/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Competition;
import javax.persistence.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hamizan
 */
@Repository
public class CompetitionRepository extends RepositoryFacade {

    public CompetitionRepository() {
        this.log = LoggerFactory.getLogger(getClass().getName());
    }
    
    public long count () {
        return count(Competition.class);
    }
    
    public Competition findByCompetitionId (String competitionId){
        String sql = "SELECT * FROM COMPETITION c WHERE c.COMPETITION_ID =:competitionId";
        try {
            Query q = entityManager.createNativeQuery(sql, Competition.class);
            q.setParameter("competitionId", competitionId);
            return (Competition) q.getSingleResult();
        }
        catch (Exception e){
            log.debug(e.getMessage());
            return null;
        }
    }
    
}
