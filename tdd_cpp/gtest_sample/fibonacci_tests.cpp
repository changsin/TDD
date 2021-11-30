//#include <gtest/gtest.h>
//
//int fib(int id) {
//    if (id < 0) {
//        return -1;
//    }
//    if (id == 0 || id == 1) {
//        return id;
//    }
//
//    return fib(id - 2) + fib(id - 1);
//}
//
//TEST(FibTest, Valid) {
//    ASSERT_EQ(fib(-1), -1);
//    ASSERT_EQ(fib(0), 0);
//    ASSERT_EQ(fib(1), 1);
//    ASSERT_EQ(fib(3), 2);
//}
