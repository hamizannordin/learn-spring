/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.repository;

import com.hamizan.app.ligabolehland.database.Player;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hamizan
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, BigInteger> {

    @Override
    public List<Player> findAll ();
}
