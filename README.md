# TDD
Here are practice learning materials for Test Driven Development.

## Java

- IDE: IntelliJ recommended
- JDK: https://www.oracle.com/java/technologies/javase-downloads.html (Java SE 16)

The following libraries can be installed through IntelliJ Maven

### JUnit
- JUnit 5: In IntelliJ Maven, search for
  `org.junit.jupiter:junit-jupiter:5.7.0`

### For TDD
In IntelliJ Maven, search for:
- Mockito: `org.mockito:junit-jupiter:2.20.0`

- AmazonAWS: `com.amazonaws:aws-java-sdk:1.8.11`

- Google guava: `com.google.guava:guava:18.0`

### For BDD
Install IntelliJ plugins for
- Cucumber for Java
- Gherkin

Then in IntelliJ Maven, search for:
- Cucumber: `io.cucumber:cucumber-java:6.10.3`

*NB: Do NOT install Cucumber java 2.4 and lower. It has a bug:
https://stackoverflow.com/questions/67050745/cucumber-with-junit-java-lang-exceptionininitializererror

If you have a problem of installing it through Maven, you can install the jar file manually from here:
  https://jar-download.com/?search_box=cucumber-java

﻿

