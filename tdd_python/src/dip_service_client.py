class Service:
    def __init__(self, id):
        self.id = id

    def get_id(self):
        return self.id


class Client:
    def __init__(self, id):
        self.service = Service(id)

    def get_id(self):
        return self.service.get_id()
