package com.hackerrank.github.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event implements Comparable<Event>{
    private Long id;
    private String type;
    private Actor actor;
    private Repo repo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp created_at;

    public Event() {
    }

    public Event(Long id, String type, Actor actor, Repo repo, Timestamp created_at) {
        this.id = id;
        this.type = type;
        this.actor = actor;
        this.repo = repo;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
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
        if (type != null) {
            builder.append("type\" : \"");
            builder.append(type);
            builder.append("\",\n     \"");
        }
        if (actor != null) {
            builder.append("actor\" : ");
            builder.append(actor);
            builder.append(",\n     \"");
        }
        if (repo != null) {
            builder.append("repo\" : ");
            builder.append(repo);
            builder.append(",\n     \"");
        }
        if (created_at != null) {
            builder.append("created_at\" : \"");
            builder.append(created_at);
        }
        builder.append("\"\n  }");
        return builder.toString();
    }

    @Override
    public int compareTo(Event event) {
        return this.getId().compareTo(event.getId());
    }
}