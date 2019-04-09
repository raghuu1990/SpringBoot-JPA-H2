package com.hackerrank.github.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.entity.RepoDE;

public interface RepoRepository extends CrudRepository<RepoDE, Long> {
}