package com.system.observer;

import com.system.enums.EventType;

public class Event {

    private final EventType eventType;
    private final Object data;


    public Event(EventType eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }


    public EventType getEventType() {
        return eventType;
    }


    public Object getData() {
        return data;
    }
}
