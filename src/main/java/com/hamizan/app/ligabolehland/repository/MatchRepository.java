/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Match;
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
public class MatchRepository extends RepositoryFacade {
    
    public MatchRepository (){
        this.log = LoggerFactory.getLogger(getClass().getName());
    }
    
    public List<Match> getMatchListByCompetitionId (String competitionId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Match> query = criteriaBuilder.createQuery(Match.class);
        Root<Match> match = query.from(Match.class);
        
        Path<String> idPath = match.get("competitionId");
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.like(idPath, competitionId));
        
        query.select(match).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        
        return entityManager.createQuery(query).getResultList();
    }
}
