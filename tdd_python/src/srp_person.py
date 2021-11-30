class SRP:
    class Person:
        def __init__(self, first_name, last_name):
            self.first_name = first_name
            self.last_name = last_name

        def get_full_name(self):
            return "{} {}".format(self.first_name, self.last_name)

        def get_full_name_in_table_format(self):
            return "<tr><td>{} {}</td></tr>".format(self.first_name, self.last_name)
