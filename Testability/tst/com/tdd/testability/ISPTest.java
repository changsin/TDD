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
	static class Logger {
		public void log(int id, String name) { System.out.println(String.format("Logging for %s[%d].", name, id)); }
	}

	interface Cache {
		int getId();
		String getName();
		void evict(String key);
		int getTTL(String key);
		boolean setTTL(String key, int timeToLive);
		Object get(String key);
		boolean insert(String key, Object o);
	}

	class LRUCache implements Cache {
		private Logger _logger;

		public LRUCache() { }
		public LRUCache(Logger logger) { this._logger = logger; }

		@Override
		public int getId() { return 1; }
		
		@Override
		public String getName() { return "LRU"; }

		@Override
		public void evict(String key) { throw new UnsupportedOperationException(); }
		
		@Override
		public int getTTL(String key) { throw new UnsupportedOperationException(); }
		
		@Override
		public boolean setTTL(String key, int timeToLive) { throw new UnsupportedOperationException(); }

		@Override
		public Object get(String key) { 
			if (_logger != null) {
				_logger.log(this.getId(), this.getName());
			}

			return new Object(); 
		}
		
		@Override
		public boolean insert(String key, Object o) { 
			if (_logger != null) {
				_logger.log(this.getId(), this.getName());
			}

			return true; 
		}
	}

	class DistributedCache implements Cache {
		@Override public int getId() { return 2; }
		@Override public String getName() { return "Distributed"; }
		@Override public void evict(String key) { System.out.println(key); }
		@Override public int getTTL(String key) { return 42; }
		@Override public boolean setTTL(String key, int timeToLive) { return true; }
		@Override public Object get(String key) { return new Object(); }
		@Override public boolean insert(String key, Object o) { return true; }		
	}

	class Worker {
		private final Cache _cache;
		public Worker(Cache cache) { this._cache = cache; }
		public boolean hasData(String key) { return this._cache.get(key) != null; }
	}

	@Test
	public void givenLruCache_whenGetId_thenReturnsOne() {
		// GIVEN
		LRUCache lruCache = new LRUCache();
		
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
