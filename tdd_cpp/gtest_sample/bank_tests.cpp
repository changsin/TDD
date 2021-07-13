#include <gtest/gtest.h>

/**
* The following examples are inspired by https://youtu.be/16FI1-d2P4E
**/
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

    void setBalance(int amount) { balance = amount; }

    void deposit(int amount) {
        balance += amount;
    }

    bool withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
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

// simple test
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

/**
    Test Fixture
**/
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
protected:
    BankAccount* account;
};


/**
* Tests using Test Fixture class
**/
TEST_F(BankAccountTest, EmptyAccount) {
    EXPECT_EQ(0, account->getBalance());
}

TEST_F(BankAccountTest, Deposit) {
    getAccount()->deposit(100);
    EXPECT_EQ(100, account->getBalance());
}

/**
* Parametarized tests
**/
struct AccountState {
    int initialBalance;
    int withdrawAmount;
    int expectedBalance;
    bool expectedState;
};

class BankAccountParamTest : public BankAccountTest, public testing::WithParamInterface<AccountState> {
public:
    BankAccountParamTest() {
        account->setBalance(GetParam().initialBalance);
    }
};

TEST_P(BankAccountParamTest, FinalBalance) {
    AccountState accountState = GetParam();
    bool actualState = account->withdraw(accountState.withdrawAmount);
    EXPECT_EQ(accountState.expectedBalance, account->getBalance());
    EXPECT_EQ(accountState.expectedState, actualState);
}

INSTANTIATE_TEST_SUITE_P(Default, BankAccountParamTest,
    testing::Values(AccountState{ 100, 50, 50, true },
                    AccountState{ 100, 200, 100, false }
));
