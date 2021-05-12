package com.tdd.bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.ko.먼저;

public class Steps {
    @먼저("^Load a text file$")
    public void loadATextFile() {
        System.out.println("Loading a text file? Yes");
    }

    @먼저("Load a text file {string}")
    public void loadATextFile(String arg0) {
        System.out.println("Loading a text ㄴㄹㅁㅇㄴ ㄴㅁ file? Yes");
    }

    @When("^Open the url$")
    public void openTheUrl() {
        System.out.println("Opening the url");
    }

    @Then("^I can download the content$")
    public void iCanSeeTheContent() {
        System.out.println("Downloading the html");
    }
}
