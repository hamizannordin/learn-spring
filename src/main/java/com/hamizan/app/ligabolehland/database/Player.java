/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {
    
    @Id
    @Column(name = "PLAYER_ID")
    private String playerId;
    
    @Column(name = "PLAYER_NAME")
    private String playerName;
    
    @Column(name = "POSITION")
    private String position;
    
    @Column(name = "DATEOFBIRTH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName="TEAM_ID")
    private Team teamId;
    
    @Column(name = "NATIONALITY")
    private String nationality;
    
    @Column(name = "TRANSFER_STATUS")
    private String transferStatus;
    
    @Column(name = "CONTRACT")
    private String contract;
    
    @Column(name = "WAGE")
    private String wage;

    public Player() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Team getTeamId() {
        return teamId;
    }

    public void setTeamId(Team teamId) {
        this.teamId = teamId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }
}
