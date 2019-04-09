package com.hackerrank.github.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class EventDE implements Serializable {
    private static final long serialVersionUID = -22463671072876790L;

    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "actor_id", nullable = false)
    private Long actorId;

    @Column(name = "repo_id", nullable = false)
    private Long repoId;
    
    @Column(name = "created_on", nullable = false)
    private Timestamp createdAt;

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

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}