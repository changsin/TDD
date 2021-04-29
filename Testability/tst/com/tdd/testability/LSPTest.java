/*
Liskov Substitution Principle: https://en.wikipedia.org/wiki/Liskov_substitution_principle

Definition: If S is a subtype of T, then objects of type T in a program may be replaced with objects of type S.

Context: Our base class is Rectangle. Our subclass of Rectangle is Square. Rectangle and Square each have invariants.
	Invariant #1: We don't accept pink Rectangles. The background of our UI is pink.
	Invariant #2: We don't accept Rectangles with a negative height and/or width.
	Invariant #3: Squares must have height equal to width.

Goal: Implement givenSquareWhichInheritsFromRectangleWithNegativeHeight_whenIsValid_thenReturnsFalse
 and givenSquareWhichInheritsFromRectangle_thenShouldNotBePossibeToCreatePinkSquare.
*/
package com.tdd.testability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LSPTest {
	class Rectangle {
		int _width;
		int _height;
		String _color;

		public Rectangle(int width, int height, String color) {
			if (color.equals("Pink")) { throw new IllegalStateException(); }

			this._width = width;
			this._height = height;
			this._color = color;
		}

		public boolean isValid() {
			return this._width >= 0 && this._height >= 0;
		}

		public void paint() {
			System.out.println("Painting the screen: " + this._color);
		}

		public int getArea() {
			return this._width * this._height;
		}

		public String toString() {
			return "Width: " + this._width + ", Height: " + this._height;
		}
	}

 	class Square extends Rectangle {
		public Square(int width, int height, String color) {
			super(width, height, color);
		}

		public void setColor(String color) { this._color = color; }

		public boolean isValid() {
			return this._width == this._height;
		}
	}

	@Test
	public void givenSquare_whenGetArea_thenAreaIsExpected() {
		// GIVEN
		Square square = new Square(3, 3, "Red");
		
		// WHEN
		int area = square.getArea();
		
		// THEN
		assertEquals(9, area);
	}

	// Given: A Square that inherits from Rectangle
	// When: We give it a negative height and/or width
	// Then: The rectangle isValid() invariant should be upheld
	@Test
	public void givenSquareWhichInheritsFromRectangleWithNegativeHeight_whenIsValid_thenReturnsFalse() {
		// GIVEN
		Rectangle square = new Square(-10, -10, "White");
		// WHEN
								
		// THEN
	}
	
	
	// Given: A Square the inherits from Rectangle
	// When: We call square.setColor with "pink"
	// Then: The Rectangle invariant "No pink objects" is upheld
	//	@Test(expected = IllegalStateException.class)
	@Test()
	public void givenSquareWhichInheritsFromRectangle_thenShouldNotBePossibeToCreatePinkSquare() {
		// GIVEN
		
		// WHEN
								
		// THEN
	}
}
