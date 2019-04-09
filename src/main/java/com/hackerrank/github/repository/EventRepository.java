package com.hackerrank.github.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.entity.EventDE;

public interface EventRepository extends CrudRepository<EventDE, Long> {
    @Query("SELECT event FROM EventDE event WHERE event.actorId = ?1")
    Iterable<EventDE> findByActorId(Long id);
}