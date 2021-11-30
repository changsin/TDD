from abc import ABC


class Service(ABC):
    def __init__(self, id):
        self.id = id

    def get_id(self):
        print("this is an abstract class")
        return self.id


class ServiceImpl(Service):
    def __init__(self, id):
        super().__init__(id)

    def get_id(self):
        print("this is an impl class")
        return self.id


class ServiceMock(Service):
    def __init__(self, id):
        super().__init__(id)

    def get_id(self):
        print("this is a Mock class")
        return self.id


class Client:
    def __init__(self, service):
        self.service = service

    def get_id(self):
        return self.service.get_id()

# class Service:
#     def __init__(self, id):
#         self.id = id
#
#     def get_id(self):
#         return self.id
#
#
# class Client:
#     def __init__(self, id):
#         self.service = Service(id)
#
#     def get_id(self):
#         return self.service.get_id()
