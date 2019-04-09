package com.hackerrank.github.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class ActorDE implements Serializable {
    private static final long serialVersionUID = -55463671072876222L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "avatar", nullable = false)
    private String avatar;
    
    @Column(name = "total_events", nullable = false)
    private Long totalEvents;

    @Column(name = "curr_streak", nullable = false)
    private Long currStreak;
    
    @Column(name = "max_streak", nullable = false)
    private Long maxStreak;
    
    @Column(name = "last_active", nullable = false)
    private Timestamp lastActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public Long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(Long totalEvents) {
        this.totalEvents = totalEvents;
    }
    
    public Long getCurrStreak() {
        return currStreak;
    }

    public void setCurrStreak(Long currStreak) {
        this.currStreak = currStreak;
    }

    public Long getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(Long maxStreak) {
        this.maxStreak = maxStreak;
    }

    public Timestamp getLastActive() {
        return lastActive;
    }

    public void setLastActive(Timestamp lastActive) {
        this.lastActive = lastActive;
    }
}