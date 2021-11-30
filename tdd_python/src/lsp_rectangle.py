class Rectangle:
    def __init__(self, width, height, color):
        self.width = width
        self.height = height
        self.color = color

    def is_valid(self):
        return self.width >= 0 and self.height >= 0

    def get_area(self):
        return self.width * self.height

    def paint(self):
        print("Painting the rectangle")


class Square(Rectangle):
    def __init__(self, width, height, color):
        super().__init__(width, height, color)

    def is_valid(self):
        return self.width == self.height
