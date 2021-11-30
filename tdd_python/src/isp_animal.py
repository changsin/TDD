class Animal:
    def __init__(self, name):
        self.name = name

    def eat(self, food):
        print("Eating " + food)

    def pat(self, times):
        print("Patted {} times".format(times))
