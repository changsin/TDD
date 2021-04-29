package com.tdd.mocking.support;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;


public class DepartNotificationPublishException extends RuntimeException {

    public DepartNotificationPublishException(final String string, final AmazonServiceException exception) {
        // TODO Auto-generated constructor stub
    }

    public DepartNotificationPublishException(final String string, final AmazonClientException exception) {
        // TODO Auto-generated constructor stub
    }

    public DepartNotificationPublishException(final String string, final IllegalArgumentException exception) {
        // TODO Auto-generated constructor stub
    }

}
