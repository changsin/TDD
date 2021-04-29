/*
Open/Closed Principle: https://en.wikipedia.org/wiki/Open/closed_principle

Definition: Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.

Context: We are writing an IDE for Amazon. It has a menu system to draw menus such as "File", "Edit", etc. Currently
we have one method in the MenuSystem class of the IDE to draw menus. 

Goal: Implement givenMultipeMenuItems_whenDrawMenus_thenTheImplemenetationOfDrawMenusDoesntChange.
*/
package com.tdd.testability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OCPTest {
	@Test
	public void givenMenuSystem_whenIsValidMenuText_thenReturnsTrue() {
		// GIVEN
		OCP.MenuSystem menuSystem = new OCP.MenuSystem();
		
		//WHEN
		boolean validMenuText = menuSystem.isValidMenuText("Menu Item 1");
		
		// THEN
		assertTrue(validMenuText);
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
