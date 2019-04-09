package com.hackerrank.github.utils;

import java.util.Comparator;

import com.hackerrank.github.model.Actor;

public class ActorComperator implements Comparator<Actor> {
    @Override
    public int compare(Actor actor1, Actor actor2) {
        if (actor1.getMaxStreak().equals(actor2.getMaxStreak())) {
            if (actor1.getLastActive().equals(actor2.getLastActive())) {
                return actor1.getLogin().compareTo(actor2.getLogin());
            }
            return actor2.getLastActive().compareTo(actor1.getLastActive());
        }
        return actor2.getMaxStreak().compareTo(actor1.getMaxStreak());
    }
}