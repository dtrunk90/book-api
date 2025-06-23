/* SPDX-License-Identifier: GPL-3.0-or-later */

package com.github.dtrunk90.bookapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic integration test for the {@link BookApiApplication}.
 *
 * <p>This test verifies that the Spring application context can be loaded successfully.
 * It serves as a sanity check to ensure the application configuration is valid and all
 * required beans can be initialized.</p>
 *
 * <p>Annotated with {@link SpringBootTest} to bootstrap the full application context
 * during testing.</p>
 *
 * @author dtrunk90
 */
@SpringBootTest
class BookApiApplicationTests {
	/**
	 * Verifies that the Spring context loads without errors.
	 *
	 * <p>If this test fails, there may be misconfigurations in the application setup,
	 * missing beans, or other critical issues preventing the app from starting.</p>
	 */
	@Test
	void contextLoads() {
		// No implementation needed — test will fail if context cannot be loaded
	}
}
