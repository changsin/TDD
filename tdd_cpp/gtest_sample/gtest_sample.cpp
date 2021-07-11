// gtest_sample.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <gtest/gtest.h>


class BankAccount {
public:
    explicit BankAccount(int accountId, std::string ownerName) :
        accountId(accountId), ownerName(ownerName), balance(0) {
    }

    ~BankAccount() {
    }

    int getAccountId() const { return accountId; }
    int getBalance() const { return balance; }
    std::string getOwnerName() { return ownerName; }

    void deposit(int amount) {
        balance += amount;
    }

private:
    const int accountId;
    const std::string ownerName;
    int balance;
};

class Bank {
public:
    Bank() {
    }

private:
    std::string name;
    std::_In_place_key_extract_map<int, BankAccount> accounts;
};

TEST(Account, initAccount) {
    BankAccount account(1234, "John Smith");
    ASSERT_EQ(account.getAccountId(), 1234);
    ASSERT_EQ(account.getOwnerName(), "John Smith");
    ASSERT_EQ(account.getBalance(), 0);
}

int main(int argc, wchar_t* argv[]) {
    testing::InitGoogleTest(&argc, argv);
    std::cout << "Hello GTest!\n";
    return RUN_ALL_TESTS();
    std::cout << "Hello World!\n";
}
