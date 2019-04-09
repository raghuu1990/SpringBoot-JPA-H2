package com.hackerrank.github.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.github.entity.RepoDE;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.utils.GitException;
import com.hackerrank.github.utils.Mappers;

@Service
public class RepoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepoService.class);

    @Autowired
    RepoRepository repoRepository;

    public Repo findById(Long id) throws GitException {
        Optional<RepoDE> repoDE = repoRepository.findById(id);
        if (repoDE.isPresent()) {
            return Mappers.getRepo(repoDE.get());
        }
        LOGGER.info("No repo found for id:{}", id);
        throw new GitException("No repo found for given request");
    }

    public List<Repo> findAll() {
        Iterable<RepoDE> repoDEs = repoRepository.findAll();
        return Mappers.getRepos(repoDEs);
    }

    public Repo save(Repo repo) {
        repoRepository.save(Mappers.getRepoDE(repo));
        return repo;
    }

    public void deleteAll() {
        repoRepository.deleteAll();
        System.out.println("#############   All deleted (repo)  : "+ findAll()+"   ##############");
    }
}