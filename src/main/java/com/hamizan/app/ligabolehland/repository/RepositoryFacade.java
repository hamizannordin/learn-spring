/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hamizan
 */
public class RepositoryFacade {
    
    Logger log;
    
    @PersistenceContext
    public EntityManager entityManager;
    
    @Transactional
    public void save (Object object){
        entityManager.persist(object);
    }
    
    public long count (Class c) {
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        
        query.select(criteriaBuilder.count(query.from(c)));
        
        return entityManager.createQuery(query).getSingleResult();
    }
    
}
