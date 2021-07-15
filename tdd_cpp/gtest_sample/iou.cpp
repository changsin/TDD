#include <iostream>
#include <math.h>
#include <gtest/gtest.h>

using namespace std;

// input
// output
struct Box {
    int x1;
    int y1;
    int x2;
    int y2;
};

float calcIOU(Box boxA, Box boxB) {
    int xA = max(boxA.x1, boxB.x1);
    int yA = max(boxA.y1, boxB.y1);
    int xB = min(boxA.x2, boxB.x2);
    int yB = min(boxA.y2, boxB.y2);

    int intersectionArea = max(0, xB - xA) * max(0, yB - yA);

    int boxAArea = (boxA.x2 - boxA.x1) * (boxA.y2 - boxA.y1);
    int boxBArea = (boxB.x2 - boxB.x1) * (boxB.y2 - boxB.y1);

    float unionArea = boxAArea + boxBArea - intersectionArea;

    if (unionArea == 0) {
        cout << "Invalid value: division by zero" << unionArea << endl;
        return -1;
    }
    return intersectionArea / unionArea;
}


TEST(IOU, Init) {
    // Given
    Box box1 = {1, 2, 3, 4};
    Box box2 = { 2, 2, 4, 4 };

    // When
    float iou = calcIOU(box1, box2);
    cout << "IoU is " << iou << endl;

    // Then
    ASSERT_NE(iou, 0.16, 0.0);
}

TEST(Valid, normal) {
    // Given
    Box box1 = { 100, 200, 400, 300 };
    Box box2 = { 200, 250, 500, 400 };

    // When
    float iou = calcIOU(box1, box2);
    cout << "IoU is " << iou << endl;

    // Then
    ASSERT_NEAR(iou, 0.16, 0.01);
}

TEST(Invalid, All_zeroes) {
    // Given
    Box box1 = { 0, 0, 0, 0 };
    Box box2 = { 0, 0, 0, 0 };

    // When
    float iou = calcIOU(box1, box2);
    cout << "IoU is " << iou << endl;

    // Then
    ASSERT_EQ(iou,-1);
}

