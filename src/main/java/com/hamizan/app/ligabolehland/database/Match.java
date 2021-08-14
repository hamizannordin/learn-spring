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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    
    @Id
    @Column(name = "MATCH_ID")
    private String matchId;
    
    @ManyToOne
    @JoinColumn(name = "COMPETITON_ID", referencedColumnName = "COMPETITION_ID")
    private Competition competitionId;
    
    @OneToOne
    @JoinColumn(name = "HOME_TEAM_ID", referencedColumnName = "TEAM_ID")
    private Team homeTeamId;
    
    @OneToOne
    @JoinColumn(name = "AWAY_TEAM_ID", referencedColumnName = "TEAM_ID")
    private Team awayTeamId;
    
    @Column(name = "HOME_TEAM_SCORE")
    private int homeTeamScore;
    
    @Column(name = "AWAY_TEAM_SCORE")
    private int awayTeamScore;
    
    @Column(name = "MATCH_DATETIME")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date matchDatetime;
    
    @Column(name = "MATCH_STATUS")
    private String matchStatus;

    public Match() {
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Competition getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Competition competitionId) {
        this.competitionId = competitionId;
    }

    public Team getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Team homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Team getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Team awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Date getMatchDatetime() {
        return matchDatetime;
    }

    public void setMatchDatetime(Date matchDatetime) {
        this.matchDatetime = matchDatetime;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }
}
