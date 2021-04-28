package com.tdd.testbootcamp.testdoubles.examples;

import com.tdd.testbootcamp.testdoubles.PackageDepartEventPublisher;
import com.tdd.testbootcamp.testdoubles.support.AmazonSNSIdentifier;
import com.tdd.testbootcamp.testdoubles.support.DepartNotificationPublishException;
import com.tdd.testbootcamp.testdoubles.support.PackageDepartEvent;
import com.tdd.testbootcamp.testdoubles.support.UnstableSNSClient;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Welcome to the Test Bootcamp: Mocking and Stubbing hands-on workshop. In this workshop we'll be using Java and a
 * well-known test double framework called Mockito.
 *
 * This class has test cases that introduce stubbing (setting return values). You will need to remove the @Ignore
 * annotation from the test methods prior to running them or it won't execute.
 */
public class StubbingExamplesTests {

    private static final String EVENT_MESSAGE = "some message";
    private static final String MESSAGE_ID = "messageId";
    private static final String TOPIC = "someTopic";
    private static final String SUBJECT = "someSubject";

    /**
     * You can declare test doubles in Mockito by using the @Mock annotations. Alternatively, you can call the static
     * Mockito.mock(class) method. We're going to be using the static method call to make it easier to follow the code
     * while learning.
     *
     * Note: Mockito (and most other frameworks) call ALL test doubles "mocks", but they could be stubs, mocks, or spies
     * depending on how you use them.
     */

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Walkthrough Exercise 0 (with instructor):
     *
     * Task: In this exercise, we'll see how an unstable dependency can lead to problems testing a behavior in a
     * consistent manner.
     *
     * This test is entirely contrived to present the example of a "flaky" external dependency that would be a good
     * candidate for replacing with a test double in order to isolate your SUT and be able to test it with a consistent
     * state.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    // This test is ignored so that the build doesn't fail. Comment out/remove this @Ignore to run locally.
    @Disabled
    public void unstableDependencyExample() throws Exception {
        /**
         * These classes are the direct dependencies that the SUT PackageDepartEventPublisher uses.
         */
        final AmazonSNS unstableClient = new UnstableSNSClient();
        final AmazonSNSIdentifier someSnsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);

        /**
         * In these exercises the PackageDepartEventPublisher class is going to be the class you want to test, also
         * known as the System Under Test (SUT).
         */
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(unstableClient, someSnsIdentifier);

        /**
         * We need some event to publish, so we create one.
         */
        final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);

        final PublishResult actualResult = sut.publishEvent(eventToPublish);

        assertNotEquals(null, actualResult);
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Walkthrough Exercise 1 (with instructor):
     *
     * Task: In this exercise, we replace the unstable dependency with a test double. This lets us isolate the system
     * under test from the unstable dependency.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    public void isolatingTheSUTFromUnstableDependencyExample() throws Exception {
        final AmazonSNS snsClientTestDouble = Mockito.mock(AmazonSNS.class);
        final AmazonSNSIdentifier someSnsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);
        /**
         * We pass the AmazonSNS test double into the SUT so that we have full control of it.
         */
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, someSnsIdentifier);
        final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);

        /**
         * This is how you use a stub in Mockito. What the code is saying is that whenever the publish method is called
         * on the snsClientTestDouble with exactly the arguments ("someTopic", "eventName", "someSubject") then it
         * should return a new PublishResult.
         *
         * Note: The stub will *only* work if all three String values passed to .publish are matched during execution.
         */
        final PublishResult result = new PublishResult();
        result.setMessageId(MESSAGE_ID);
        Mockito.when(snsClientTestDouble.publish(TOPIC, EVENT_MESSAGE, SUBJECT)).thenReturn(result);

        PublishResult actualResult = sut.publishEvent(eventToPublish);

        assertNotNull(actualResult);
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 1:
     *
     * Task: In this exercise, uncomment the Assert.assertEquals statement and see if you can figure out what value
     * should replace the ?? placedholder.
     *
     * Note: Verifying a result that is returned from the SUT is state verification.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    // This test is ignored so that the build doesn't fail. Comment out/remove this @Ignore to run locally.
    @Disabled
    public void stubbingAHappyPathValueExample() throws Exception {
        final AmazonSNS snsClientTestDouble = Mockito.mock(AmazonSNS.class);
        final AmazonSNSIdentifier someSnsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, someSnsIdentifier);
        final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);

        final PublishResult publishResult = new PublishResult();
        publishResult.setMessageId(MESSAGE_ID);
        Mockito.when(snsClientTestDouble.publish(TOPIC, EVENT_MESSAGE, SUBJECT)).thenReturn(publishResult);

        final PublishResult actualResult = sut.publishEvent(eventToPublish);

        assertNotNull(actualResult);
        assertEquals(publishResult, actualResult);
        // What should the expected result be?
        // Hint: Look at the publish() method in PackageDepartEventPublisher.
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 2:
     *
     * We noticed that there are several code paths we'd like to test to make sure we have proper code coverage.
     *
     * Task: In this exercise, we want to test the path where publishEvent() throws a DepartNotificationPublishException
     * exception.
     *
     * Note: Mockito has slightly different syntax/APIs for void versus non-void methods.
     *
     * Syntax Hint:
     * To stub a non-void method: Mockito.when(<method call here>).thenReturn(<valueToReturn>);
     * To stub a non-void method to throw an exception: Mockito.when(<method call here>).thenThrow(<exceptionToReturn>);
     * To stub a void method to throw an exception: Mockito.doThrow(<exception>).when(<testDouble>).<method call>();
     * ----------------------------------------------------------------------------------------------------------------
     */
    //    @Test(expected = DepartNotificationPublishException.class) - Junit 4
    @Test
    // This test is ignored so that the build doesn't fail. Comment out/remove this @Ignore to run locally.
    @Disabled
    public void publish_useStubbingToThrowAnException() throws Exception {
        final AmazonSNS snsClientTestDouble = Mockito.mock(AmazonSNS.class);
        final AmazonSNSIdentifier someSnsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, someSnsIdentifier);
        final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);

        /**
         * Do your stubbing here!
         */
        final PublishResult publishResult = new PublishResult();
        publishResult.setMessageId(MESSAGE_ID);
        Mockito.when(snsClientTestDouble.publish(TOPIC, EVENT_MESSAGE, SUBJECT)).thenReturn(publishResult);

        assertThrows(DepartNotificationPublishException.class,
                ()->{
                    sut.publishEvent(eventToPublish);
                });
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 3:
     *
     * So far, we've only had to stub a method once. But what happens if we want our stub to return different values
     * each time it's called?
     *
     * Task: Write a unit test that uses stubbing to have the snsClient.publish method throw an exception the first time
     * it's called and then succeed (no exception) the second time it's called.
     *
     * Syntax Hint: To stub multiple values try Mockito.when(<method call here>).thenReturn(<value1>, <value2>);
     *                                                                      .thenThrow(X).thenReturn(Y);
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test
    // This test is ignored so that the build doesn't fail. Comment out/remove this @Ignore to run locally.
    @Disabled
    public void publish_stubbingMultipleValues_FailsThenSucceedsOnRetry() {
        final AmazonSNS snsClientTestDouble = Mockito.mock(AmazonSNS.class);
        final AmazonSNSIdentifier someSnsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);
        final PackageDepartEventPublisher sut = new PackageDepartEventPublisher(snsClientTestDouble, someSnsIdentifier);
        final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);

        final PublishResult expectedResult = new PublishResult();
        expectedResult.setMessageId(MESSAGE_ID);

        /**
         * Do your stubbing here!
         */
        final PublishResult publishResult = new PublishResult();
        publishResult.setMessageId(MESSAGE_ID);
        Mockito.when(snsClientTestDouble.publish(TOPIC, EVENT_MESSAGE, SUBJECT))
                .thenThrow(AmazonServiceException.class)
                .thenReturn(publishResult);

        final PublishResult actualResult = sut.publishEvent(eventToPublish);

        assertEquals(expectedResult, actualResult);
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * Solo/Team Exercise 4:
     *
     * Task: Write a unit test that uses stubbing to fail input validation.
     * ----------------------------------------------------------------------------------------------------------------
     */
    @Test//(expected = ??.class)
    // This test is ignored so that the build doesn't fail. Comment out/remove this @Ignore to run locally.
    @Disabled
    public void publish_useStubbingToMakeInputValidationFail() throws Exception {
        /**
         * Put all the concepts together
         * 1. Define your test doubles.
         * 2. Create your SUT and inject the test doubles into it.
         * 3. Figure out which conditions would fail input validation
         * 4. Write the test to verify the error condition/exception thrown.
         */
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
