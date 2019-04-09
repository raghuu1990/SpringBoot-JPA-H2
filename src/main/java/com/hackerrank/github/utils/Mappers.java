package com.hackerrank.github.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hackerrank.github.entity.ActorDE;
import com.hackerrank.github.entity.EventDE;
import com.hackerrank.github.entity.RepoDE;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;

public class Mappers {
    public static List<Event> getEvents(Iterable<EventDE> eventDEs, List<Repo> repos, List<Actor> actors) {
        List<Event> events = new ArrayList<Event>();
        HashMap<Long, Repo> repoMap = new HashMap<Long, Repo>();
        HashMap<Long, Actor> actorMap = new HashMap<Long, Actor>();
        for (Repo repo : repos) {
            repoMap.put(repo.getId(), repo);
        }
        for (Actor actor : actors) {
            actorMap.put(actor.getId(), actor);
        }
        
        for (EventDE eventDE : eventDEs) {
            events.add(getEvent(eventDE, actorMap.get(eventDE.getActorId()), repoMap.get(eventDE.getRepoId())));
        }
        return events;
    }
    
    public static List<Event> getEvents(Iterable<EventDE> eventDEs, List<Repo> repos, Actor actor) {
        List<Event> events = new ArrayList<Event>();
        HashMap<Long, Repo> repoMap = new HashMap<Long, Repo>();
        for (Repo repo : repos) {
            repoMap.put(repo.getId(), repo);
        }
        for (EventDE eventDE : eventDEs) {
            events.add(getEvent(eventDE, actor, repoMap.get(eventDE.getRepoId())));
        }
        return events;
    }

    public static Event getEvent(EventDE eventDE, Actor actor, Repo repo) {
        Event event = new Event();
        event.setId(eventDE.getId());
        event.setType(eventDE.getType());
        event.setActor(actor);
        event.setRepo(repo);
        event.setCreated_at(eventDE.getCreatedAt());
        return event;
    }

    public static Actor getActor(ActorDE actorDE) {
        Actor actor = new Actor();
        actor.setId(actorDE.getId());
        actor.setLogin(actorDE.getLogin());
        actor.setAvatar_url(actorDE.getAvatar());
        
        actor.setCurrStreak(actorDE.getCurrStreak());
        actor.setLastActive(actorDE.getLastActive());
        actor.setMaxStreak(actorDE.getMaxStreak());
        actor.setTotalEvents(actorDE.getTotalEvents());
        
        return actor;
    }

    public static Repo getRepo(RepoDE repoDE) {
        Repo repo = new Repo();
        repo.setId(repoDE.getId());
        repo.setName(repoDE.getName());
        repo.setUrl(repoDE.getUrl());
        return repo;
    }

    public static EventDE getEventDE(Event event) {
        EventDE eventDE = new EventDE();
        eventDE.setId(event.getId());
        eventDE.setType(event.getType());
        eventDE.setActorId(event.getActor().getId());
        eventDE.setRepoId(event.getRepo().getId());
        eventDE.setCreatedAt(event.getCreated_at());
        return eventDE;
    }
    
    public static List<EventDE> getEventDEs(List<Event> events) {
        List<EventDE> eventDEs = new ArrayList<EventDE>();
        for (Event event : events) {
            eventDEs.add(getEventDE(event));
        }
        return eventDEs;
    }

    public static ActorDE getActorDE(Actor actor) {
        ActorDE actorDE = new ActorDE();
        actorDE.setId(actor.getId());
        actorDE.setLogin(actor.getLogin());
        actorDE.setAvatar(actor.getAvatar_url());
        actorDE.setCurrStreak(actor.getCurrStreak());
        actorDE.setLastActive(actor.getLastActive());
        actorDE.setMaxStreak(actor.getMaxStreak());
        actorDE.setTotalEvents(actor.getTotalEvents());
        return actorDE;
    }

    public static RepoDE getRepoDE(Repo repo) {
        RepoDE repoDE = new RepoDE();
        repoDE.setId(repo.getId());
        repoDE.setName(repo.getName());
        repoDE.setUrl(repo.getUrl());
        return repoDE;
    }

    public static List<Actor> getActors(Iterable<ActorDE> actorDEs) {
        List<Actor> actors = new ArrayList<Actor>();
        for (ActorDE actorDE : actorDEs) {
            actors.add(getActor(actorDE));
        }
        return actors;
    }

    public static List<Repo> getRepos(Iterable<RepoDE> repoDEs) {
        List<Repo> repos = new ArrayList<Repo>();
        for (RepoDE repoDE : repoDEs) {
            repos.add(getRepo(repoDE));
        }
        return repos;
    }

    public static List<Actor> getActors(List<ActorDE> actorDEs) {
        List<Actor> actors = new ArrayList<Actor>();
        for (ActorDE actorDE : actorDEs) {
            actors.add(getActor(actorDE));
        }
        return actors;
    }

    public static List<Repo> getRepos(List<RepoDE> repoDEs) {
        List<Repo> repos = new ArrayList<Repo>();
        for (RepoDE repoDE : repoDEs) {
            repos.add(getRepo(repoDE));
        }
        return repos;
    }

    public static List<ActorDE> getActorDEs(List<Actor> actors) {
        List<ActorDE> actorDEs = new ArrayList<ActorDE>();
        for (Actor actor : actors) {
            actorDEs.add(getActorDE(actor));
        }
        return actorDEs;
    }

    public static List<RepoDE> getRepoDEs(List<Repo> repos) {
        List<RepoDE> repoDEs = new ArrayList<RepoDE>();
        for (Repo repo : repos) {
            repoDEs.add(getRepoDE(repo));
        }
        return repoDEs;
    }

    public static ActorDE getUpdatedActorDE(ActorDE actorDE, Actor actor) {
        actorDE.setId(actor.getId());
        actorDE.setLogin(actor.getLogin());
        actorDE.setAvatar(actor.getAvatar_url());
        actorDE.setTotalEvents(actorDE.getTotalEvents()+1);
        
        if(isNextDay(actorDE.getLastActive(), actor.getLastActive())) {
            actorDE.setCurrStreak(actorDE.getCurrStreak()+1);
            actorDE.setMaxStreak(Math.max(actorDE.getMaxStreak(), actorDE.getCurrStreak()));
        }else {
            actorDE.setCurrStreak(1l);
        }
        actorDE.setLastActive(getLattestTime(actorDE.getLastActive(), actor.getLastActive()));
        return actorDE;
    }
    
    public static ActorDE getUpdatedUrlActorDE(ActorDE actorDE, Actor actor) {
        actorDE.setAvatar(actor.getAvatar_url());
        return actorDE;
    }

    public static boolean isNextDay(Timestamp t1, Timestamp t2) {
        if(t1.getYear()==t2.getYear()) {
            if(t1.getMonth()==t2.getMonth()) {
                if(Math.abs(t2.getDate()-t1.getDate())==1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Timestamp getLattestTime(Timestamp t1, Timestamp t2) {
        if(t2.before(t1)) {
            return t1;
        }
        return t2;
    }
}