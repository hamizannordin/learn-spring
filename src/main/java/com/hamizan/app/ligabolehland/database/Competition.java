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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "COMPETITION")
public class Competition implements Serializable {
    
    @Id
    @Column(name = "COMPETITION_ID")
    private String competitionId;
    
    @Column(name = "COMPETITION_NAME")
    private String competitionName;
    
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "COMPETITION_START")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date competitionStart;
    
    @Column(name = "COMPETITION_END")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date competitionEnd;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TOTAL_TEAM")
    private int totalTeam;
    
    public Competition() {
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCompetitionStart() {
        return competitionStart;
    }

    public void setCompetitionStart(Date competitionStart) {
        this.competitionStart = competitionStart;
    }

    public Date getCompetitionEnd() {
        return competitionEnd;
    }

    public void setCompetitionEnd(Date competitionEnd) {
        this.competitionEnd = competitionEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalTeam() {
        return totalTeam;
    }

    public void setTotalTeam(int totalTeam) {
        this.totalTeam = totalTeam;
    }
    
}
