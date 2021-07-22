/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.request;

/**
 *
 * @author hamizan
 */
public class TeamRequest {
    
    private String teamName;
    private String stadiumId;
    private String leagueId;
    private String cupId;
    private String managerId;
    private String homeKit;
    private String awayKit;

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

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getCupId() {
        return cupId;
    }

    public void setCupId(String cupId) {
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
