package com.kozeljko.expensetracker;

import com.kozeljko.expensetracker.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpenseTrackerApplicationTests {

	@Autowired
	private UserController controller;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(controller);
	}
}
