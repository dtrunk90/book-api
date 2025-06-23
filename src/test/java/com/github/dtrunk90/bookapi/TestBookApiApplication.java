/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi;

import org.springframework.boot.SpringApplication;

/**
 * Test entry point for the Book API application.
 *
 * <p>This class serves as an alternative application bootstrap,
 * typically used in integration testing scenarios or specialized
 * test configurations.</p>
 *
 * <p>It delegates to {@link BookApiApplication#main(String[])} using
 * {@link SpringApplication#from(Runnable)}, allowing reuse of the main
 * application setup while preserving test-specific context or overrides.</p>
 *
 * @author dtrunk90
 */
public class TestBookApiApplication {
	/**
	 * Launches the Book API application in a test context.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.from(BookApiApplication::main).run(args);
	}
}
