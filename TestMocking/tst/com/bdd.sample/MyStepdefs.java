package com.bdd.sample;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishResult;
import com.tdd.mocking.PackageDepartEventPublisher;
import com.tdd.mocking.support.AmazonSNSIdentifier;
import com.tdd.mocking.support.PackageDepartEvent;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Bird{
    public void fly(){}
}
public class Duck extends Bird{}

public class MyStepdefs {
    private static final String EVENT_MESSAGE = "some message";
    private static final String MESSAGE_ID = "messageId";
    private static final String TOPIC = "someTopic";
    private static final String SUBJECT = "someSubject";

    private AmazonSNS client = Mockito.mock(AmazonSNS.class);
    private AmazonSNSIdentifier snsIdentifier;
    private PackageDepartEventPublisher sut;
    final PackageDepartEvent eventToPublish = new PackageDepartEvent(EVENT_MESSAGE);
    private PublishResult actualResult;
    private PublishResult expectedResult;

    @Given("^a sample topic$")
    public void aSampleTopic() {
        System.out.println("Preparing");
        snsIdentifier = new AmazonSNSIdentifier(SUBJECT, TOPIC);

        expectedResult = new PublishResult();
        expectedResult.setMessageId(MESSAGE_ID);
        Mockito.when(client.publish(SUBJECT, EVENT_MESSAGE,SUBJECT)).thenReturn(expectedResult);
        sut = new PackageDepartEventPublisher(client, snsIdentifier);
        System.out.println(sut);
    }

    @When("^sent to a client$")
    public void sentToAClient() {
        System.out.println("sut is");
        System.out.println(sut);
        System.out.println(eventToPublish);
        actualResult = sut.publishEvent(eventToPublish);
        System.out.println("published");
        System.out.println(actualResult);
    }

    @Then("^publish successfully$")
    public void publishSuccessfully() {
        System.out.println("verified");

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
