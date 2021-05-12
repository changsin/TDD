package com.tdd.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    // 0  1  2  3  4
    // 0, 1, 1, 2, 3
    public void TestFibValid() {
        Unit unit = new Unit();
        int ret = unit.fib(3);
        assertEquals(2, ret);

        ret = unit.fib(0);
        assertEquals(0, ret);
    }
    // -1? big number? etc.
}