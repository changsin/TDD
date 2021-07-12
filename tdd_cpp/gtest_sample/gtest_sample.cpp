// gtest_sample.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <gtest/gtest.h>

int main(int argc, wchar_t* argv[]) {
    testing::InitGoogleTest(&argc, argv);
    std::cout << "Hello GTest!\n";
    return RUN_ALL_TESTS();
}
