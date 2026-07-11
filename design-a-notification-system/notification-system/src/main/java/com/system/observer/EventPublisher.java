package com.system.observer;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    private final List<NotificationEventListener> listeners;


    public EventPublisher() {
        this.listeners = new ArrayList<>();
    }


    public void subscribe(NotificationEventListener listener) {
        listeners.add(listener);
    }


    public void unsubscribe(NotificationEventListener listener) {
        listeners.remove(listener);
    }


    public void publish(Event event) {

        for(NotificationEventListener listener : listeners) {

            listener.onEvent(event);
        }
    }
}
