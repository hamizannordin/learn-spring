/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Team;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    
    public Team findTeamById (String teamId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Team> query = criteriaBuilder.createQuery(Team.class);
        Root<Team> team = query.from(Team.class);
        
        Path<String> idPath = team.get("teamId");
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(criteriaBuilder.like(idPath, teamId));
        
        query.select(team)
                .where(criteriaBuilder
                        .and(predicates.toArray(
                                new Predicate[predicates.size()])
                        )
                );
        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
    
}
