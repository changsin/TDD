package com.tdd.testability;

import java.util.concurrent.ThreadLocalRandom;

public class DIP {
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
}

