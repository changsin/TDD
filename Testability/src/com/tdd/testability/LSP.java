package com.tdd.testability;

public class LSP {
    public static class Rectangle {
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

    public static class Square extends Rectangle {
        public Square(int width, int height, String color) {
            super(width, height, color);
        }

        public void setColor(String color) { this._color = color; }

        public boolean isValid() {
            return this._width == this._height;
        }
    }

}
