/*
Open/Closed Principle: https://en.wikipedia.org/wiki/Open/closed_principle

Definition: Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.

Context: We are writing an IDE for Amazon. It has a menu system to draw menus such as "File", "Edit", etc. Currently
we have one method in the MenuSystem class of the IDE to draw menus. 

Goal: Implement givenMultipeMenuItems_whenDrawMenus_thenTheImplemenetationOfDrawMenusDoesntChange.
*/
package com.tdd.testability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OCPTest {
	class MenuSystem {
		public boolean isValidMenuText(String text) { 
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

	@Test
	public void givenMenuSystem_whenIsValidMenuText_thenReturnsTrue() {
		// GIVEN
		MenuSystem menuSystem = new MenuSystem();
		
		//WHEN
		boolean validMenuText = menuSystem.isValidMenuText("Menu Item 1");
		
		// THEN
		assertEquals(true, validMenuText);
	}

	// Given: A MenuSystem that knows about multiple menu items
	// When: We draw menus
	// Then: drawMenus() is closed to modification, but open to extension
	@Test
	public void givenMultipeMenuItems_whenDrawMenus_thenTheImplemenetationOfDrawMenusDoesntChange() {
		// GIVEN
		
		// WHEN
										
		// THEN
	}
}
