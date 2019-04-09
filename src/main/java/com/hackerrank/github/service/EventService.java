package com.hackerrank.github.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.github.entity.EventDE;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.utils.GitException;
import com.hackerrank.github.utils.Mappers;

@Service
public class EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Autowired
    RepoService repoService;

    @Autowired
    ActorService actorService;

    @Autowired
    EventRepository eventRepository;

    public Event findById(Long id) throws GitException {
        Optional<EventDE> eventDE = eventRepository.findById(id);
        if (eventDE.isPresent()) {
            Actor actor = actorService.findById(id);
            Repo repo = repoService.findById(id);
            return Mappers.getEvent(eventDE.get(), actor, repo);
        }
        LOGGER.info("No event found for id:{}", id);
        throw new GitException("No event found for given request");
    }

    public List<Event> findAllByActorId(Long id) throws GitException {
        Actor actor = actorService.findById(id);
        Iterable<EventDE> eventDEs = eventRepository.findByActorId(id);
        List<Repo> repos = repoService.findAll();
        return Mappers.getEvents(eventDEs, repos, actor);
    }

    public List<Event> findAll() {
        List<Repo> repos = repoService.findAll();
        List<Actor> actors = actorService.findAll();
        Iterable<EventDE> eventDEs = eventRepository.findAll();
        return Mappers.getEvents(eventDEs, repos, actors);
    }

    @Transactional
    public Event save(Event event) throws GitException {
        repoService.save(event.getRepo());
        event.getActor().setLastActive(event.getCreated_at());
        actorService.save(event.getActor());
        eventRepository.save(Mappers.getEventDE(event));
        return event;
    }

    @Transactional
    public void deleteAll() {
        repoService.deleteAll();
        actorService.deleteAll();
        eventRepository.deleteAll();
    }
}