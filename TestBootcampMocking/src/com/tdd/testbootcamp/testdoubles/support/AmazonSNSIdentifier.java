package com.tdd.testbootcamp.testdoubles.support;

public class AmazonSNSIdentifier {

    private final String subject;
    private final String topic;

    public AmazonSNSIdentifier(final String subject, final String topic) {
        this.subject = subject;
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }
}
