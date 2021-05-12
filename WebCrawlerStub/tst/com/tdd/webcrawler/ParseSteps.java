package com.tdd.webcrawler;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParseSteps {
    @When("^I see x element$")
    public void iSeeXElement() {
        System.out.println("SeeXElement");
    }

    @Then("^I see top text x$")
    public void iSeeTopTextX() {
        System.out.println("SeeTopTextX");
    }

    @Given("^I open \"([^\"]*)\"$")
    public void iOpen(String arg0) {
        System.out.println("Bingo" + arg0);
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
    }
}
