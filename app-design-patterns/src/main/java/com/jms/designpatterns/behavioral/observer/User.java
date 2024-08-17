package com.jms.designpatterns.behavioral.observer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User implements GroupObserver {
    private final int userId;

    public User(int id) {
        userId = id;
    }

    @Override
    public void notify(String message) {
        log.info("User {} received message {}", userId, message);
    }
}
