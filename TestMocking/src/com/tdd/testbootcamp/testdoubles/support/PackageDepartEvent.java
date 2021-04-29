package com.tdd.testbootcamp.testdoubles.support;

public class PackageDepartEvent {

    private final String eventMessage;

    public PackageDepartEvent(final String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }
}
