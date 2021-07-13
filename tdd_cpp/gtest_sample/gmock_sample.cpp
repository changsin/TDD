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

class MyDataBase {
public:
    MyDataBase(DataBaseConnect& dbC) : dbC(dbC) {}
    bool init(string username, string password) {
        if (dbC.login(username, password) != true) {
            cout << "DB Login failed" << endl;
            //if (dbC.login(username, password) != true) {
            //    cout << "DB FAILURE for the 2nd time" << endl;
            //    return -1;
            //}
            return false;
        }
        else {
            cout << "DB SUCCESS" << endl;
            return true;
        }
    }

    int fetchRecord(int id) {
        return dbC.fetchRecord(id);
    }

private:
    DataBaseConnect& dbC;
};


TEST(TestRealDB, FlakyConnections) {
    DataBaseConnect realDB;
    MyDataBase db(realDB);

    bool retvalue = db.init("john", "password");
    EXPECT_EQ(retvalue, true);

    int record = db.fetchRecord(12);
    ASSERT_GT(record, 0);
}

TEST(TestMockDB, StableConnections) {
    MockDB mockDB;
    MyDataBase db(mockDB);

    EXPECT_CALL(mockDB, login("John", "password"))
        .WillOnce(Return(true));

    bool retValue = db.init("John", "password");
    EXPECT_EQ(retValue, true);

    //ON_CALL(mockDB, fetchRecord(12)).WillByDefault(Return(24));
    EXPECT_CALL(mockDB, fetchRecord(12)).WillRepeatedly(Return(24));
    int record = db.fetchRecord(12);
    cout << "####Record is " << record << endl;
    EXPECT_GT(record, 0);
}

TEST(MyDBTest, LoginTest) {
    MockDB mockDB;
    MyDataBase db(mockDB);

    EXPECT_CALL(mockDB, login("John", "password"))
        .Times(1)
        .WillOnce(Return(true));
    //ON_CALL(mockDB, login(_, _)).WillByDefault(Return(true));

    int retValue = db.init("John", "password");
    EXPECT_EQ(retValue, 1);
}

//TEST(MyDBTest, LoginFailure) {
//    MockDB mockDB;
//    MyDataBase db(mockDB);
//
//    EXPECT_CALL(mockDB, login(_, _))
//        .Times(2)
//        .WillOnce(Return(false));
//
//    int retValue = db.init("John", "password");
//    EXPECT_EQ(retValue, -1);
//
//}