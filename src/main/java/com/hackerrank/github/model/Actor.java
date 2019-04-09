package com.hackerrank.github.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Actor implements Comparable<Actor> {
    private Long id;
    private String login;
    private String avatar_url;

    @JsonIgnore
    private Long totalEvents;

    @JsonIgnore
    private Long currStreak;

    @JsonIgnore
    private Long maxStreak;

    @JsonIgnore
    private Timestamp lastActive;

    public Actor() {
    }

    public Actor(Long id, String login, String avatar_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public Actor(Long id, String login, String avatar_url, Long totalEvents, Long currStreak, Long maxStreak,
            Timestamp lastActive) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.totalEvents = totalEvents;
        this.currStreak = currStreak;
        this.maxStreak = maxStreak;
        this.lastActive = lastActive;
    }

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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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

    @Override
    public int compareTo(Actor actor) {
        if (actor.getTotalEvents().equals(this.getTotalEvents())) {
            if (actor.getLastActive().equals(this.getLastActive())) {
                return this.getLogin().compareTo(actor.getLogin());
            }
            return actor.getLastActive().compareTo(this.getLastActive());
        }
        return actor.getTotalEvents().compareTo(this.getTotalEvents());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" {\n     \"");
        if (id != null) {
            builder.append("id\" : \"");
            builder.append(id);
            builder.append("\",\n     \"");
        }
        if (login != null) {
            builder.append("login\" : \"");
            builder.append(login);
            builder.append("\",\n     \"");
        }
        if (avatar_url != null) {
            builder.append("avatar_url\" : \"");
            builder.append(avatar_url);
        }
        builder.append("\"\n  }");
        return builder.toString();
    }
}