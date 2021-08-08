/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.request;

import java.util.List;

/**
 *
 * @author hamizan
 */
public class CompetitionAddTeamRequest {
 
    private List<String> teamList;

    public List<String> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<String> teamList) {
        this.teamList = teamList;
    }
    
}
