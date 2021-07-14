#include <iostream>
#include <vector>
#include <time.h>
#include <gtest/gtest.h>
#include <gmock/gmock.h>

using namespace std;
using ::testing::AtLeast;
using ::testing::Return;
using ::testing::_;

/**
* Inspired by https://youtu.be/dLB2aDasVTg
**/
class DataBaseConnect {
public:
    virtual bool login(string username, string password) { return true; }
    virtual bool logout(string username) { return true; }
    virtual int getRecordCount() { return 1; }
    virtual int fetchRecord(int id) {
        srand(time(NULL));
        int rvalue = rand() % 3;
        if (rvalue == 2) {
            return -1;
        }
        return id * 2;
    }
};

class MockDB : public DataBaseConnect {
public:
    MOCK_METHOD0(getRecordCount, int());
    MOCK_METHOD1(fetchRecord, int(int));
    MOCK_METHOD1(logout, bool(string username));
    MOCK_METHOD2(login, bool(string username, string password));
};

class MyService {
    const int MAX_RETRIES = 2;
public:
    MyService(DataBaseConnect& dbC) : dbC(dbC) {}
    bool init(string username, string password) {
        int retryCount = 0;
        bool retValue = dbC.login(username, password);
        while (!retValue && retryCount < MAX_RETRIES) {
            cout << "DB Login failed. Trying again..." << endl;
            retValue = dbC.login(username, password);
            retryCount++;
        }

        return retValue;
    }

    int fetchRecord(int id) {
        return dbC.fetchRecord(id);
    }

private:
    DataBaseConnect& dbC;
};


TEST(TestRealDB, FlakyConnections) {
    DataBaseConnect realDB;
    MyService service(realDB);

    bool retvalue = service.init("john", "password");
    EXPECT_EQ(retvalue, true);

    int record = service.fetchRecord(12);
    ASSERT_GT(record, 0);
}

TEST(TestMockDB, StableConnections) {
    MockDB mockDB;
    MyService service(mockDB);

    EXPECT_CALL(mockDB, login("John", "password"))
        .WillOnce(Return(true));

    bool retValue = service.init("John", "password");
    EXPECT_EQ(retValue, true);

    //ON_CALL(mockDB, fetchRecord(12)).WillByDefault(Return(24));
    EXPECT_CALL(mockDB, fetchRecord(12)).WillRepeatedly(Return(24));
    int record = service.fetchRecord(12);
    cout << "####Record is " << record << endl;
    EXPECT_GT(record, 0);
}

TEST(TestMockDB, InvalidLogin) {
    MockDB mockDB;
    MyService service(mockDB);

    EXPECT_CALL(mockDB, login(_, _))
        .Times(3)
        .WillOnce(Return(false))
        .WillOnce(Return(false))
        .WillOnce(Return(false));

    bool retValue = service.init("hn", "password");
    EXPECT_EQ(retValue, false);

    EXPECT_CALL(mockDB, fetchRecord(12)).WillRepeatedly(Return(-1));
    int record = service.fetchRecord(12);
    cout << "####Record is " << record << endl;
    EXPECT_EQ(record, -1);
}

TEST(MyDBTest, LoginTest) {
    MockDB mockDB;
    MyService service(mockDB);

    //EXPECT_CALL(mockDB, login("John", "password"))
    //    //.Times(2)
    //    .WillRepeatedly(Return(true));
    ON_CALL(mockDB, login(_, _)).WillByDefault(Return(true));

    bool retValue = service.init("John", "password");
    EXPECT_EQ(retValue, true);
}

TEST(MyDBTest, LoginRetry) {
    MockDB mockDB;
    MyService service(mockDB);

    EXPECT_CALL(mockDB, login("John", "password"))
        .Times(2)
        .WillOnce(Return(false))
        .WillOnce(Return(true));

    bool retValue = service.init("John", "password");
    EXPECT_EQ(retValue, true);
}