/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Player;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class PlayerImpRepository implements PlayerCustomRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Player> findPlayerWithParam (String playerName, 
            String position, String year, String teamId, String nationality, 
            String transferStatus, String contract, String wage){
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query = criteriaBuilder.createQuery(Player.class);
        Root<Player> player = query.from(Player.class);
        
        Path<String> namePath = player.get("playerName");
        Path<String> positionPath = player.get("position");
        Path<Date> yearPath = player.get("dateOfBirth");
        Path<String> teamPath = player.get("teamId");
        Path<String> nationalityPath = player.get("nationality");
        Path<String> transferPath = player.get("transferStatus");
        Path<String> contractPath = player.get("contract");
        Path<String> wagePath = player.get("wage");
        
        List<Predicate> predicates = new ArrayList<>();
        
        if(playerName != null && !playerName.isEmpty()){
            predicates.add(criteriaBuilder.like(namePath, playerName));
        }
        if(year != null && !year.isEmpty()){
            Calendar yearStart = new GregorianCalendar();
            Calendar yearEnd = new GregorianCalendar();
            
            yearStart.set(Integer.parseInt(year), 0, 1);
            yearEnd.set(Integer.parseInt(year), 11, 31);
            
            predicates.add(criteriaBuilder.between(yearPath, yearStart.getTime(), yearEnd.getTime()));
        }
        if(position != null && !position.isEmpty()){
            predicates.add(criteriaBuilder.like(positionPath, position));
        }
        if(teamId != null && !teamId.isEmpty()){
            predicates.add(criteriaBuilder.like(teamPath, teamId));
        }
        if(nationality != null && !nationality.isEmpty()){
            predicates.add(criteriaBuilder.like(nationalityPath, nationality));
        }
        if(transferStatus != null && !transferStatus.isEmpty()){
            predicates.add(criteriaBuilder.like(transferPath, transferStatus));
        }
        if(contract != null && !contract.isEmpty()){
            predicates.add(criteriaBuilder.like(contractPath, contract));
        }
        if(wage != null && !wage.isEmpty()){
            predicates.add(criteriaBuilder.like(wagePath, wage));
        }
        
        query.select(player).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        
        return entityManager.createQuery(query).getResultList();
    }
    
}
