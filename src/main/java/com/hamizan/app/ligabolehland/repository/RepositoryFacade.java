/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hamizan
 */
public class RepositoryFacade {
    
    @PersistenceContext
    public EntityManager entityManager;
    
    @Transactional
    public void save (Object object){
        entityManager.persist(object);
    }
    
}
