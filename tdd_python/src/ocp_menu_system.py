class MenuSystem:
    def is_valid_menu_text(self, text):
        return len(text) < 100

    def draw_menus(self):
        self.init_lite_menu()

        lite_menu_text = "Lite menu"
        if self.is_valid_menu_text(lite_menu_text):
            self.draw_lite_menu()

        self.init_full_menu()

        full_menu_text = "Full menu"
        if self.is_valid_menu_text(full_menu_text):
            self.draw_full_menu()

    def init_lite_menu(self):
        print("init lite menu")

    def draw_lite_menu(self):
        print("draw lite menu")

    def init_full_menu(self):
        print("init full menu")

    def draw_full_menu(self):
        print("draw full menus")
