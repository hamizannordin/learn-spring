/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.database;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "TEAM")
public class Team implements Serializable {
    
    @Id
    @Column(name = "TEAM_ID")
    private String teamId;
    
    @Column(name = "TEAM_NAME")
    private String teamName;
    
    @Column(name = "STADIUM_ID")
    private String stadiumId;
    
    @ManyToOne
    @JoinColumn(name = "LEAGUE_ID", referencedColumnName = "COMPETITION_ID")
    private Competition leagueId;
    
    @ManyToOne
    @JoinColumn(name = "CUP_ID", referencedColumnName = "COMPETITION_ID")
    private Competition cupId;
    
    @Column(name = "MANAGER_ID")
    private String managerId;
    
    @Column(name = "HOME_KIT")
    private String homeKit;
    
    @Column(name = "AWAY_KIT")
    private String awayKit;

    public Team() {
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(String stadiumId) {
        this.stadiumId = stadiumId;
    }

    public Competition getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Competition leagueId) {
        this.leagueId = leagueId;
    }

    public Competition getCupId() {
        return cupId;
    }

    public void setCupId(Competition cupId) {
        this.cupId = cupId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getHomeKit() {
        return homeKit;
    }

    public void setHomeKit(String homeKit) {
        this.homeKit = homeKit;
    }

    public String getAwayKit() {
        return awayKit;
    }

    public void setAwayKit(String awayKit) {
        this.awayKit = awayKit;
    }
    
}
