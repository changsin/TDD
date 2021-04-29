/*
Interface Segregation Principle: https://en.wikipedia.org/wiki/Interface_segregation_principle

Definition: No client should be forced to depend on methods it does not use.

Context: We have a big interface called Cache. We have two implementations of Cache, namely LRUCache and DistributedCache.
The Worker class needs an implementation of the Cache interface which *ONLY* uses some of the Cache methods. 
In production our Worker only uses an LRUCache so our mock should focus on that. DistributedCache is just provided as an example. 
(Hint: Don't refactor Logger or DistributedCache)

Goal: Implement givenMockCacheForWorker_whenHasData_thenWeMockTheMinimalAmountOfMethods.
*/
package com.tdd.testability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ISPTest {

	@Test
	public void givenLruCache_whenGetId_thenReturnsOne() {
		// GIVEN
		ISP.LRUCache lruCache = new ISP.LRUCache();
		
		// WHEN
		int cacheId = lruCache.getId();
		
		// THEN
		assertEquals(cacheId, 1);
	}

	// Given: Several independent interfaces
	// When: We need to mock a cache for testing
	// Then: We implement the minimal amount of interfaces
	@Test
	public void givenMockCacheForWorker_whenHasData_thenWeMockTheMinimalAmountOfMethods() {
		// GIVEN
		
		// WHEN
												
		// THEN
	}
}
