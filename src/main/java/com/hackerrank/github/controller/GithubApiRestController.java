package com.hackerrank.github.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.service.ActorService;
import com.hackerrank.github.service.EventService;
import com.hackerrank.github.service.RepoService;
import com.hackerrank.github.utils.ActorComperator;

@RestController
public class GithubApiRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubApiRestController.class);

    @Autowired
    RepoService repoService;

    @Autowired
    EventService eventService;

    @Autowired
    ActorService actorService;

    @DeleteMapping("/erase")
    private ResponseEntity<Void> delete() {
        LOGGER.info("Delete all request recieved");
        try {
            eventService.deleteAll();
        } catch (Exception e) {
            LOGGER.error("Error while deleting all events", e);
        }
        LOGGER.info("All data deleted successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/events")
    private ResponseEntity<Void> save(@RequestBody Event event) {
        LOGGER.info("Save event request recieved: {}", event);
        try {
            eventService.save(event);
        } catch (Exception e) {
            LOGGER.error("Error while saving event: {}", event, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/events")
    private ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        try {
            events = eventService.findAll();
            Collections.sort(events);
        } catch (Exception e) {
            LOGGER.error("Error while fetching all events", e);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @GetMapping("/events/actors/{actorId}")
    private ResponseEntity<List<Event>> getEventByActorId(@PathVariable Long actorId) {
        try {
            List<Event> events = eventService.findAllByActorId(actorId);
            Collections.sort(events);
            return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error while fetching events for actorId:{}", actorId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actors")
    private ResponseEntity<Void> update(@RequestBody Actor actor) {
        LOGGER.info("Update actor request recieved: {}", actor);
        try {
            boolean status = actorService.update(actor);
            if (status) {
                LOGGER.info("Actor updated successfully");
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                LOGGER.error("Your can't update actor's login");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            LOGGER.error("Error while updating actor", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/actors")
    private ResponseEntity<List<Actor>> getActors() {
        List<Actor> actors = new ArrayList<Actor>();
        try {
            actors = actorService.findAll();
            Collections.sort(actors);
        } catch (Exception e) {
            LOGGER.error("Error while fetchig all actors", e);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/actors/streak")
    private ResponseEntity<List<Actor>> getActorsStreak() {
        List<Actor> actors = new ArrayList<Actor>();
        try {
            actors = actorService.findAll();
            Collections.sort(actors, new ActorComperator());
        } catch (Exception e) {
            LOGGER.error("Error while fetching actor's streak", e);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
}