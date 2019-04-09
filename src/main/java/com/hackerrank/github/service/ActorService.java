package com.hackerrank.github.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.github.entity.ActorDE;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.utils.GitException;
import com.hackerrank.github.utils.Mappers;

@Service
public class ActorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    @Autowired
    ActorRepository actorRepository;

    public Actor findById(Long id) throws GitException {
        Optional<ActorDE> actorDE = actorRepository.findById(id);
        if (actorDE.isPresent()) {
            return Mappers.getActor(actorDE.get());
        }
        LOGGER.info("No actor found for id:{}", id);
        throw new GitException("No actor found for given request");
    }

    public List<Actor> findAll() {
        Iterable<ActorDE> actorDEs = actorRepository.findAll();
        return Mappers.getActors(actorDEs);
    }

    @Transactional
    public Actor save(Actor actor) {
        Optional<ActorDE> actorDE = actorRepository.findById(actor.getId());
        if (actorDE.isPresent()) {
            actorRepository.save(Mappers.getUpdatedActorDE(actorDE.get(), actor));
        } else {
            actor.setTotalEvents(1l);
            actor.setCurrStreak(1l);
            actor.setMaxStreak(1l);
            actorRepository.save(Mappers.getActorDE(actor));
            return actor;
        }
        return actor;
    }
    
    @Transactional
    public boolean update(Actor actor) throws GitException {
        Optional<ActorDE> actorDE = actorRepository.findById(actor.getId());
        if (actorDE.isPresent()) {
            if(actorDE.get().getLogin().equals(actor.getLogin())) {
                actorDE.get().setAvatar(actor.getAvatar_url());
                actorRepository.save(actorDE.get());
                return true;
            }
            return false;
        }
        throw new GitException("No actor found for given request");
    }

    public void deleteAll() {
        actorRepository.deleteAll();
    }
}