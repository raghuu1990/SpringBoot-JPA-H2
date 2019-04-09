package com.hackerrank.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.entity.ActorDE;

public interface ActorRepository extends CrudRepository<ActorDE, Long> {
}