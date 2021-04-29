package com.tdd.testability;

public class ISP {
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

    public static class LRUCache implements Cache {
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

    public static class DistributedCache implements Cache {
        @Override public int getId() { return 2; }
        @Override public String getName() { return "Distributed"; }
        @Override public void evict(String key) { System.out.println(key); }
        @Override public int getTTL(String key) { return 42; }
        @Override public boolean setTTL(String key, int timeToLive) { return true; }
        @Override public Object get(String key) { return new Object(); }
        @Override public boolean insert(String key, Object o) { return true; }
    }

    public static class Worker {
        private final Cache _cache;
        public Worker(Cache cache) { this._cache = cache; }
        public boolean hasData(String key) { return this._cache.get(key) != null; }
    }
}
