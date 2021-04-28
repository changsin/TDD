package com.tdd.testbootcamp.testdoubles.examples;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishResult;
import com.tdd.testbootcamp.testdoubles.PackageDepartEventPublisher;
import com.tdd.testbootcamp.testdoubles.support.AmazonSNSIdentifier;
import com.tdd.testbootcamp.testdoubles.support.PackageDepartEvent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Welcome to the Test Bootcamp: Mocking and Stubbing hands-on workshop. In this workshop we'll be using Java and a
 * well-known test double framework called Mockito.
 *
 * This class has test cases that introduce mocking (verifying calls to dependencies).
 *
 * Mockito Documentation on mocking:
 * See <a href="http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html#4">Mockito Mocking Examples</a>
 *
 */
public class MockingExamplesTests {

    /**
     * Exercise 0 (with instructor) Let's get familiar with the Mockito syntax to perform behavior verification using a
     * mock.
     *
     * Let's see if we can use behavior verification to ensure that a mock object is called exactly once.
     */
    @Test
    public void walkThroughMockingSyntaxExample() {
        final List<String> mockList = Mockito.mock(List.class);

        mockList.add("adding some string value");

        /**
         * Mockito implicitly checks that the call happened exactly 1 time.
         */
        Mockito.verify(mockList, Mockito.times(2)).add(ArgumentMatchers.anyString());
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 1: In addition to being able to check whether a method was called a certain number of times,
     * we also covered that we could verify that a dependency is called with a specific value (verifying the message
     * being passed).
     *
     * Task: Use a mock to verify that the SNS client is passing the correct values when publishing an event.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    public void useMockToVerifyADependencyIsCalledWithSpecificParameter() {
        final AmazonSNSClient snsClientTestDouble = Mockito.mock(AmazonSNSClient.class);
        final AmazonSNSIdentifier snsIdentifier = new AmazonSNSIdentifier("subject1", "topic1");
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, snsIdentifier);
        final PackageDepartEvent packageDepartEvent = new PackageDepartEvent("event message");

        sut.publishEvent(packageDepartEvent);

        /**
         * Add mock verification here
         */
        Mockito.verify(snsClientTestDouble).publish("topic1", "event message", "subject1");
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 2: In our previous stubbing test case we were able to get our dependency replaced with a test
     * double (stub) and then have that return different values each time it was called. What if it was *really*
     * important to make sure that we're not retrying a call to the SNS client too many times because the call takes a
     * lot of time and you were working in a latency-sensitive service?
     *
     * Task: Use a mock to verify that the SNS client was called exactly twice.
     *
     * Hint: The Mockito.verify API accepts a 2nd parameter other than the mock.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    public void addVerificationToPreviousMultiStubbingExample() {
        final AmazonSNSClient snsClientTestDouble = Mockito.mock(AmazonSNSClient.class);
        final AmazonSNSIdentifier snsIdentifier = new AmazonSNSIdentifier("subject", "topic");
        final PackageDepartEvent packageDepartEvent = new PackageDepartEvent("some event message");
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, snsIdentifier);

        final PublishResult expectedResult = new PublishResult();

        Mockito.when(snsClientTestDouble.publish(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()))
                .thenThrow(AmazonServiceException.class)
                .thenReturn(expectedResult);

        final PublishResult actualResult = sut.publishEvent(packageDepartEvent);

        assertEquals(expectedResult, actualResult);
        /**
         * The code above gets us to the point we were before where we were using state verification to compare the
         * expected and actual results. For this example though, let's go ahead and use behavior verification via a
         * mock.
         *
         * Add mock verification below
         */
        Mockito.verify(snsClientTestDouble, Mockito.times(2)).publish(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
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
