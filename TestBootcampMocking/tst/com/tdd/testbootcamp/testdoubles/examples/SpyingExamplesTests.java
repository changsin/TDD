package com.tdd.testbootcamp.testdoubles.examples;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.tdd.testbootcamp.testdoubles.PackageDepartEventPublisher;
import com.tdd.testbootcamp.testdoubles.support.AmazonSNSIdentifier;
import com.tdd.testbootcamp.testdoubles.support.PackageDepartEvent;
import com.amazonaws.services.sns.AmazonSNSClient;

/**
 * Mockito documentation on spies
 * See <a href="http://site.mockito.org/mockito/docs/current/org/mockito/ArgumentCaptor.html>Argument Captor (Spy)</a>
 *
 * WARNING: Mockito has an @Spy annotation and the concept of a spy. It is NOT the same thing as the spy concept we
 * learned about during the lecture portion - it refers to an advanced concept called "partial mocking."
 *
 * To run these tests from the command line, use:
 * brazil-build single-test -DtestClass=com.amazon.testbootcamp.testdoubles.examples.SpyingExamplesTests
 */
public class SpyingExamplesTests {

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Exercise 0 (with instructor):
     *
     * Task: Let's get familiar with the Mockito syntax to perform behavior verification using a Spy.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    public void spySyntaxWalkthrough() {
        final List<PackageDepartEvent> packageDepartEvents = Mockito.mock(List.class);

        packageDepartEvents.add(new PackageDepartEvent("event message"));

        final ArgumentCaptor<PackageDepartEvent> captor = ArgumentCaptor.forClass(PackageDepartEvent.class);
        Mockito.verify(packageDepartEvents).add(captor.capture());
        assertEquals("event message", captor.getValue().getEventMessage());
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 1:
     *
     * Task: intercept the data passed to a dependency via a spy. You can make any assertion you want, I just want you
     * to understand what it lets you do and how it works with Mockito.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    public void useASpyToInterceptAMessageToADependency() {
        final AmazonSNSClient snsClientTestDouble = Mockito.mock(AmazonSNSClient.class);
        final AmazonSNSIdentifier snsIdentifier = new AmazonSNSIdentifier("subject1", "topic1");
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, snsIdentifier);
        final PackageDepartEvent packageDepartEvent = new PackageDepartEvent("event message");

        sut.publishEvent(packageDepartEvent);

        /**
         * Add your spying here.
         */
        final ArgumentCaptor<String> capturedData = ArgumentCaptor.forClass(String.class);
        Mockito.verify(snsClientTestDouble).publish(capturedData.capture(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString());
        assertEquals("topic1", capturedData.getValue());
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
