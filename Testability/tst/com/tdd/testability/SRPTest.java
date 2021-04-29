/*
Single Responsibility Principle: https://en.wikipedia.org/wiki/Single_responsibility_principle

Definition: A class should have only one reason to change.

Context: We have a Person class. It stores some data about a person and can print a person report row in HTML.

Goal: Implement givenPersonWithTwoNewFields_whenGetReportRow_thenOnlyTheClassWhichIsResponsibleOfFullNameChanges
 and givenNewReportLogicWithDivs_whenGetReportRow_thenOnlyTheClassWhichIsResponsibleOfGeneratingReportsChanges.
*/
package com.tdd.testability;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SRPTest {
	class Person {
		String _firstName;
		String _lastName;

		Person(String firstName, String lastName) {
			this._firstName = firstName;
			this._lastName = lastName;
		}

		public String getFullName() {
			return this._firstName + " " + this._lastName;
		}

		public String getReportRow() {
			return String.format("<tr><td>%s</td></tr>", this._firstName + " " + this._lastName);
		}
	}

	@Test
	public void givenPersonWithFirstAndLastName_whenGetFullName_thenOutputIsExpected() {
		// GIVEN
		Person person = new Person("Buster", "Posey");
		
		// WHEN
		String fullName = person.getFullName();
		System.out.print(person.getReportRow());
		// THEN
		assertEquals("Buster Posey", fullName);
	}

	// Given: A Person object 
	// When: We add two new fields, middle name and title (Mr., Mrs., etc.)
	// Then: Only the class which is responsible for the full name changes without changing the logic about generating reports. 
	@Test
	public void givenPersonWithTwoNewFields_whenGetReportRow_thenOnlyTheClassWhichIsResponsibleOfFullNameChanges() {
		// GIVEN
		
		// WHEN
						
		// THEN
	}

	// Given: A Person object with first name, last name, middle name, title (complete previous test case first)
	// When: The HTML rows change from <tr>'s to <div>'s
	// Then: It changes without depending on details of how a fullName is calculated
	@Test
	public void givenNewReportLogicWithDivs_whenGetReportRow_thenOnlyTheClassWhichIsResponsibleOfGeneratingReportsChanges() {
		// GIVEN
		
		// WHEN
						
		// THEN
	}

	// Extra credit: Design your own violation and refactor of the Single Responsibility Principle.
	@Test
	public void testYourOwnViolation() {

	}	
}
