/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.LigaBolehlandRepository;
import com.hamizan.app.ligabolehland.database.Competition;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hamizan
 */
@Repository
public class CompetitionRepository extends LigaBolehlandRepository {

    public CompetitionRepository() {
        this.log = LoggerFactory.getLogger(getClass().getName());
    }
    
    public long count () {
        return count(Competition.class);
    }
    
    public Competition findByCompetitionId (String competitionId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Competition> query = criteriaBuilder.createQuery(Competition.class);
        Root<Competition> competition = query.from(Competition.class);
        
        Path<String> idPath = competition.get("competitionId");
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.like(idPath, competitionId));
        
        query.select(competition).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        
        return entityManager.createQuery(query).getSingleResult();
    }
    
}
