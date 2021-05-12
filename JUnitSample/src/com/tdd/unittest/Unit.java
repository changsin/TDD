package com.tdd.unittest;

public class Unit {
    public int fib(int id) {
        // TODO: this will be a problem if the id is negative. Leaving it here as a bad example.
        if (id <= 1) {
            return id;
        }
        return fib(id - 1) + fib(id - 2);
    }
}
