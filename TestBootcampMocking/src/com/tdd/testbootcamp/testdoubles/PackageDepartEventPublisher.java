package com.tdd.testbootcamp.testdoubles;

import com.tdd.testbootcamp.testdoubles.support.AmazonSNSIdentifier;
import com.tdd.testbootcamp.testdoubles.support.DepartNotificationPublishException;
import com.tdd.testbootcamp.testdoubles.support.PackageDepartEvent;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.google.common.base.Strings;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PackageDepartEventPublisher {

    private final Logger logger = Logger.getGlobal();

    private final AmazonSNS snsClient;
    private final AmazonSNSIdentifier snsIdentifier;

    public PackageDepartEventPublisher(final AmazonSNS snsClient, final AmazonSNSIdentifier snsIdentifier) {
        this.snsClient = checkNotNull(snsClient, "snsClient is null");
        this.snsIdentifier = checkNotNull(snsIdentifier, "snsIdentifier is null");
    }

    public PublishResult publishEvent(final PackageDepartEvent packageDepartEvent) {
        checkNotNull(packageDepartEvent, "The packageDepartEvent can't be null.");
        checkArgument(!Strings.isNullOrEmpty(packageDepartEvent.getEventMessage()),
                "The packageDepartEvent message can't be null or empty.");

        final String eventMessage = packageDepartEvent.getEventMessage();
        PublishResult result = null;
        try {
            result = snsClient.publish(snsIdentifier.getTopic(), eventMessage, snsIdentifier.getSubject());
        } catch (final AmazonServiceException exception) {
            logger.log(Level.WARNING, "There was an error when publishing the event! Attempting retry.");
            // Simple retry
            result = snsClient.publish(snsIdentifier.getTopic(), eventMessage, snsIdentifier.getSubject());
        } catch (final AmazonClientException exception) {
            logger.log(Level.SEVERE, "There was an error when publishing the event!");
            throw new DepartNotificationPublishException("Error returned by SNS: " + exception.toString(), exception);
        }

        return result;
    }
}
/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * Intentionally leaving blank lines here because sometimes the screen won't scroll when using Projector mode in
 * Windows.
 */