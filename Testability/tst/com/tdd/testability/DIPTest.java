/*
Dependency Inversion Principle: https://en.wikipedia.org/wiki/Dependency_inversion_principle

Definition: 
	A. High-level modules should not depend on low-level modules. Both should depend on abstractions.
	B. Abstractions should not depend on details. Details should depend on abstractions.

Context: We have a Service class that makes a live service call. The Client class depends on the Service.
We also have an independent class Utils that has a collection of static utility methods.

Goal: Implement givenInvertedClient_whenGetId_thenNoRealServiceCallsOccurs 
and givenUtils_whenGetId_thenNoRealServiceCallOccurs test cases.
*/
package com.tdd.testability;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DIPTest {
	static class Service {
		// Simulating a slow and non-deterministic service call.
		public int getId() throws Exception {
			int i = 0;
			while (i++ < 5) { Thread.sleep(500); }
			return ThreadLocalRandom.current().nextInt(0, 1000);
		}
	}

	static class Client {
		Service _service;

		public Client() { 
			this._service = new Service();
		}

		public int getId() throws Exception {
			return 100 + _service.getId();
		}
	}

	static class Utils {
		public static int getId() throws Exception {
			Service service = new Service();
			return 100 + service.getId();
		}
	}

	@Test
	public void givenClient_whenGetId_thenIdIsPositive() throws Exception {

		// GIVEN
		Client client = new Client();
		
		// WHEN
		int clientId = client.getId();
		
		// THEN
		assertEquals(true, clientId > 0);
	}

	// Given: A Client class that accepts a Service dependency
	// When: We retrieve getId() from the Service dependency
	// Then: We should not make a call to a real service
	@Test
	public void givenInvertedClient_whenGetId_thenNoRealServiceCallsOccurs() throws Exception {
		// GIVEN
		
		// WHEN
		
		// THEN
	}

	// Given: Our static utility method, Utils.getId()
	// When: We call it to retrieve an arbitrary id
	// Then: We should not make a call to a real service
	@Test
	public void givenUtils_whenGetId_thenNoRealServiceCallOccurs() throws Exception {
		// GIVEN
		
		// WHEN
				
		// THEN
	}

	// Extra credit: Design your own violation and refactor of the Dependency Inversion Principle.
	@Test
	public void testYourOwnViolation() throws Exception {

	}
}
