package com.tdd.testability;

public class OCP {
    public static class MenuSystem {
        boolean isValidMenuText(String text) {
            return text.length() < 100;
        }

        public void drawMenus() {
            // ... lots of code ...
            initBrazilMenu();

            // Drawing a Brazil menu
            String brazilMenuText = "Brazil Menu";

            if (isValidMenuText(brazilMenuText)) {
                paintBrazil(brazilMenuText);
            }

            // Drawing a Pipelines menu
            preparePipelinesMenu();

            String pipelinesMenuText = "Pipelines Menu";

            if (isValidMenuText(pipelinesMenuText)) {
                drawPipelinesMenu(pipelinesMenuText);
            }
            // ... lots of code ...
        }

        private void initBrazilMenu() {
            // ... do some work ...
        }

        private void paintBrazil(String text) {
            System.out.println(String.format("Inserting a menu item: %s", text));
        }

        private void preparePipelinesMenu() {
            // ... do some work ...
        }

        private void drawPipelinesMenu(String text) {
            System.out.println(String.format("Inserting a menu item: %s", text));
        }
    }
}
