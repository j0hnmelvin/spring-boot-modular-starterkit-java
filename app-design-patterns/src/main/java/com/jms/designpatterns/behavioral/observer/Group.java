package com.jms.designpatterns.behavioral.observer;

import java.util.LinkedHashSet;
import java.util.Set;

public class Group  {
    private final Set<GroupObserver> observers;

    public Group() {
        observers = new LinkedHashSet<>();;
    }

    public void addObserver(GroupObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GroupObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (var observer : observers) {
            observer.notify(message);
        }
    }
}
