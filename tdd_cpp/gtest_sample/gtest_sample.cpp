// gtest_sample.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <gtest/gtest.h>


class BankAccount {
public:
    explicit BankAccount(int accountId, std::string ownerName) :
        accountId(accountId), ownerName(ownerName), balance(0) {
    }

    virtual ~BankAccount() {
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

class BankAccountTest : public testing::Test {
public:
    BankAccountTest() {
        account = new BankAccount(1234, "John Smith");
    }

    virtual ~BankAccountTest() {
        delete account;
    }

    BankAccount* getAccount() {
        return account;
    }
private:
    BankAccount* account;
};


TEST(Account, initAccount) {

    // Arange - Setup

    BankAccount account(1234, "John Smith");

    // Act - Execute
    int accountId = account.getAccountId();
    ASSERT_EQ(accountId, 1234);

    // Assert - Verify, cleanpu
    ASSERT_EQ(account.getOwnerName(), "John Smith");
    std::cout << "Hello GTest!\n";
    ASSERT_EQ(account.getBalance(), 0);
}

TEST_F(BankAccountTest, EmptyAccount) {
    EXPECT_EQ(0, getAccount()->getBalance());
}

TEST_F(BankAccountTest, Deposit) {
    getAccount()->deposit(100);
    EXPECT_EQ(100, getAccount()->getBalance());
}


int fib(int id) {
    if (id < 0) {
        return -1;
    }
    if (id == 0 || id == 1) {
        return id;
    }

    return fib(id - 2) + fib(id - 1);
}

TEST(FibTest, Valid) {
    ASSERT_EQ(fib(-1), -1);
    ASSERT_EQ(fib(0), 0);
    ASSERT_EQ(fib(1), 1);
    ASSERT_EQ(fib(3), 2);
}

int main(int argc, wchar_t* argv[]) {
    testing::InitGoogleTest(&argc, argv);
    std::cout << "Hello GTest!\n";
    return RUN_ALL_TESTS();
}
