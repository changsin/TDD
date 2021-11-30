class Animal:
    def __init__(self, name):
        self.name = name
        self.fed_times = 0
        self.groomed_times = 0

    def feed(self, times):
        print("Fed {}".format(times))
        self.fed_times += times
        return self.fed_times

    def groom(self, times):
        print("Groomed {} times".format(times))
        self.groomed_times += times
        return self.groomed_times
